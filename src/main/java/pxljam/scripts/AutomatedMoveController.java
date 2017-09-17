package pxljam.scripts;

import core.components.ScriptComponent;
import core.entity.Entity;

/**
 * Created by Alex Mitchell on 17/09/2017.
 */
public class AutomatedMoveController extends ScriptComponent{

    float dX;
    float dY;
    float x;
    float y;
    boolean movingDown;
    boolean movingRight;

    double theta;

    private float metresPerSecond = 1f;

    public AutomatedMoveController(Entity e, float dX, float dY){
        super(e);
        this.dX = dX;
        this.dY = dY;
        x = 0;
        y = 0;
        movingDown = true;
        movingRight = true;
        theta = (Math.atan(dY/dX));
    }

    @Override
    public void update(long dmt) {
        float speed = metresPerTick(dmt);
        float xChange = (float)(Math.cos(theta) * speed);
        float yChange = (float)(Math.sin(theta) * speed);

        if(movingRight){
            x += xChange;
            getEntity().translate(xChange, 0);
            if(x >= dX) movingRight = false;

        }else{
            x -= xChange;
            getEntity().translate(-xChange, 0);
            if(x <= 0) movingRight = true;
        }


        if(movingDown){
            y += yChange;
            getEntity().translate(0, yChange);
            if(y >= dY) movingDown = false;

        }else{
            y -= yChange;
            getEntity().translate(0, -yChange);
            if(y <= 0) movingDown = true;
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
