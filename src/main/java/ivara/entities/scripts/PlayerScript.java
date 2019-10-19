package ivara.entities.scripts;

import core.Script;
import core.components.SensorHandlerComponent;
import core.components.VelocityComponent;
import core.entity.GameEntity;
import core.input.Constants;
import core.input.InputHandler;
import core.input.SensorHandler;
import core.scene.Scene;
import core.struct.Sensor;
import ivara.entities.PlayerEntity;
import ivara.entities.enemies.Enemy;
import ivara.entities.enemies.ImmortalEnemy;
import ivara.scenes.Level;
import kuusisto.tinysound.Music;
import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;
import maths.Vector;

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
        RIGHT,
        LEFT
    }

    private enum State{ // Possible animation states
        WALK,
        IDLE,
        JUMP
    }

    private static final float metresPerSecond = 3.5f; // Movement speed
    private static final float jump = -9f; // Y-velocity on jump

    private final Sensor bottomSensor; // Sensor for detecting what is under the player
    private final Sensor enemySensor; // Sensor for detecting any enemy above the position of the feet

    private PlayerEntity.PlayerSprite sprite; // Current sprite

    private Vector relative; // The relative velocity of the player

    private Orientation orientation = Orientation.RIGHT; // Current player orientation
    private State state = State.IDLE; // Current player state

    private boolean canJump = true;

    private static final Sound jumpSound = TinySound.loadSound("jumpsound.wav");
    private static final Sound playerDeath = TinySound.loadSound("playerdeath.wav");
    private static final Sound playerKill = TinySound.loadSound("kill.wav");
    private static final Music playerStep = TinySound.loadMusic("steps.wav");

    private boolean walking = false;

    /**
     * Constructs a PlayerScript that controls how a the player behaves.
     * @param sprite The player sprite to alter with the script.
     * @param bottomSensor The sensor at the bottom of the player.
     * @param enemySensor The sensor that detects an enemy collision.
     */
    public PlayerScript(PlayerEntity.PlayerSprite sprite, Sensor bottomSensor, Sensor enemySensor) {
        this.sprite = sprite;
        this.bottomSensor = bottomSensor;
        this.enemySensor = enemySensor;
        relative = new Vector(0f,0f);
    }

    @Override
    public void update(int dt, GameEntity entity) {
        InputHandler.InputFrame input = entity.getInput();
        VelocityComponent vComp = entity.get(VelocityComponent.class).get();
        SensorHandler sensorHandler = entity.get(SensorHandlerComponent.class).get().getSensorHandler();

        //Enemy detection
        if(sensorHandler.isActive(enemySensor)) handleEnemy(sensorHandler, entity);

        //Bottom sensor detection
        if (sensorHandler.isActive(bottomSensor)) handleOnGround(vComp, sensorHandler, entity);
        else handleAirborne();

        //Handle potential jump
        if (input.isKeyPressed(Constants.W)) performJump(vComp);

        //Handle left and right movement
        if (input.isKeyPressed(Constants.A)) handleWalk(vComp, sensorHandler, Orientation.LEFT);
        else if (input.isKeyPressed(Constants.D)) handleWalk(vComp, sensorHandler, Orientation.RIGHT);
        else stopWalk(vComp, sensorHandler);

        //Handle pause
        if(input.isKeyReleased(Constants.ESC)) entity.getScene().getGame().pause();
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
        updateState(State.JUMP);
        stopWalkNoise();
    }

    /**
     * Handles a jump.
     * @param vComp The velocity component of the player.
     */
    private void performJump(VelocityComponent vComp){
        if (canJump) {
            jumpSound.play();
            vComp.setY(jump);
            canJump = false;
        }
    }

    /**
     * Handles the player walking.
     * @param vComp The velocity component of the player.
     * @param sensorHandler The sensor handler of the player.
     * @param o The orientation of the player.
     */
    private void handleWalk(VelocityComponent vComp, SensorHandler sensorHandler, Orientation o){
        vComp.setX(((o.equals(Orientation.LEFT)?-1:1)*metresPerSecond) + relative.x);
        updateState(o);
        if(sensorHandler.isActive(bottomSensor)){
            updateState(State.WALK);
            playWalkNoise();
        }
    }

    private void playWalkNoise(){
        if(!walking){
            playerStep.play(true);
            walking = true;
        }
    }

    private void stopWalkNoise(){
        walking = false;
        playerStep.stop();
    }

    /**
     * Sets the player velocity to be the relative velocity (y velocity already accounted for).
     * Updates the animation of the player.
     * @param vComp The velocity component of the player.
     * @param sensorHandler The sensor handler of the player.
     */
    private void stopWalk(VelocityComponent vComp, SensorHandler sensorHandler){
        vComp.setX(relative.x);
        if(sensorHandler.isActive(bottomSensor)) updateState(State.IDLE);
        stopWalkNoise();
    }

    /**
     * Does the necessary actions for when a player comes into contact with the ground.
     * @param player The player colliding.
     * @param collided The player collided.
     */
    private void groundCollision(GameEntity player, GameEntity collided) {
        canJump = true;
        VelocityComponent v = player.get(VelocityComponent.class).get();

        Vector c = collided.get(VelocityComponent.class)
                .map(VelocityComponent::getVelocity)
                .orElse(new Vector(0, 0));

        relative.setAs(c);
    }

    /**
     * Updates the state of the player.
     * @param o The new orientation.
     * @param s The new state.
     */
    private void updateState(Orientation o, State s){
        orientation = o;
        state = s;
        updateSprite();
    }

    /**
     * Updates the state of the player.
     * @param s The new state.
     */
    private void updateState(State s){
        state = s;
        updateSprite();
    }

    /**
     * Updates the state of the player.
     * @param o The new orientation.
     */
    private void updateState(Orientation o){
        orientation = o;
        updateSprite();
    }

    /**
     * Updates the current sprite of the player based on the given state.
     */
    private void updateSprite(){
        if(orientation.equals(Orientation.RIGHT)){
            switch (state){
                case WALK:
                    sprite.setState(PlayerEntity.WALK_RIGHT);
                    break;
                case IDLE:
                    sprite.setState(PlayerEntity.IDLE_RIGHT);
                    break;
                case JUMP:
                    sprite.setState(PlayerEntity.JUMP_RIGHT);
                    break;
            }
        }else{
            switch (state){
                case WALK:
                    sprite.setState(PlayerEntity.WALK_LEFT);
                    break;
                case IDLE:
                    sprite.setState(PlayerEntity.IDLE_LEFT);
                    break;
                case JUMP:
                    sprite.setState(PlayerEntity.JUMP_LEFT);
                    break;
            }
        }
    }
}
