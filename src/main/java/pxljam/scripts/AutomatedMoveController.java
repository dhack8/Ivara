package pxljam.scripts;

import core.components.ScriptComponent;
import core.entity.Entity;

/**
 * Created by Alex Mitchell on 17/09/2017.
 */
public class AutomatedMoveController extends ScriptComponent{

    private float dX;
    private float dY;
    private float x;
    private float y;
    private boolean movingDown;
    private boolean movingRight;

    private double theta;

    private float metresPerSecond;

    /**
     * Creates a script that moves an entity autonomously
     * @param e The entity to move
     * @param dX The distance that the entity moves in the x plane
     * @param dY The distance that the entity moves in the y plane
     * @param speed The speed of the block in m/s
     */
    public AutomatedMoveController(Entity e, float dX, float dY, float speed){
        super(e);
        this.dX = dX;
        this.dY = dY;
        this.metresPerSecond = speed;
        x = 0;
        y = 0;
        movingDown = true;
        movingRight = true;
        theta = (Math.atan(dY/dX));
    }

    @Override
    public void update(long dmt) { //Todo fix this, problems in relation to changing the direction of the movement
        float speed = metresPerTick(dmt);
        float xChange = (float)(Math.cos(theta) * speed);
        float yChange = (float)(Math.sin(theta) * speed);

        if(movingRight){
            if(x + xChange > dX)  xChange = dX - x;
            x = x + xChange;
            getEntity().translate(xChange, 0);
            if(x == dX) movingRight = false;

        }else{
            if(x - xChange < 0)  xChange =  x;
            x = x - xChange;
            getEntity().translate(-xChange, 0);
            if(x == 0) movingRight = true;
        }

        if(movingDown){
            if(y + yChange > dY)  yChange = dY - y;
            y =y + yChange;
            getEntity().translate(0, yChange);
            if(y == dY) movingDown = false;

        }else{
            if(y - yChange < 0)  yChange =  y;
            y = y - yChange;
            getEntity().translate(0, -yChange);
            if(y == 0) movingDown = true;
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

}
