package pxljam.scripts;

import core.components.ScriptComponent;
import core.entity.Entity;

/**
 * A basic move left -> right script for an entity
 * @author Alex Mitchell
 */
public class LeftRightController extends ScriptComponent{

    private float distance;
    private float currentX;
    private boolean movingRight;

    private float metresPerSecond = 1f;

    public LeftRightController(Entity e, float distance){
        super(e);
        this.distance = distance;
        this.currentX = 0;
        movingRight = true;
    }

    @Override
    public void update(long dmt) {
        float speed = metresPerTick(dmt);

        if(movingRight){
            currentX += speed;
            getEntity().translate(speed, 0);
            if(currentX >= distance) movingRight = false;

        }else{
            currentX -= speed;
            getEntity().translate(-speed, 0);
            if(currentX <= 0) movingRight = true;
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
