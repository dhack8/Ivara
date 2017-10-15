package ivara.entities.scripts;

import core.Script;
import core.components.VelocityComponent;
import core.entity.GameEntity;
import core.struct.Timer;
import maths.Vector;

/**
 * Created by Alex Mitchell on 12/10/2017.
 */
public class PatrolScript implements Script{

    private Vector pos1;
    private Vector pos2;
    private float speed;

    private Vector target;
    private static final float THRESHOLD = 0.01f;

    /**
     * Creates a Script that causes the entity to patrol their initial spawn location
     * @param entity The game entity
     * @param deviance The x and y deviance from the origin
     */
    public PatrolScript(GameEntity entity, Vector deviance, float speed){
        Vector home = new Vector(entity.getTransform());
        pos1 = new Vector(home.x - deviance.x, home.y - deviance.y);
        pos2 = new Vector(home.x + deviance.x, home.y + deviance.y);

        target = pos2;
        this.speed = speed;

        updateVelocity(entity);
    }


    @Override
    public void update(int dt, GameEntity entity) {
        Vector current = entity.getTransform();

        if(atPoint(current, target)){swapTarget(entity);updateVelocity(entity);}
    }

    private void updateVelocity(GameEntity entity){
        VelocityComponent velocityComponent = entity.get(VelocityComponent.class).get();
        Vector velocity = target.sub(entity.getTransform()).norm();
        velocity.scaleBy(speed);
        velocityComponent.set(velocity);
    }


    private void swapTarget(GameEntity entity){
        target = (target.equals(pos1)? pos2 : pos1);

    }

    private boolean atPoint(Vector location, Vector point){
        return location.dist(point) <= THRESHOLD;
    }

}
