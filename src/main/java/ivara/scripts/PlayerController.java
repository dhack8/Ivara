package ivara.scripts;

import core.Script;
import core.SensorListener;
import core.components.ScriptComponent;
import core.components.VelocityComponent;
import core.entity.GameEntity;
import core.input.InputHandler;
import core.struct.Sensor;
import ivara.entities.PlayerEntity;

import static core.input.InputHandler.*;

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
        float speed = metresPerTick(dt);

        PlayerEntity pEntity = (PlayerEntity)entity;
        VelocityComponent vComp = pEntity.get(VelocityComponent.class).get();

        if(InputHandler.keyPressed(W)){
            if(pEntity.canJump){
                vComp.setY(-5f);
                pEntity.canJump = false;
            }
        }else if(InputHandler.keyPressed(S)){
            vComp.setY(3f);
        }

        if(InputHandler.keyPressed(A)){
            vComp.setX(-3f);
        }else if(InputHandler.keyPressed(D)){
            vComp.setX(3f);
        }else{
            vComp.setX(0f);
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
        v.setX(0f);
        //v.setY(0f);

    }

    @Override
    public void onExit(Sensor sensor, GameEntity entity) {

    }
}
