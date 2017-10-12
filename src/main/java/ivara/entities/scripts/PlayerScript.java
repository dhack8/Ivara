package ivara.entities.scripts;

import core.Script;
import core.components.SensorHandlerComponent;
import core.components.VelocityComponent;
import core.entity.GameEntity;
import core.input.Constants;
import core.input.InputHandler;
import core.input.SensorHandler;
import core.struct.ResourceID;
import core.struct.Sensor;
import core.struct.Timer;
import ivara.entities.BulletEntity;
import ivara.entities.enemies.Enemy;
import ivara.entities.PlayerEntity;
import ivara.entities.enemies.ImortalEnemy;
import ivara.entities.sprites.PlayerSprite;
import maths.Vector;

import java.util.Arrays;

/**
 * Script to control the player entity. Relies on the current input
 * stored in InputHandler to determine control.
 * Assumes y axis goes top down and x axis goes left right.
 * @author Alex Mitchell
 * @author Will Pearson
 * @author David Hack
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
    private float jump = -9f;

    private final Sensor bottomSensor;
    private final Sensor enemySensor;

    private PlayerSprite sprite;

    private Vector relative;

    private Orientation orientation = Orientation.RIGHT;
    private State state = State.IDLE;

    private boolean canJump = true;

    public PlayerScript(PlayerSprite sprite, Sensor bottomSensor, Sensor enemySensor) {
        this.sprite = sprite;
        this.bottomSensor = bottomSensor;
        this.enemySensor = enemySensor;
        relative = new Vector(0f,0f);
    }

    /**
     * Updates the player entity.
     * @param dt elapsed milliseconds since last update
     */
    @Override
    public void update(int dt, GameEntity entity) {
        InputHandler.InputFrame input = entity.getInput();
        VelocityComponent vComp = entity.get(VelocityComponent.class).get();
        SensorHandler sensorHandler = entity.get(SensorHandlerComponent.class).get().getSensorHandler();

        //Enemy Detection
        if(sensorHandler.isActive(enemySensor)) handleEnemy(sensorHandler, entity);

        //Bottom sensor stuff---
        if (sensorHandler.isActive(bottomSensor)) handleOnGround(vComp, sensorHandler, entity);
        else handleAirborne();

        //Try jump---
        if (input.isKeyPressed(Constants.W)) performJump(vComp);

        //Handle left and right movement---
        if (input.isKeyPressed(Constants.A)) handleWalk(vComp, sensorHandler, Orientation.LEFT);
        else if (input.isKeyPressed(Constants.D)) handleWalk(vComp, sensorHandler, Orientation.RIGHT);
        else stopWalk(vComp, sensorHandler);

        /**
        //Fire more Pablos---
        if (input.isMousePressed(Constants.LEFT_MOUSE)) fireBullet(entity, input);
         **/

        //Pause menu---
        if(input.isKeyReleased(Constants.ESC)) entity.getScene().getGame().pause();
    }

    private void handleEnemy(SensorHandler sensorHandler, GameEntity player){ // Todo: fix when colliding with multiple things
        GameEntity collided = sensorHandler.getActivatingEntities(enemySensor).stream().findAny().get();
        if(collided instanceof Enemy || collided instanceof ImortalEnemy) player.getScene().resetScene();
    }

    private void handleOnGround(VelocityComponent vComp, SensorHandler sensorHandler, GameEntity player){
        GameEntity collided = sensorHandler.getActivatingEntities(bottomSensor).stream().findAny().get();
        groundCollision(player, collided);
        vComp.setY(relative.y); // Todo regardless, when the sensor is triggered the y velocity is set to 0

        if(collided instanceof Enemy && !sensorHandler.isActive(enemySensor)){
            player.getScene().removeEntity(collided);
        }else if(collided instanceof ImortalEnemy){
            player.getScene().resetScene();
        }
    }

    private void handleAirborne(){
        relative.set(0f,0f); // Todo Relative velocity reset to 0 when there is no contact with a block
        updateState(State.JUMP);
    }

    private void performJump(VelocityComponent vComp){
        if (canJump) {
            vComp.setY(jump + relative.y); // Todo, on a jump y velocity is set to relative y velocity + the jump velocity
            canJump = false;
        }
    }

    private void handleWalk(VelocityComponent vComp, SensorHandler sensorHandler, Orientation o){
        vComp.setX(((o.equals(Orientation.LEFT)?-1:1)*metresPerSecond) + relative.x);
        updateState(o);
        if(sensorHandler.isActive(bottomSensor)) updateState(State.WALK);
    }

    private void stopWalk(VelocityComponent vComp, SensorHandler sensorHandler){
        vComp.setX(relative.x);
        if(sensorHandler.isActive(bottomSensor)) updateState(State.IDLE);
    }

    /**
    private void fireBullet(GameEntity entity, InputHandler.InputFrame input){
        GameEntity bullet = new BulletEntity(entity.transform, input.getMousePosition(),new ResourceID("slimeball"), 1000, Arrays.asList(PlayerEntity.class));

        entity.getScene().addEntity(bullet);
        entity.getScene().addTimer(new Timer(1000, () -> entity.getScene().removeEntity(bullet)));
    }
     **/

    /**
     * Does the necessary actions for when a player comes into contact with the ground
     *
     * @param collided The player collided
     */
    private void groundCollision(GameEntity player, GameEntity collided) {
        canJump = true;
        VelocityComponent v = player.get(VelocityComponent.class).get();

        Vector c = collided.get(VelocityComponent.class)
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
