package pxljam.entities;

import core.components.ColliderComponent;
import core.components.PSpriteComponent;
import core.entity.Entity;
import maths.Vector;
import physics.AABBCollider;

/**
 * Created by dhack on 17-Sep-17.
 */
public class NPlatformEntity extends Entity{

    private float width;
    private float height;

    public NPlatformEntity(float x, float y, int n, String recourseID){
        super(new Vector(x,y));

        this.width = 1 * n;
        this.height = y;


        PSpriteComponent first = new PSpriteComponent(this, recourseID + "-top-left", 1, 1);
        addComponent(first);

        for(int i = 1; i<n-1; i++){
            PSpriteComponent sprite = new PSpriteComponent(this, recourseID+"-top", 1, 1);
            sprite.setTransform(new Vector(1*i, 0));
            addComponent(sprite);
        }

        PSpriteComponent last = new PSpriteComponent(this, recourseID + "-top-right", 1, 1);
        last.setTransform(new Vector(n-1, 0));

        addComponent(
                new ColliderComponent(this,
                    new AABBCollider(
                            AABBCollider.TOPLEFT,
                            new Vector(0,0),
                            new Vector(1*n, 1
                            )
                    )
                )
        );
    }
}
