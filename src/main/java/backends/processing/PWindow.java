package backends.processing;

import backends.InputBroadcaster;
import backends.Renderer;
import core.AssetHandler;
import core.components.*;
import core.components.TextComponent;
import core.entity.GameEntity;
import core.input.KeyListener;
import core.input.MouseListener;
import core.scene.Scene;
import core.struct.*;
import ivara.entities.CoinTextEntity;
import ivara.entities.TimerEntity;
import maths.Vector;
import physics.AABBCollider;
import processing.core.PApplet;
import processing.core.PFont;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Window that the game runs in. Extends the Processing window PApplet.
 * @author David Hack
 */
public class PWindow extends Renderer implements InputBroadcaster{

    private Scene currentScene;
    private AssetHandler handler;
    private int mask = 2;

    private float s = 100;
    private Vector t = new Vector(0,0); //translation
    private Vector b = new Vector(0,0); //border

    private List<KeyListener> keyListeners = new ArrayList<>();
    private List<MouseListener> mouseListeners = new ArrayList<>();

    private boolean useOpenGl;
    private boolean drawing = false;

    /**
     * Initializes the PWindow and tries to use open gl if specified however without the proper libraries this
     * can fail.
     * @param useOpenGl true to use open gl
     */
    public PWindow(boolean useOpenGl){
        this.useOpenGl = useOpenGl;
    }

    /**
     * Sets the mask, whether or not debugging view is on.
     * @param mask 1 for game 2 for debug
     */
    @Override
    public void setMask(int mask) {
        this.mask = mask;
    }

