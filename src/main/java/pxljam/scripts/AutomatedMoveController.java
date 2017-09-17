package pxljam.scripts;

import core.components.ScriptComponent;
import core.components.VelocityComponent;
import core.entity.Entity;
import maths.Vector;

/**
 * @author Alex Mitchell
 */
public class AutomatedMoveController extends ScriptComponent{

    private Vector start;
    private Vector end;

    @Override
    public void update(long dmt) { //Todo still problems with start and end positions, moving up or left will break
        Vector current = getEntity().getPosition();
        if((current.x < start.x || current.x > end.x || current.y < start.y || current.y > end.y)){
            VelocityComponent velocityComp = getEntity().getComponents(VelocityComponent.class).stream().findAny().get();
            Vector velocity = velocityComp.getVelocity();
            velocity.mult(-1);
        }
    }

    public AutomatedMoveController(Entity e, Vector end, float time){
        super(e);
        this.start = new Vector(getEntity().getPosition());
        this.end = end;

        VelocityComponent velocityComp = getEntity().getComponents(VelocityComponent.class).stream().findAny().get();
        Vector velocity = velocityComp.getVelocity();
        velocity.set((end.x - start.x)/time, (end.y - start.y)/time);
    }

}
