package ivara.entities;

import core.components.ColliderComponent;
import core.components.SpriteComponent;
import core.entity.GameEntity;
import maths.Vector;
import physics.AABBCollider;

/**
 * Class that handles a platform of any specified size n, in a specified direction (horizontal/vertical) decided by the
 * boolean passed in.
 * @author James Magallanes
 */
public class NPlatformEntity extends GameEntity {

    private Vector direction;
    private String startSectionID;
    private String middleSectionID;
    private String endSectionID;

    /**
     * Constructs a NPlatform at the specified coordinates (x,y), with n amount of tiles. Is created vertically or
     * horizontally, whichever is specified in the constructor of the level the NPlatform is used in.
     * @param x x location
     * @param y y location
     * @param n amount of tiles that makes up the platform, assuming n >= 2
     * @param isVertical boolean symbolising if the platform is vertical or horizontal
     * @throws IllegalArgumentException if the user tries to call the creation of an NPlatform of size less than 2
     */
    public NPlatformEntity(float x, float y, int n, boolean isVertical) throws IllegalArgumentException{
        super(new Vector(x, y));
        if(n < 2){
            throw new IllegalArgumentException("Number of blocks too small for creation of NPlatform");
        }

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
            if(n == 1){ startSectionID = "grass-top";}
            else{ startSectionID = "grass-top-left";}

            middleSectionID = "grass-top";
            endSectionID = "grass-top-right";
        }
        
        SpriteComponent first = new SpriteComponent(this, startSectionID, 1, 1);
        addComponent(first);

        for (int i = 1; i < n - 1; i++) {
            SpriteComponent sprite = new SpriteComponent(this, middleSectionID, 1, 1, new Vector(i * direction.x, i * direction.y));
            addComponent(sprite);
        }

        Vector transform;
        if (isVertical) {
            transform = new Vector(n - n * direction.y, n - 1);
        } else {
            transform = new Vector(n - 1, 0);
        }
        SpriteComponent last = new SpriteComponent(this, endSectionID, 1, 1, transform);
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