    /**
     * Draws the scene that is passed in.
     * Blocks thread
     * @param scene current scene
     */
    @Override
    public synchronized void render(Scene scene) {

        if(scene == null){
            displayError("The scene provided to the renderer is NULL!");
            return;
        }

        currentScene = scene;

        drawing = true;
        loop();
        while(drawing){
            try {
                wait();
                noLoop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Configures the width and height of the window.
     * Can potentially do full screen, this is where open gl is specified.
     */
    @Override
    public void settings(){

        if(useOpenGl) {
            //size(1600, 900, P2D);
            fullScreen(P2D);
            System.out.println("---USING OPENGL---");
        }else{
            size(1600, 900);
            //fullScreen();
            System.out.println("---USING JAVA2D---");
        }

        //noLoop();

    }

    /**
     * Processing's setup, for now loads the images to the asset handler.
     * This goes into the asset folder and loads anything inside with the id of what was before the '.'.
     */
    @Override
    public void setup(){
        loadAssets("./assets");

        // limited framerate
        frameRate(200);

        PFont font = createFont("Arial", 50);
        textFont(font);
    }

    /**
     * Loads assets in a certain folder regardless of sub directories.
     * Sub directories recursive call with it appended.
     * @param root where to start loading from, recursive.
     */
    private void loadAssets(String root){
        //Code taken from here: https://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder
        File folder = new File(root);
        File[] listOfFiles = folder.listFiles();
        //Loads assets automatically
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                //System.out.println("Loaded File: " + listOfFiles[i].getName() + " AS: " + listOfFiles[i].getName().split("\\.")[0]);
                AssetHandler.loadImage(root + "/" + listOfFiles[i].getName(), listOfFiles[i].getName().split("\\.")[0], this);
            } else if (listOfFiles[i].isDirectory()) {
                //System.out.println("Directory " + listOfFiles[i].getName());
                loadAssets(root + "/" + listOfFiles[i].getName());
            }
        }
    }

    /**
     * Draws all the entities respecting layers from render component, default layer is 0.
     * FULLSCREEN spans the whole screen stretching image as needed.
     * PIXEL_NO_TRANS draws at exactly the co ords provided.
     * NORMAL does all the transforms depending on the camera.
     * Errors are indicated with a red screen.
     * Also draws debugging boxes.
     * Has lots of helper methods.
     */
    @Override
    public synchronized void draw(){
        if(currentScene == null){
            displayError("The scene provided to the renderer is NULL!");
            return;
        }

        Camera camera = currentScene.getCamera();

        if(camera == null){
            displayError("Scene to be rendered has no camera");
            return;
        }

        Vector gameDimensions = camera.dimensions;
        Vector topLeft = camera.transform;

        Vector screenScale = new Vector(width/gameDimensions.x, height/gameDimensions.y);
        //Scale
        s = Math.min(screenScale.x, screenScale.y);
        //Translate
        t = new Vector(-topLeft.x * s, -topLeft.y * s);
        //Buffer (bars)
        b = new Vector(width/2f - (s*gameDimensions.x/2f), height/2f - (s*gameDimensions.y/2f));

        background(200);

        //Sorting
        currentScene.getEntities().stream()
                .sorted((e1, e2) -> {
                    int layer1 = e1.get(RenderComponent.class).orElse(new RenderComponent(e1, 0)).getLayer();
                    int layer2 = e2.get(RenderComponent.class).orElse(new RenderComponent(e2, 0)).getLayer();

                    return layer1 - layer2;
                }).forEach((e) -> {

                    drawSprites(e);
                    drawTexts(e);

                    //Debugging
                    if (mask == 2) {
                        drawCollider(e);
                        drawSensors(e);
                    }
                }
        );

        if (mask == 2) drawFramerate();

        //Black bars
        fill(0);
        noStroke();
        if(b.x < 0.5f){
            rect(0, 0, width, b.y);
            rect(0, height - b.y, width, b.y);
        }else{
            rect(0, 0, b.x, height);
            rect(width - b.x, 0, b.x, height);
        }

        //Blocking
        drawing = false;
        notifyAll();
    }

    /**
     * Draws the framerate in the left top corner.
     */
    private void drawFramerate() {
        textSize(10);
        text("FPS: " + frameRate, 20 + b.x, 20 + b.y);
    }

    /**
     * Will draw all the sensors belonging to a game entity.
     * @param e Entity to draw sensors from
     */
    private void drawSensors(GameEntity e){
        Optional<SensorComponent> osc = e.get(SensorComponent.class);

        if(!osc.isPresent()) return;

        SensorComponent sc = osc.get();

        for(Sensor sensor : sc.getSensors()) {
            AABBCollider ab = sensor.collider.getAABBBoundingBox();
            drawAABB(e, ab, new Color(0,255,0));
        }
    }

    /**
     * Draws the Collider of a entity.
     * entities only have one collider.
     * @param e Entity to draw collider
     */
    private void drawCollider(GameEntity e){
        Optional<ColliderComponent> occ = e.get(ColliderComponent.class);

        if(!occ.isPresent()) return;

        AABBCollider ab = occ.get().getCollider().getAABBBoundingBox();
        drawAABB(e, ab, new Color(255,0,0));
    }

    /**
     * Draws a AABB in the correct position.
     * @param e Entity it belongs to
     * @param ab The AABB itself
     * @param c The color to use
     */
    private void drawAABB(GameEntity e, AABBCollider ab, Color c){
        Vector loc = getPixelLoc(e.getTransform(), ab.getMin());

        stroke(c.getRGB());
        noFill();
        rect(loc.x, loc.y, ab.getDimension().x * s, ab.getDimension().y * s);
    }

    /**
     * Draws all the sprites of a Entity.
     * @param e Entity to draw sprites from
     */
    private void drawSprites(GameEntity e){
        Optional<SpriteComponent> osc = e.get(SpriteComponent.class);

        if(!osc.isPresent()) return;

        for(Sprite s : osc.get().getSprites()){
            drawSprite(e, s);
        }
    }

    /**
     * Draws all the texts of a Entity
     * @param e Entity to draw texts from
     */
    private void drawTexts(GameEntity e){
        Optional<TextComponent> otc = e.get(TextComponent.class);

        if(!otc.isPresent()) return;

        for(Text t : otc.get().getTexts()){
            drawText(e, t);
        }
    }

    /**
     * Draws a sprite, and respects the render component of the Entity.
     * Uses asset handler and the sprites resourceID to source the image.
     * @param e Entity that owns the sprite
     * @param sprite Sprite to draw
     */
    private void drawSprite(GameEntity e, Sprite sprite){
        RenderComponent rc = e.get(RenderComponent.class).orElse(new RenderComponent(e));
        Vector entityTransform = e.getTransform();

        if(!rc.getMode().equals(RenderComponent.Mode.FULLSCREEN)) {
            Vector loc;
            if (rc.getMode().equals(RenderComponent.Mode.NORMAL)) { //NORMAL GETS TRANSFORM
                loc = getPixelLoc(entityTransform, sprite.transform);
            }else{ //ELSE IS TRANSFORM LESS
                loc = new Vector((entityTransform.x + sprite.transform.x) + b.x, (entityTransform.y + sprite.transform.y) + b.y);
            }

            //Whether to draw with scaling or not.
            if(sprite.hasDimension()) {
                Vector dimension = new Vector(sprite.dimensions.x * s, sprite.dimensions.y * s);
                image(AssetHandler.getImage(sprite.resourceID.id), loc.x, loc.y, dimension.x, dimension.y);
            }else{
                image(AssetHandler.getImage(sprite.resourceID.id), loc.x, loc.y);
            }

        }else{ //FULLSCREEN
            image(AssetHandler.getImage(sprite.resourceID.id), b.x, b.y, width-2*b.x, height-2*b.y);
        }
    }

    /**
     * Draws a text, and respects the render component of the Entity.
     * @param e Entity that owns the text
     * @param text The text to draw
     */
    private void drawText(GameEntity e, Text text){
        RenderComponent rc = e.get(RenderComponent.class).orElse(new RenderComponent(e));
        Vector entityTransform = e.getTransform();

        Vector loc;
        if (rc.getMode().equals(RenderComponent.Mode.NORMAL) || rc.getMode().equals(RenderComponent.Mode.FULLSCREEN)) {
            loc = getPixelLoc(entityTransform, text.transform); //FULLSCREEN IS THE SAME AS NORMAL
        }else{ //NO TRANS
            loc = new Vector((entityTransform.x + text.transform.x) + b.x, (entityTransform.y + text.transform.y) + b.y);
        }

        textSize(text.fontSize);
        text(text.text, loc.x, loc.y);
    }

    /**
     * Takes a location in meters and coverts it to pixel locations.
     * s = SCALE this is how scaled meters are in relation to pixels.
     * t = TRANSLATE this is how far everything is shifted, this is stored in pixels already.
     * b = BORDER the black bars on the screen depending on aspect ratio.
     * @param meters meter location to convert
     * @return pixel location
     */
    private Vector getPixelLoc(Vector meters){
        return new Vector(meters.x*s + t.x + b.x, meters.y*s + t.y + b.y);
    }

    /**
     * Same as above but combines two vector locations together before hand useful for combining transforms.
     * @param entityTransform entity transform
     * @param componentTransform component transform
     * @return pixel location
     */
    private Vector getPixelLoc(Vector entityTransform, Vector componentTransform){
        Vector meters = new Vector(entityTransform.x + componentTransform.x, entityTransform.y + componentTransform.y);
        return getPixelLoc(meters);
    }

    /**
     * Displays a red error screen when something goes wrong, simple as that.
     * Text here will sometimes null pointer deep within the PApplet.
     * @param text reason for error but not always displayed
     */
    private void displayError(String text){
        textSize(40);
        background(255,0,0);
        fill(0);
        //TEXT THROWS ERRORS PROCESSINGS FAULT
        //text(text, 10, 40);
        drawing = false;
    }

    /**
     * Returns the world location in meters of a provided pixel co-ordinate.
     * @param x x pixel location
     * @param y y pixel location
     * @return vector of game world position
     */
    public Vector pixelToWorld(int x, int y){
        float xMeters = (x -t.x -b.x)/s;
        float yMeters = (y -t.y -b.y)/s;
        return new Vector(xMeters, yMeters);
    }

    //---------------------- Input Broadcaster ------------------------

    /**
     * Adds new key listener.
     * @param listener the key listener
     */
    @Override
    public void addKeyListener(KeyListener listener) {
        if (listener != null)
            keyListeners.add(listener);
    }

    /**
     * Adds new mouse listener.
     * @param listener the mouse listener
     */
    @Override
    public void addMouseListener(MouseListener listener) {
        if (listener != null)
            mouseListeners.add(listener);
    }


    /**
     * Passes the key press event to all key listeners.
     */
    @Override
    public void keyPressed(KeyEvent event) {
        key = keyCode == ESC ? 0 : key;
        keyListeners.forEach((keyListener -> keyListener.keyPressed(event.getKeyCode())));
    }

    /**
     * Passes the key release event to all key listeners.
     */
    @Override
    public void keyReleased(KeyEvent event) {
        key = keyCode == ESC ? 0 : key;
        keyListeners.forEach((keyListener -> keyListener.keyReleased(event.getKeyCode())));
    }


    /**
     * Passes the mouse press event to all mouse listeners.
     */
    @Override
    public void mousePressed(MouseEvent event) {
        int button = event.getButton();
        Vector position = pixelToWorld(event.getX(), event.getY()); // pixelToWorld(event.getX(), event.getY());
        mouseListeners.forEach((mouseListener -> mouseListener.mousePressed(button, position)));
    }

    /**
     * Passes the mouse release event to all mouse listeners.
     */
    @Override
    public void mouseReleased(MouseEvent event) {
        int button = event.getButton();
        Vector position = pixelToWorld(event.getX(), event.getY()); // pixelToWorld(event.getX(), event.getY());
        mouseListeners.forEach((mouseListener -> mouseListener.mouseReleased(button, position)));
    }

    /**
     * Passes the mouse moved event to all mouse listeners.
     */
    @Override
    public void mouseMoved(MouseEvent event) {
        Vector position = pixelToWorld(event.getX(), event.getY());
        mouseListeners.forEach(mouseListener -> mouseListener.mouseMoved(position));
    }

    /**
     * Passes the mouse dragged event to all mouse listeners.
     */
    @Override
    public void mouseDragged(MouseEvent event) {
        Vector position = pixelToWorld(event.getX(), event.getY());
        mouseListeners.forEach(mouseListener -> mouseListener.mouseMoved(position));
    }
}
