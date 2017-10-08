package ivara.entities;

import core.components.ColliderComponent;
import core.components.ScriptComponent;
import core.components.SpriteComponent;
import core.components.VelocityComponent;
import core.entity.GameEntity;
import core.struct.ResourceID;
import ivara.entities.scripts.BasicMoveScript;
import maths.Vector;
import physics.AABBCollider;

/**
 * Class that handles a platform of any specified size n, in a specified direction (horizontal/vertical) decided by the
 * boolean passed in.
 * @author James Magallanes
 * @author Alex Mitchell
 */
public class NPlatformEntity extends GameEntity {

    private Vector direction;
    private String startSectionID;
    private String middleSectionID;
    private String endSectionID;
    private Vector dimensions = new Vector(1,1);

    /**
     * Constructs a NPlatform at the specified coordinates (x,y), with n amount of tiles. Is created vertically or
     * horizontally, whichever is specified in the constructor of the level the NPlatform is used in.
     * @param startX x location
     * @param startY y location
     * @param numBlocks amount of tiles that makes up the platform, assuming n >= 2
     * @param isVertical boolean symbolising if the platform is vertical or horizontal
     * @throws IllegalArgumentException if the user tries to call the creation of an NPlatform of size less than 2
     */
    public NPlatformEntity(float startX, float startY, int numBlocks, boolean isVertical) throws IllegalArgumentException{
        super(new Vector(startX, startY));
        setup(startX, startY, numBlocks, isVertical, false, 0f, 0f, 0f); // todo change this
    }

    /**
     * Constructs a NPlatform at the specified coordinates (x,y), with n amount of tiles. Is created vertically or
     * horizontally, whichever is specified in the constructor of the level the NPlatform is used in.
     * This constructor also takes information to make the platform move
     * @param startX x location
     * @param startY y location
     * @param numBlocks amount of tiles that makes up the platform, assuming n >= 2
     * @param isVertical boolean symbolising if the platform is vertical or horizontal
     * @param endX The end X position of the entity
     * @param endY The end Y position of the entity
     * @param time The time taken for it to move from start to end
     * @throws IllegalArgumentException if the user tries to call the creation of an NPlatform of size less than 2
     */
    public NPlatformEntity(float startX, float startY, int numBlocks, boolean isVertical, float endX, float endY, float time) throws IllegalArgumentException{
        super(new Vector(startX, startY));
        setup(startX, startY, numBlocks, isVertical, true, endX, endY, time); // todo change this
    }


    private void setup(float startX, float startY, int numBlocks, boolean isVertical, boolean isMoving, float endX, float endY, float time){
        SpriteComponent sc = new SpriteComponent(this);

        if(numBlocks < 2){
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
            if(numBlocks == 1){ startSectionID = "grass-top";}
            else{ startSectionID = "grass-top-left";}

            middleSectionID = "grass-top";
            endSectionID = "grass-top-right";
        }

        sc.add( new ResourceID(startSectionID), dimensions);

        for (int i = 1; i < numBlocks - 1; i++) {
            sc.add(new ResourceID(middleSectionID), new Vector(i * direction.x, i * direction.y), dimensions);
        }

        Vector transform;
        if (isVertical) {
            transform = new Vector(numBlocks - numBlocks * direction.y, numBlocks - 1);
        } else {
            transform = new Vector(numBlocks - 1, 0);
        }
        sc.add(new ResourceID(endSectionID), transform, dimensions);

        if (isVertical) {
            addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.MIN_DIM,
                    new Vector(0, 0),
                    new Vector(1, direction.y * numBlocks))));
        } else {
            addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.MIN_DIM,
                    new Vector(0, 0),
                    new Vector(direction.x * numBlocks, 1))));
        }

        addComponent(sc);

        if(isMoving){ // adds a script if it's a moving block
            VelocityComponent vComp = new VelocityComponent(this);
            addComponent(vComp);

            ScriptComponent sComp = new ScriptComponent(this);
            sComp.add(new BasicMoveScript(this, new Vector(endX, endY), time));
            addComponent(sComp);
        }
    }
}
