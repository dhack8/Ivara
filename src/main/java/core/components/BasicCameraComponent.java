package core.components;

import core.entity.Entity;
import maths.Vector;

/**
 * Created by dhack on 17-Sep-17.
 */
public class BasicCameraComponent extends Component{

    private float width;

    public BasicCameraComponent(Entity entity, float width) {
        super(entity);

        this.width = width;
    }

    public Vector getPointOfInterest(){
        Vector spriteDimension = getEntity().getComponents(PSpriteComponent.class).stream().findAny().map((e) -> e.getDimensions()).orElse(new Vector(0, 0));
        Vector entityLocation = getEntity().getPosition();

        //System.out.println("Entity camera location: " + entityLocation.x + " " + entityLocation.y);

        return new Vector(entityLocation.x + (spriteDimension.x/2), entityLocation.y + (spriteDimension.y/2));
    }

    public float getWidth(){
        return width;
    }
}
