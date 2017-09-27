package ivara.scripts;

import core.components.ScriptComponent;
import core.components.VelocityComponent;
import core.entity.Entity;
import maths.Vector;

/**
 * @author Alex Mitchell
 */
public class AutomatedMoveController extends ScriptComponent {

    private Vector start;
    private Vector end;

    boolean nextSkip;

    @Override
    public void update(long dmt) { //Todo ideally the velocity should inverse when the current position is equal to either start or end. However, either collision or game tics prevent this as there is jittering
        Vector current = getEntity().getPosition();
        //if((current.x < start.x || current.x > end.x || current.y < start.y || current.y > end.y)){
        boolean outOfBoundsX = current.x < Math.min(start.x, end.x) || current.x > Math.max(start.x, end.x);
        boolean outOfBoundsY = current.y < Math.min(start.y, end.y) || current.y > Math.max(start.y, end.y);

        if ((outOfBoundsX || outOfBoundsY)) {
            VelocityComponent velocityComp = getEntity().getComponents(VelocityComponent.class).stream().findAny().get();
            Vector velocity = velocityComp.getVelocity();
            velocity.mult(-1);
        }

    }

    public AutomatedMoveController(Entity e, Vector end, float time) {
        super(e);
        this.start = new Vector(getEntity().getPosition());
        this.end = end;

        VelocityComponent velocityComp = getEntity().getComponents(VelocityComponent.class).stream().findAny().get();
        Vector velocity = velocityComp.getVelocity();
        velocity.set((end.x - start.x) / time, (end.y - start.y) / time);
    }

}
