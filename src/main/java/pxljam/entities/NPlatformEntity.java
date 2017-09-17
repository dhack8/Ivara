package pxljam.entities;

import core.components.ColliderComponent;
import core.components.PSpriteComponent;
import core.entity.Entity;
import maths.Vector;
import physics.AABBCollider;

/**
 * Class that handles a platform of any specified size n, in a specified direction (horizontal/vertical) decided by the
 * boolean passed in.
 * @author James Magallanes
 */
public class NPlatformEntity extends Entity {

    private Vector direction;
    private String startSectionID;
    private String middleSectionID;
    private String endSectionID;

    /**
     * Constructs a NPlatform at the specified coordinates (x,y), with n amount of tiles. Is created vertically or
     * horizontally, whichever is specified in the constructor of the level the NPlatform is used in.
     * @param x x location
     * @param y y location
     * @param n amount of tiles that makes up the platform
     * @param isVertical boolean symbolising if the platform is vertical or horizontal
     */
    public NPlatformEntity(float x, float y, int n, boolean isVertical) {
        super(new Vector(x, y));

        if (isVertical) {
            direction = new Vector(0, 1);
        } else {
            direction = new Vector(1, 0);
        }

        if (isVertical) {
            startSectionID = "grass-top";
            middleSectionID = "dirt";
            endSectionID = "dirt-bottom";
        } else {
            startSectionID = "grass-top-left";
            middleSectionID = "grass-top";
            endSectionID = "grass-top-right";
        }


        PSpriteComponent first = new PSpriteComponent(this, startSectionID, 1, 1);
        addComponent(first);

        for (int i = 1; i < n - 1; i++) {
            PSpriteComponent sprite = new PSpriteComponent(this, middleSectionID, 1, 1);
            sprite.setTransform(new Vector(i * direction.x, i * direction.y));
            addComponent(sprite);
        }

        PSpriteComponent last = new PSpriteComponent(this, endSectionID, 1, 1);
        if (isVertical) {
            last.setTransform(new Vector(n - n * direction.y, n - 1));
        } else {
            last.setTransform(new Vector(n - 1, 0));
        }
        addComponent(last);

        if (isVertical) {
            addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.TOPLEFT,
                    new Vector(0, 0),
                    new Vector(1, direction.y * n))));
        } else {
            addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.TOPLEFT,
                    new Vector(0, 0),
                    new Vector(direction.x * n, 1))));
        }
    }
}
