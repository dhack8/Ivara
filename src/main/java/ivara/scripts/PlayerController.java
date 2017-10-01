package ivara.scripts;

import core.Script;
import core.SensorListener;
import core.components.InputComponent;
import core.components.VelocityComponent;
import core.entity.GameEntity;
import core.input.Constants;
import core.input.InputHandler;
import core.struct.Sensor;
import ivara.entities.BulletEntity;
import ivara.entities.PlayerEntity;
import maths.Vector;

/**
 * Script to control the player entity. Relies on the current input
 * stored in InputHandler to determine control.
 * Assumes y axis goes top down and x axis goes left right.
 *
 * @author Will Pearson
 */
public class PlayerController implements Script, SensorListener {

    private float metresPerSecond = 3f;

    private final PlayerEntity player;

    public PlayerController(PlayerEntity player) {
        this.player = player;
    }

    //float gravity = 1 / 1000f; // todo temp fix for gravity
    /**
    public PlayerController(GameEntity e) {
        super(e);
    }
     **/

    /**
     * Updates the player entity.
     * @param dt elapsed milliseconds since last update
     */
    @Override
    public void update(int dt, GameEntity entity) { // Todo change how these are handled -- temp fix for the removal of translate
        InputHandler input = entity.getInput();


        float speed = metresPerTick(dt);

        PlayerEntity pEntity = (PlayerEntity)entity;
        VelocityComponent vComp = pEntity.get(VelocityComponent.class).get();

        if(input.isKeyPressed(Constants.W)){
            if(pEntity.canJump){
                vComp.setY(-7f);
                pEntity.canJump = false;
            }
        }else if(input.isKeyPressed(Constants.S)){
            vComp.setY(3f);
        }

        if(input.isKeyPressed(Constants.A)){
            vComp.setX(-3f);
        }else if(input.isKeyPressed(Constants.D)){
            vComp.setX(3f);
        }else{
            vComp.setX(0f);
        }

        if (input.isMousePressed(Constants.LEFT_MOUSE)) {
            System.out.println(input.getMousePosition().toString());

            entity.getScene().addEntity(new BulletEntity(entity.transform, input.getMousePosition(), 1000));
        }

        /**
        if (InputHandler.keyPressed(W)) {
            // TODO Jumping
            //PlayerEntity entity = (PlayerEntity)getEntity();
            PlayerEntity playerEntity = (PlayerEntity)entity;
            if(playerEntity.canJump){
                VelocityComponent comp = entity.get(VelocityComponent.class);
                comp.getVelocity().set(0, -5f);
                playerEntity.canJump = false;
            }




        }
        if (InputHandler.keyPressed(A)) {
            // TODO running
            entity.translate(-speed, 0);
        }
        if (InputHandler.keyPressed(S)) {
            // TODO ducking
            getEntity().translate(0, speed);
        }
        if (InputHandler.keyPressed(D)) {
            // TODO running
            getEntity().translate(speed, 0);
        }
        if (InputHandler.keyPressed(SPACE)) { // TODO: Remove once levels are designed properly
            // TODO extra function?
            getEntity().translate(0, -speed);
        }
        */


       // VelocityComponent v = entity.get(VelocityComponent.class); // todo temp fix for gravity
       // v.add(0, 10f/1000f * dt);
    }

    /**
     * Calculates the metres travelled per game tick.
     * @param dmt milliseconds since last game tick
     * @return metres travelled
     */
    private float metresPerTick(long dmt) {
        return metresPerSecond / 1000f * dmt;
    }

    @Override
    public void onEnter(Sensor sensor, GameEntity entity) {

    }

    @Override
    public void onActive(Sensor sensor, GameEntity entity) {
        player.canJump = true;
        VelocityComponent v = player.get(VelocityComponent.class).get();
        Vector c = entity.get(VelocityComponent.class)
                .map(VelocityComponent::getVelocity)
                .orElse(new Vector(0, 0));
        v.setX(c.x);
        v.setY(c.y);

    }

    @Override
    public void onExit(Sensor sensor, GameEntity entity) {

    }
}
