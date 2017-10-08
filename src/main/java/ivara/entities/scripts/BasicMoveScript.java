package ivara.entities.scripts;

import core.Script;
import core.components.VelocityComponent;
import core.entity.GameEntity;
import maths.Vector;

/**
 * @author Alex Mitchell
 */
public class BasicMoveScript implements Script {

    private Vector start;
    private Vector end;

    boolean nextSkip;

    @Override
    public void update(int dt, GameEntity entity) {//Todo ideally the velocity should inverse when the current position is equal to either start or end. However, either collision or game tics prevent this as there is jittering
        Vector current = entity.getTransform();
        //if((current.x < start.x || current.x > end.x || current.y < start.y || current.y > end.y)){
        boolean outOfBoundsX = current.x < Math.min(start.x, end.x) || current.x > Math.max(start.x, end.x);
        boolean outOfBoundsY = current.y < Math.min(start.y, end.y) || current.y > Math.max(start.y, end.y);

        if ((outOfBoundsX || outOfBoundsY)) {
            VelocityComponent velocityComp = entity.get(VelocityComponent.class).get();
            Vector velocity = velocityComp.getVelocity();
            velocity.scale(-1);
        }

    }

    public BasicMoveScript(GameEntity e, Vector end, float time) { // Todo may want to re-consider how we do the automated movement, as constructing a moving block is rather disgusting

        this.start = new Vector(e.getTransform());
        this.end = end;

        VelocityComponent velocityComp = e.get(VelocityComponent.class).get();
        Vector velocity = velocityComp.getVelocity();
        velocity.set((end.x - start.x) / time, (end.y - start.y) / time);
    }


}
