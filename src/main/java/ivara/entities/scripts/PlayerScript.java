package ivara.entities.scripts;

import core.Script;
import core.components.SensorHandlerComponent;
import core.components.VelocityComponent;
import core.entity.GameEntity;
import core.input.Constants;
import core.input.InputHandler;
import core.input.SensorHandler;
import core.scene.Scene;
import core.struct.ResourceID;
import core.struct.Sensor;
import core.struct.Timer;
import ivara.entities.ArrowEntity;
import ivara.entities.PlayerEntity;
import ivara.entities.enemies.Enemy;
import ivara.entities.enemies.ImmortalEnemy;
import ivara.scenes.Level;
import kuusisto.tinysound.Music;
import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;
import maths.Vector;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Script to control the player entity. Relies on the current input
 * stored in InputHandler to determine control.
 * Assumes y axis goes top down and x axis goes left right.
 * @author Alex Mitchell
 * @author Will Pearson
 * @author David Hack
 */
public class PlayerScript implements Script{

    private enum Orientation{ // Possible player sprite orientations
        RIGHT("right"),
        LEFT("left");

        private String value;

        Orientation(String value){
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    private enum PlayerState { // Possible animation states
        WALK("walk"),
        IDLE("idle"),
        JUMP("jump");

        private String value;

        PlayerState(String value){
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    private enum CrossbowState { // Possible crossbow animation states
        VISIBLE("visible"),
        HIDDEN("hidden");

        private String value;

        CrossbowState(String value){
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    private enum BootsState { // Possible crossbow animation states
        VISIBLE("visible"),
        HIDDEN("hidden");

        private String value;

        BootsState(String value){
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    private static final float metresPerSecond = 3.5f; // Movement speed
    private static final float jump = -9f; // Y-velocity on jump

    private final Sensor bottomSensor; // Sensor for detecting what is under the player
    private final Sensor enemySensor; // Sensor for detecting any enemy above the position of the feet

    private PlayerEntity.PlayerSprite sprite; // Current sprite
    private PlayerEntity.CrossbowSprite crossbowSprite;
    private PlayerEntity.BootsSprite bootsSprite;

    private Vector relative; // The relative velocity of the player

    private Orientation orientation = Orientation.RIGHT; // Current player orientation
    private PlayerState playerState = PlayerState.IDLE; // Current player state
    private CrossbowState crossbowState = CrossbowState.HIDDEN;
    private BootsState bootsState = BootsState.HIDDEN;

    private boolean moving = false;
    private boolean shooting = false;
    private boolean canShoot = true;
    private boolean inAir = false;
    private int jumpsMade = 0;
    private boolean jumpKeyPressedLast = false;
    private boolean shotKeyPressedLast = false;

    private static final Sound arrowSound = TinySound.loadSound("crossbow.wav");
    private static final Sound jumpSound = TinySound.loadSound("jumpsound.wav");
    private static final Sound playerDeath = TinySound.loadSound("playerdeath.wav");
    private static final Sound playerKill = TinySound.loadSound("kill.wav");
    private static final Music playerStep = TinySound.loadMusic("steps.wav");


    /**
     * Constructs a PlayerScript that controls how a the player behaves.
     * @param sprite The player sprite to alter with the script.
     * @param enemySensor The sensor that detects an enemy collision.
     */
    public PlayerScript(PlayerEntity.PlayerSprite sprite, PlayerEntity.CrossbowSprite crossbowSprite, PlayerEntity.BootsSprite bootsSprite, Sensor bottomSensor, Sensor enemySensor) {
        this.sprite = sprite;
        this.crossbowSprite = crossbowSprite;
        this.bootsSprite = bootsSprite;
        this.bottomSensor = bottomSensor;
        this.enemySensor = enemySensor;
        relative = new Vector(0f,0f);
        this.crossbowState = PlayerEntity.hasCrossbow() && PlayerEntity.canShootWhileMoving() ? CrossbowState.VISIBLE : CrossbowState.HIDDEN;
        this.bootsState = PlayerEntity.hasBoots() ? BootsState.VISIBLE : BootsState.HIDDEN;
    }

    @Override
    public void update(int dt, GameEntity entity) {
        InputHandler.InputFrame input = entity.getInput();
        VelocityComponent vComp = entity.get(VelocityComponent.class).get();
        SensorHandler sensorHandler = entity.get(SensorHandlerComponent.class).get().getSensorHandler();

        //Enemy detection
        if(sensorHandler.isActive(enemySensor)) {
            handleEnemy(sensorHandler, entity);
        };

        //Bottom sensor detection
        if (sensorHandler.isActive(bottomSensor)) {
            handleOnGround(vComp, sensorHandler, entity);
        }else {
            handleAirborne();
        }

        //Handle left and right movement
        if (input.isKeyPressed(Constants.A)){
            handleMove(vComp, sensorHandler, Orientation.LEFT, input.isKeyPressed(Constants.SHIFT));
            this.crossbowState = PlayerEntity.hasCrossbow() && PlayerEntity.canShootWhileMoving() ? CrossbowState.VISIBLE : CrossbowState.HIDDEN;
        }
        else if (input.isKeyPressed(Constants.D)) {
            handleMove(vComp, sensorHandler, Orientation.RIGHT, input.isKeyPressed(Constants.SHIFT));
            this.crossbowState = PlayerEntity.hasCrossbow() && PlayerEntity.canShootWhileMoving() ? CrossbowState.VISIBLE : CrossbowState.HIDDEN;
        }
        else {
            stopMove(vComp, sensorHandler);
        };

        //Handle potential jump
        if (input.isKeyPressed(Constants.W)){
            performJump(vComp, !(input.isKeyPressed(Constants.A) || input.isKeyPressed(Constants.D)));
        } else jumpKeyPressedLast = false;

        // Handle potential shot
        if(input.isKeyPressed(Constants.SPACE)){
            handleCrossbow(entity);
        }else shotKeyPressedLast = false;

        //Handle pause
        if(input.isKeyReleased(Constants.ESC)) entity.getScene().getGame().pause();

        // Update the sprites based on the state
        updatePlayerSprite();
        updateCrossbowSprite();
        updateBootsSprite();
    }


    private void handleCrossbow(GameEntity entity){
        PlayerEntity playerEntity = (PlayerEntity) entity;

        // When the crossbow can't fire
        if(!PlayerEntity.hasCrossbow() ||
                !canShoot ||
                shotKeyPressedLast ||
                ((moving || inAir) && !PlayerEntity.canShootWhileMoving()) ||
                playerEntity.getArrowsFired() >= PlayerEntity.getCrossbowQuiverSize()
        ) return;

        // Show the crossbow shooting and stop the player from moving
        this.crossbowState = CrossbowState.VISIBLE;
        this.shooting = true;
        this.canShoot = false;
        this.shotKeyPressedLast = true;

        Timer preShotTimer = new Timer(PlayerEntity.getCrossbowPreShotDelay(), (Runnable & Serializable) () -> {
            float xVelocity = orientation == Orientation.LEFT ? PlayerEntity.getCrossbowShotSpeed() * -1 : PlayerEntity.getCrossbowShotSpeed();
            float xStart = orientation == Orientation.LEFT ? entity.getTransform().x - 0.25f : entity.getTransform().x + 0.75f;
            float yStart = entity.getTransform().y + 0.9f;
            Vector location = new Vector(xStart, yStart);

            arrowSound.play();
            playerEntity.fireArrow();

            fireArrow(entity.getScene(), location, new Vector(xVelocity, 0), new ResourceID("arrow-straight-"+orientation), Arrays.asList(entity.getClass(), ArrowEntity.class), PlayerEntity.getCrossbowShotDuration());
            if(PlayerEntity.hasMultishotCrossbow()){
                fireArrow(entity.getScene(), new Vector(location), new Vector(xVelocity, -1f), new ResourceID("arrow-up-"+orientation), Arrays.asList(entity.getClass(), ArrowEntity.class), PlayerEntity.getCrossbowShotDuration());
                fireArrow(entity.getScene(), new Vector(location), new Vector(xVelocity, 1f), new ResourceID("arrow-down-"+orientation), Arrays.asList(entity.getClass(), ArrowEntity.class), PlayerEntity.getCrossbowShotDuration());
            }

            Timer postShotTimer = new Timer(PlayerEntity.getCrossbowPostShotDelay(), (Runnable & Serializable) () -> {canShoot = true;});
            entity.getScene().addTimer(postShotTimer);
            shooting = false;
        });
        entity.getScene().addTimer(preShotTimer);
    }

    private void fireArrow(Scene scene, Vector startLocation, Vector velocity, ResourceID resourceID, Collection<Class<? extends GameEntity> > nonColliders, int duration){
        GameEntity arrow = new ArrowEntity(
                startLocation,
                velocity,
                resourceID,
                nonColliders
        );
        Timer durationTimer = new Timer(duration, (Runnable & Serializable) () -> scene.removeEntity(arrow));
        scene.addEntity(arrow);
        scene.addTimer(durationTimer);
    }


    /**
     * Handles what happens when an enemy enters the enemy sensor.
     * @param sensorHandler What handles the sensors in the player.
     * @param player The player.
     */
    private void handleEnemy(SensorHandler sensorHandler, GameEntity player){
        boolean shouldDie = sensorHandler.getActivatingEntities(enemySensor).stream().filter((e)-> e instanceof  Enemy || e instanceof  ImmortalEnemy).count() > 0; // player should die if the sensor detects any enemies
        if(shouldDie) respawnPlayer(player);
    }

    /**
     * Handles what happens when the player is in contact with a block underneath him.
     * @param vComp The velocity component of the player.
     * @param sensorHandler The sensorhandler of the player.
     * @param player The player.
     */
    private void handleOnGround(VelocityComponent vComp, SensorHandler sensorHandler, GameEntity player){
        GameEntity collided = sensorHandler.getActivatingEntities(bottomSensor).stream().findAny().get();
        groundCollision(player, collided); // Gather the relative velocity
        vComp.setY(relative.y); // Set Y velocity to relative velocity regardless

        if(collided instanceof Enemy && !sensorHandler.isActive(enemySensor)){ // Kill the enemy if the player has jumped on it
            playerKill.play();
            player.getScene().removeEntity(collided);
        }else if(collided instanceof ImmortalEnemy){ // If the enemy can't die, kill the player
            respawnPlayer(player);
        }
    }

    /**
     * Checks the player is able to respawn, and kills it.
     * @param player The player.
     */
    private void respawnPlayer(GameEntity player){
        if(player instanceof PlayerEntity){
            PlayerEntity p = (PlayerEntity) player;
            Scene current = p.getScene();
            if(current instanceof Level){
                Level currentDefault = (Level) current;
                playerDeath.play();
                currentDefault.respawnPlayer(p);
            }
        }else{
            throw new IllegalArgumentException("Player script should be on a player entity");
        }
    }

    /**
     * Sets the player state when in the air.
     */
    private void handleAirborne(){
        this.playerState = PlayerState.JUMP;
        inAir = true;
        moving = false;
        playerStep.stop();
    }


    /**
     * Handles a jump.
     * @param vComp The velocity component of the player.
     */
    private void performJump(VelocityComponent vComp, boolean isStationary){
        if(jumpsMade <= PlayerEntity.getBootsAdditionalJumps() && !(jumpKeyPressedLast)){ // can jump
            jumpSound.play();

            float alteredBaseJump = jump - PlayerEntity.getBootsAdditionalHeight();

            // Set super jump
            if(isStationary && !inAir){
                alteredBaseJump -= PlayerEntity.getBootsSuperJumpPower();
            }

            // Jumps wear out
            float jumpHeight = jumpsMade < 1? alteredBaseJump : alteredBaseJump * (float) Math.pow(PlayerEntity.getBootsSuccessiveJumpPower(), jumpsMade);

            vComp.setY(jumpHeight);

            jumpKeyPressedLast = true;
            jumpsMade++;
            inAir = true;
        }
    }


    /**
     * Handles the player moving.
     * @param vComp The velocity component of the player.
     * @param sensorHandler The sensor handler of the player.
     * @param o The orientation of the player.
     */
    private void handleMove(VelocityComponent vComp, SensorHandler sensorHandler, Orientation o, boolean isRun){
        float walkingSpeed = metresPerSecond * PlayerEntity.getWalkMultiplier();
        float speed = isRun && PlayerEntity.canSprint()? walkingSpeed * PlayerEntity.getSprintMultiplier() : walkingSpeed;

        vComp.setX(((o.equals(Orientation.LEFT)?-1:1)*speed) + relative.x);

        this.orientation = o;

        if(sensorHandler.isActive(bottomSensor)){
            this.playerState = PlayerState.WALK;
            playerStep.play(true);
            moving = true;
        }
    }


    /**
     * Sets the player velocity to be the relative velocity (y velocity already accounted for).
     * Updates the animation of the player.
     * @param vComp The velocity component of the player.
     * @param sensorHandler The sensor handler of the player.
     */
    private void stopMove(VelocityComponent vComp, SensorHandler sensorHandler){
        vComp.setX(relative.x);
        if(sensorHandler.isActive(bottomSensor)){
            this.playerState = PlayerState.IDLE;
        }
        moving = false;
        playerStep.stop();
    }

    /**
     * Does the necessary actions for when a player comes into contact with the ground.
     * @param player The player colliding.
     * @param collided The player collided.
     */
    private void groundCollision(GameEntity player, GameEntity collided) {
        jumpsMade = 0;
        inAir = false;
        VelocityComponent v = player.get(VelocityComponent.class).get();

        Vector c = collided.get(VelocityComponent.class)
                .map(VelocityComponent::getVelocity)
                .orElse(new Vector(0, 0));

        relative.setAs(c);
    }

    private void updatePlayerSprite(){
        sprite.setState("player-" + playerState + "-" + orientation);
    }

    private void updateCrossbowSprite(){
        if( crossbowState == CrossbowState.HIDDEN){
            crossbowSprite.setState("crossbow-" + crossbowState);
        }else{
            crossbowSprite.setState("crossbow-" + playerState + "-" + orientation);
        }
    }

    private void updateBootsSprite(){
        if( bootsState == BootsState.HIDDEN){
            bootsSprite.setState("boots-" + bootsState);
        } else {
            bootsSprite.setState("boots-" + playerState + "-" + orientation);
        }
    }
}
