package ivara.entities;

import core.components.ColliderComponent;
import core.components.ScriptComponent;
import core.components.SpriteComponent;
import core.components.VelocityComponent;
import core.entity.GameEntity;
import core.struct.ResourceID;
import core.struct.Sprite;
import ivara.entities.scripts.BasicMoveScript;
import maths.Vector;
import physics.AABBCollider;

import java.io.File;

/**
 * Class that handles a platform of any specified size n, in a specified direction (horizontal/vertical) decided by the
 * boolean passed in.
 * @author James Magallanes
 * @author Alex Mitchell
 */
public class PlatformEntity extends GameEntity {

    private static final float largeChance = 2; //IE 1 in 2

    private Vector direction;
    private String startSectionID;
    private String middleSectionID;
    private String endSectionID;
    private Vector dimensions = new Vector(1,1);

    private static final int ADD_VEGE_CHANCE = 4; // 1 in 4 chance for there to be a vege added
    private static final float LARGE_CHANCE = 2; //IE 1 in 2
    private static final Vector LARGE_MAX = new Vector(2.5f, 3); //Max and Min dimensions of vegetation
    private static final Vector LARGE_MIN = new Vector(1.2f, 1.5f);
    private static final Vector SMALL_MAX = new Vector(1, 1);
    private static final Vector SMALL_MIN = new Vector(0.3f, 0.3f);
    private boolean addVege = true; //Whether or not to add vegetation

    private static final int NUM_LARGE;
    private static final int NUM_SMALL;

    static{
        File folder = new File("./assets/vegetation/large");
        File[] listOfFiles = folder.listFiles();

        File folder2 = new File("./assets/vegetation/small");
        File[] listOfFiles2 = folder2.listFiles();

        NUM_LARGE = listOfFiles.length;
        NUM_SMALL = listOfFiles2.length;
        System.out.println("LArge number: " + NUM_LARGE + " small num: " + NUM_SMALL);
    }

    /**
     * Constructs a Platform at the specified coordinates (x,y), with n amount of tiles. Is created vertically or
     * horizontally, whichever is specified in the constructor of the level the Platform is used in.
     * @param start The start position
     * @param numBlocks amount of tiles that makes up the platform, assuming n >= 2
     * @param isVertical boolean symbolising if the platform is vertical or horizontal
     * @throws IllegalArgumentException if the user tries to call the creation of an Platform of size less than 2
     */
    public PlatformEntity(Vector start, int numBlocks, boolean isVertical) throws IllegalArgumentException{
        super(start);
        if(numBlocks < 1) throw new IllegalArgumentException("Cannot create a platform with less than 1 block.");
        setupMulti(numBlocks, isVertical);
    }

    /**
     * Constructs a Platform at the specified coordinates (x,y), with n amount of tiles. Is created vertically or
     * horizontally, whichever is specified in the constructor of the level the Platform is used in.
     * This constructor also takes information to make the platform move
     * @param start The start position
     * @param numBlocks amount of tiles that makes up the platform, assuming n >= 2
     * @param isVertical boolean symbolising if the platform is vertical or horizontal
     * @param end The position that the platform moves to from the starting position
     * @param time The time taken for it to move from start to end
     * @throws IllegalArgumentException if the user tries to call the creation of an Platform of size less than 2
     */
    public PlatformEntity(Vector start, int numBlocks, boolean isVertical, Vector end, float time) throws IllegalArgumentException{
        super(start);
        if(numBlocks < 1) throw new IllegalArgumentException("Cannot create a platform with less than 1 block.");
        setupMulti(numBlocks, isVertical);
        setupScripts(end, time);
    }

    /**
     * Creates a Platform at the specified coordinates with a single tile.
     * @param start The position to construct the Platform
     */
    public PlatformEntity(Vector start){
        super(start);
        setupSingle();
    }

    /**
     * Creates a Platform at the specified coordinates with a single tile.
     * This platform moves to the specified end position in the specified time.
     * @param start The position to construct the Platform
     * @param end The end position for the Platform
     * @param time The time taken to get from the start to end, in seconds
     */
    public PlatformEntity(Vector start, Vector end, float time){
        super(start);
        setupSingle();
        setupScripts(end, time);
    }

    /**
     * Sets up the sprites and collider of a multiple block platform
     * @param numBlocks The number of blocks
     * @param isVertical Whether the platform is vertical
     */
    private void setupMulti(int numBlocks, boolean isVertical){

        SpriteComponent sc = new SpriteComponent(this);

        // Setup direction
        if (isVertical) {
            direction = new Vector(0, 1);
        } else {
            direction = new Vector(1, 0);
        }

        // Setup sprites
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

        // Add start of platform sprite
        sc.add(new ResourceID(startSectionID), dimensions);
        if(addVege) sc.add(getVege(new Vector(0,0), dimensions));

        // Add middle platform sprite/s
        int i;
        for (i = 1; i < numBlocks - 1; i++) {
            Vector loc = new Vector(i * direction.x, i * direction.y);
            sc.add(new ResourceID(middleSectionID), loc, dimensions);
            if(!isVertical && addVege) sc.add(getVege(loc, dimensions));
        }

        // Add end sprite
        if(numBlocks > 1)sc.add(new ResourceID(endSectionID), new Vector(i * direction.x, i * direction.y), dimensions);

        // Add collider component
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
    }

    /**
     * Sets up the sprite and collider for a single block
     */
    private void setupSingle(){
        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new ResourceID("grass-top"), dimensions);
        addComponent(sc);

        addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.MIN_DIM,
                new Vector(0, 0),dimensions)));
    }

    /**
     * Sets up the necessary movement scripts
     * @param end The end location
     * @param time The time taken to get to the end location
     */
    private void setupScripts(Vector end, float time){
        VelocityComponent vComp = new VelocityComponent(this);
        addComponent(vComp);

        ScriptComponent sComp = new ScriptComponent(this);
        sComp.add(new BasicMoveScript(this, end, time));
        addComponent(sComp);
    }

    /**
     * Checks whether a block will have a vegetation sprite drawn above it
     * @return If a block will be drawn on it
     */
    private  boolean willAddVege(){
        int result = ((int)(Math.random() * ADD_VEGE_CHANCE)) + 1;
        return result == 1;
    }

    /**
     * Will return a vegetation sprite positioned on the block that you give the location of.
     * @param locationOfBlock location of block to place vegetation on.
     * @param dimensionsOfBlock dimensions of block to place on.
     * @return Sprite of vegetation.
     */
    private Sprite getVege(Vector locationOfBlock, Vector dimensionsOfBlock){
        int vegeType = ((int)(Math.random() * LARGE_CHANCE)) + 1;

        Vector dimension;
        ResourceID id;
        if(vegeType == 1){ //Make large
            int number = ((int)(Math.random() * NUM_LARGE)) + 1;
            id = new ResourceID("vegeL" + number);
            dimension = getVegeDimen(LARGE_MAX, LARGE_MIN);

        }else{ //Make small
            int number = ((int)(Math.random() * NUM_SMALL)) + 1;
            id = new ResourceID("vege" + number);
            dimension = getVegeDimen(SMALL_MAX, SMALL_MIN);
        }

        float xLoc = (locationOfBlock.x + dimensionsOfBlock.x/2) - dimension.x/2;
        float yLoc = - dimension.y;

        return new Sprite(id,new Vector(xLoc, yLoc), dimension);
    }

    private Vector getVegeDimen(Vector max, Vector min){
        float x = (float) ((Math.random() * (max.x - min.x)) + min.x);
        float y = (float) ((Math.random() * (max.y - min.y)) + min.x);
        return new Vector(x,y);
    }
}
