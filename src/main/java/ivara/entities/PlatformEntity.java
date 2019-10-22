package ivara.entities;

import core.Script;
import core.components.ColliderComponent;
import core.components.ScriptComponent;
import core.components.SpriteComponent;
import core.components.VelocityComponent;
import core.entity.GameEntity;
import core.struct.ResourceID;
import core.struct.Sprite;
import core.struct.Timer;
import maths.Vector;
import physics.AABBCollider;

import java.io.File;
import java.io.Serializable;
import java.util.Optional;

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

    private static final int ADD_VEGE_CHANCE = 3; // 1 in 3 chance for there to be a vege added
    private static final float LARGE_CHANCE = 3; //IE 1 in 3
    private static final Vector LARGE_MAX = new Vector(2f, 3); //Max and Min dimensions of vegetation
    private static final Vector LARGE_MIN = new Vector(1.2f, 1.5f);
    private static final Vector SMALL_MAX = new Vector(0.8f, 0.7f);
    private static final Vector SMALL_MIN = new Vector(0.6f, 0.4f);
    private boolean vegesOn = true; //Whether or not to add vegetation

    private static final int NUM_LARGE;
    private static final int NUM_SMALL;

    static{
        File folder = new File("./assets/vegetation/large");
        File[] listOfFiles = folder.listFiles();

        File folder2 = new File("./assets/vegetation/small");
        File[] listOfFiles2 = folder2.listFiles();

        NUM_LARGE = listOfFiles.length;
        NUM_SMALL = listOfFiles2.length;
    }

    /**
     * Constructs a Platform at the specified coordinates (x,y), with n amount of tiles. Is created vertically or
     * horizontally, whichever is specified in the constructor of the level the Platform is used in.
     * @param theme The theme
     * @param start The start position
     * @param numBlocks amount of tiles that makes up the platform, assuming n >= 2
     * @param isVertical boolean symbolising if the platform is vertical or horizontal
     * @throws IllegalArgumentException if the user tries to call the creation of an Platform of size less than 2
     */
    public PlatformEntity(String theme, Vector start, int numBlocks, boolean isVertical) throws IllegalArgumentException{
        super(start);
        if(numBlocks < 1) throw new IllegalArgumentException("Cannot create a platform with less than 1 block.");
        setupMulti(theme, numBlocks, isVertical);
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
        setupMulti("plains", numBlocks, isVertical);
    }

    /**
     * Constructs a Platform at the specified coordinates (x,y), with n amount of tiles. Is created vertically or
     * horizontally, whichever is specified in the constructor of the level the Platform is used in.
     * This constructor also takes information to make the platform move
     * @param theme The theme
     * @param start The start position
     * @param numBlocks amount of tiles that makes up the platform, assuming n >= 2
     * @param isVertical boolean symbolising if the platform is vertical or horizontal
     * @param end The position that the platform moves to from the starting position
     * @param time The time taken for it to move from start to end
     * @throws IllegalArgumentException if the user tries to call the creation of an Platform of size less than 2
     */
    public PlatformEntity(String theme, Vector start, int numBlocks, boolean isVertical, Vector end, float time) throws IllegalArgumentException{
        super(start);
        if(time <= 0) throw new IllegalArgumentException("Time must be a strictly positive value.");
        if(numBlocks < 1) throw new IllegalArgumentException("Cannot create a platform with less than 1 block.");
        setupMulti(theme, numBlocks, isVertical);
        setupScripts(end, time);
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
        if(time <= 0) throw new IllegalArgumentException("Time must be a strictly positive value.");
        if(numBlocks < 1) throw new IllegalArgumentException("Cannot create a platform with less than 1 block.");
        setupMulti("plains", numBlocks, isVertical);
        setupScripts(end, time);
    }

    /**
     * Constructs a Platform at the specified coordinates (x,y), with n amount of tiles. Is created vertically or
     * horizontally, whichever is specified in the constructor of the level the Platform is used in.
     * @param theme The theme
     * @param start The start position
     * @param numBlocks amount of tiles that makes up the platform, assuming n >= 2
     * @param isVertical boolean symbolising if the platform is vertical or horizontal
     * @param veges to draw vegetation or not
     * @throws IllegalArgumentException if the user tries to call the creation of an Platform of size less than 2
     */
    public PlatformEntity(String theme, Vector start, int numBlocks, boolean isVertical, boolean veges) throws IllegalArgumentException{
        super(start);
        if(numBlocks < 1) throw new IllegalArgumentException("Cannot create a platform with less than 1 block.");
        vegesOn = veges;
        setupMulti(theme, numBlocks, isVertical);
    }

    /**
     * Constructs a Platform at the specified coordinates (x,y), with n amount of tiles. Is created vertically or
     * horizontally, whichever is specified in the constructor of the level the Platform is used in.
     * @param start The start position
     * @param numBlocks amount of tiles that makes up the platform, assuming n >= 2
     * @param isVertical boolean symbolising if the platform is vertical or horizontal
     * @param veges to draw vegetation or not
     * @throws IllegalArgumentException if the user tries to call the creation of an Platform of size less than 2
     */
    public PlatformEntity(Vector start, int numBlocks, boolean isVertical, boolean veges) throws IllegalArgumentException{
        super(start);
        if(numBlocks < 1) throw new IllegalArgumentException("Cannot create a platform with less than 1 block.");
        vegesOn = veges;
        setupMulti("plains", numBlocks, isVertical);
    }

    /**
     * Constructs a Platform at the specified coordinates (x,y), with n amount of tiles. Is created vertically or
     * horizontally, whichever is specified in the constructor of the level the Platform is used in.
     * This constructor also takes information to make the platform move
     * @param theme The theme
     * @param start The start position
     * @param numBlocks amount of tiles that makes up the platform, assuming n >= 2
     * @param isVertical boolean symbolising if the platform is vertical or horizontal
     * @param end The position that the platform moves to from the starting position
     * @param time The time taken for it to move from start to end
     * @param veges to draw vegetation or not
     * @throws IllegalArgumentException if the user tries to call the creation of an Platform of size less than 2
     */
    public PlatformEntity(String theme, Vector start, int numBlocks, boolean isVertical, Vector end, float time, boolean veges) throws IllegalArgumentException{
        super(start);
        if(time <= 0) throw new IllegalArgumentException("Time must be a strictly positive value.");
        if(numBlocks < 1) throw new IllegalArgumentException("Cannot create a platform with less than 1 block.");
        vegesOn = veges;
        setupMulti(theme, numBlocks, isVertical);
        setupScripts(end, time);
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
     * @param veges to draw vegetation or not
     * @throws IllegalArgumentException if the user tries to call the creation of an Platform of size less than 2
     */
    public PlatformEntity(Vector start, int numBlocks, boolean isVertical, Vector end, float time, boolean veges) throws IllegalArgumentException{
        super(start);
        if(time <= 0) throw new IllegalArgumentException("Time must be a strictly positive value.");
        if(numBlocks < 1) throw new IllegalArgumentException("Cannot create a platform with less than 1 block.");
        vegesOn = veges;
        setupMulti("plains", numBlocks, isVertical);
        setupScripts(end, time);
    }

    /**
     * Creates a Platform at the specified coordinates with a single tile.
     * @param theme The theme
     * @param start The position to construct the Platform
     */
    public PlatformEntity(String theme, Vector start){
        super(start);
        setupSingle(theme);
    }

    /**
     * Creates a Platform at the specified coordinates with a single tile.
     * @param start The position to construct the Platform
     */
    public PlatformEntity(Vector start){
        super(start);
        setupSingle("plains");
    }

    /**
     * Creates a Platform at the specified coordinates with a single tile.
     * @param theme The theme
     * @param start The position to construct the Platform
     * @param veges to draw vegetation or not
     */
    public PlatformEntity(String theme, Vector start, boolean veges){
        super(start);
        vegesOn = veges;
        setupSingle(theme);
    }

    /**
     * Creates a Platform at the specified coordinates with a single tile.
     * @param start The position to construct the Platform
     * @param veges to draw vegetation or not
     */
    public PlatformEntity(Vector start, boolean veges){
        super(start);
        vegesOn = veges;
        setupSingle("plains");
    }

    /**
     * Creates a Platform at the specified coordinates with a single tile.
     * This platform moves to the specified end position in the specified time.
     * @param theme The theme
     * @param start The position to construct the Platform
     * @param end The end position for the Platform
     * @param time The time taken to get from the start to end, in seconds
     */
    public PlatformEntity(String theme, Vector start, Vector end, float time){
        super(start);
        if(time <= 0) throw new IllegalArgumentException("Time must be a strictly positive value.");
        setupSingle(theme);
        setupScripts(end, time);
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
        if(time <= 0) throw new IllegalArgumentException("Time must be a strictly positive value.");
        setupSingle("plains");
        setupScripts(end, time);
    }

    /**
     * Sets up the sprites and collider of a multiple block platform
     * @param numBlocks The number of blocks
     * @param isVertical Whether the platform is vertical
     */
    private void setupMulti(String theme, int numBlocks, boolean isVertical){

        SpriteComponent sc = new SpriteComponent(this);

        // Setup direction
        if (isVertical) {
            direction = new Vector(0, 1);
        } else {
            direction = new Vector(1, 0);
        }

        // Setup sprites
        if (isVertical) {
            startSectionID = theme + "-top";
            middleSectionID = theme + "-middle";
            endSectionID = theme + "-bottom";
        } else {
            if(numBlocks == 1){ startSectionID = theme + "-top";}
            else{ startSectionID = theme + "-left";}

            middleSectionID = theme + "-top";
            endSectionID = theme + "-right";
        }

        // Add start of platform sprite
        sc.add(new ResourceID(startSectionID), dimensions);
        if(vegesOn && willAddVege()) sc.add(getVege(new Vector(0,0), dimensions));

        // Add middle platform sprite/s
        int i;
        for (i = 1; i < numBlocks - 1; i++) {
            Vector loc = new Vector(i * direction.x, i * direction.y);
            sc.add(new ResourceID(middleSectionID), loc, dimensions);
            if(!isVertical && vegesOn && willAddVege()) sc.add(getVege(loc, dimensions));
        }

        // Add end sprite
        if(numBlocks > 1)sc.add(new ResourceID(endSectionID), new Vector(i * direction.x, i * direction.y), dimensions);

        // Add collider component
        if (isVertical) {
            dimensions = new Vector(1, direction.y * numBlocks);
            addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.MIN_DIM,
                    new Vector(0, 0),
                    dimensions)));
        } else {
            dimensions = new Vector(direction.x * numBlocks, 1);
            addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.MIN_DIM,
                    new Vector(0, 0),
                    dimensions)));
        }
        addComponent(sc);
    }

    /**
     * Sets up the sprite and collider for a single block
     */
    private void setupSingle(String theme){
        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new ResourceID(theme + "-top"), dimensions);
        addComponent(sc);

        addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.MIN_DIM,
                new Vector(0, 0),dimensions)));

        if(vegesOn && willAddVege()) sc.add(getVege(new Vector(0,0), dimensions));
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
        //sComp.add(new BasicMoveScript(this, end, time));
        sComp.add(new MoveScript(end, time));
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


    /**
     * MoveScript enables an entity to move between two given locations over a given time.
     * @author Alex Mitchell
     */
    private class MoveScript implements Script {

        private Vector pos1;
        private Vector pos2;
        private Vector target; // Currently targeted position

        private final float time; // Time taken to move from one place to another
        private static final float THRESHOLD = 0.01f; // The threshold used to check if the entity is at a target position
        private static final int PAUSE_TIME = 500;

        /**
         * Constructs a MoveScript to give to an entity.
         * @param end The end position.
         * @param time The time taken to get to this position.
         */
        public MoveScript(Vector end, float time){
            pos2 = end;
            pos1 = new Vector(PlatformEntity.this.getTransform());
            target = pos2;
            this.time = time;
            updateVelocity(PlatformEntity.this);
        }

        @Override
        public void update(int dt, GameEntity entity) {
            Vector current = entity.getTransform();
            if(atPoint(current, target)) {swapTarget();updateVelocity(entity);}
        }

        /**
         * Swaps the target
         */
        private void swapTarget(){
            target = (target.equals(pos1)? pos2 : pos1);
        }

        /**
         * Updates the velocity of the entity with the target position.
         * @param entity The entity to update.
         */
        private void updateVelocity(GameEntity entity){
            VelocityComponent velocityComponent = entity.get(VelocityComponent.class).get();
            float speed = target.dist(entity.getTransform())/time;
            Vector velocity = target.sub(entity.getTransform()).norm();
            velocity.scaleBy(speed);
            velocityComponent.set(velocity);

            if(entity.getScene() == null) return; // todo working on this
            velocityComponent.pause();
            entity.getScene().addTimer(new Timer(PAUSE_TIME, (Runnable & Serializable) velocityComponent::unpause));
        }

        /**
         * Checks whether two given points "equal" eachother with a given threshold for rounding issues.
         * @param location The first location.
         * @param point The second location.
         * @return Whether the points are within a close enough proximity to consider as being equal.
         */
        private boolean atPoint(Vector location, Vector point){
            return location.dist(point) <= THRESHOLD;
        }
    }
}
