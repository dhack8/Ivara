package ivara.scripts;

import core.Script;
import core.components.SensorHandlerComponent;
import core.components.VelocityComponent;
import core.entity.GameEntity;
import core.input.Constants;
import core.input.InputHandler;
import core.input.SensorHandler;
import core.struct.Sensor;
import core.struct.Timer;
import ivara.entities.BulletEntity;
import ivara.entities.PlayerEntity;
import ivara.entities.sprites.PlayerSprite;
import javafx.geometry.Orientation;
import maths.Vector;
import physics.AABBCollider;
import util.Debug;

/**
 * Script to control the player entity. Relies on the current input
 * stored in InputHandler to determine control.
 * Assumes y axis goes top down and x axis goes left right.
 * @author Alex Mitchell
 * @author Will Pearson
 */
public class PlayerScript implements Script{//}, SensorListener {

    private enum Orientation{
        RIGHT,
        LEFT
    }

    private enum State{
        WALK,
        IDLE,
        JUMP
    }

    private float metresPerSecond = 3f;

    private final PlayerEntity player; //todo do we need this?
    private final Sensor bottomSensor;

    private PlayerSprite sprite;

    private Vector relative;

    private Orientation orientation = Orientation.RIGHT;
    private State state = State.IDLE;

    public PlayerScript(PlayerEntity player, PlayerSprite sprite, Sensor bottomSensor) {
        this.player = player;
        this.sprite = sprite;
        this.bottomSensor = bottomSensor;
        relative = new Vector(0f,0f);
    }

    /**
     * Updates the player entity.
     * @param dt elapsed milliseconds since last update
     */
    @Override
    public void update(int dt, GameEntity entity) {
        float speed = metresPerTick(dt); // Todo consider this for velocity
        InputHandler.InputFrame input = entity.getInput();
        PlayerEntity pEntity = (PlayerEntity) entity;
        VelocityComponent vComp = pEntity.get(VelocityComponent.class).get();

        SensorHandler sensorHandler = entity.get(SensorHandlerComponent.class).get().getSensorHandler();
        if (sensorHandler.isActive(bottomSensor)) {
            GameEntity collided = sensorHandler.getActivatingEntities(bottomSensor).stream().findAny().get();
            groundCollision(pEntity, collided);
            vComp.setY(relative.y); // Todo regardless, when the sensor is triggered the y velocity is set to 0
        }else{
            relative.set(0f,0f); // Todo Relative velocity reset to 0 when there is no contact with a block
            updateState(State.JUMP);
        }

        if (input.isKeyPressed(Constants.W)) {
            if (pEntity.canJump) {
                vComp.setY(-7f + relative.y); // Todo, on a jump y velocity is set to relative y velocity + the jump velocity
                pEntity.canJump = false;
            }
        }

        if (input.isKeyPressed(Constants.A)) {
            vComp.setX(-3f + relative.x); // Todo on a move left or right, horizontal speed is set to relative y velocity + move velocity
            updateState(Orientation.LEFT);
            if(sensorHandler.isActive(bottomSensor)) updateState(State.WALK);
        } else if (input.isKeyPressed(Constants.D)) {
            vComp.setX(3f + relative.x);
            updateState(Orientation.RIGHT);
            if(sensorHandler.isActive(bottomSensor)) updateState(State.WALK);
        }else{
            vComp.setX(relative.x); // Todo when no left or right is pressed, the speed is set to the relative x speed
            if(sensorHandler.isActive(bottomSensor)) updateState(State.IDLE);
        }

        //todo for testing with levelmanager
        /**
        accum += dt;
        if(accum > 5000){
            accum = 0;
            entity.getScene().getGame().nextScene();
        }

        if(input.isKeyPressed(Constants.SPACE)){
            entity.getScene().getGame().pause();
            System.out.println("Pause");
        }
        **/

        if (input.isMousePressed(Constants.LEFT_MOUSE)) {

            GameEntity bullet = new BulletEntity(entity.transform, input.getMousePosition(), 1000);
            entity.getScene().addEntity(bullet);
            entity.getScene().addTimer(new Timer(1000, () -> entity.getScene().removeEntity(bullet)));
        }

        if (input.isKeyReleased(Constants.W)) {
            Debug.log("Released UP");
        }

        if(input.isKeyReleased(Constants.SPACE)){ // pause menu
            Debug.log("Released SPACE");
            entity.getScene().getGame().pause();
        }
    }

    /**
     * Calculates the metres travelled per game tick.
     * @param dmt milliseconds since last game tick
     * @return metres travelled
     */
    private float metresPerTick(long dmt) {
        return metresPerSecond / 1000f * dmt;
    }


    /**
     * Does the necessary actions for when a player comes into contact with the ground
     *
     * @param entity The player entity
     */
    private void groundCollision(PlayerEntity player, GameEntity entity) {
        player.canJump = true;
        VelocityComponent v = player.get(VelocityComponent.class).get();

        Vector c = entity.get(VelocityComponent.class)
                .map(VelocityComponent::getVelocity)
                .orElse(new Vector(0, 0));

        relative.set(c); // todo relative speed is set
    }

    private void updateState(Orientation o, State s){
        orientation = o;
        state = s;
        updateSprite();
    }

    private void updateState(State s){
        state = s;
        updateSprite();
    }

    private void updateState(Orientation o){
        orientation = o;
        updateSprite();
    }

    private void updateSprite(){
        if(orientation.equals(Orientation.RIGHT)){
            switch (state){
                case WALK:
                    sprite.setState(PlayerSprite.WALK_RIGHT);
                    break;
                case IDLE:
                    sprite.setState(PlayerSprite.IDLE_RIGHT);
                    break;
                case JUMP:
                    sprite.setState(PlayerSprite.JUMP_RIGHT);
                    break;
            }
        }else{
            switch (state){
                case WALK:
                    sprite.setState(PlayerSprite.WALK_LEFT);
                    break;
                case IDLE:
                    sprite.setState(PlayerSprite.IDLE_LEFT);
                    break;
                case JUMP:
                    sprite.setState(PlayerSprite.JUMP_LEFT);
                    break;
            }
        }
    }
}
