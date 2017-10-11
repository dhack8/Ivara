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
import core.struct.Camera;
import core.struct.Sensor;
import core.struct.Sprite;
import core.struct.Text;
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
public class PWindow extends PApplet implements InputBroadcaster, Renderer{

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
     */
    @Override
    public void settings(){

        if(useOpenGl) {
            size(1600, 900, P2D);
            //fullScreen(P2D);
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
     */
    @Override
    public void setup(){
        //Code taken from here: https://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder
        File folder = new File("./assets");
        File[] listOfFiles = folder.listFiles();
        //Loads assets automatically
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                //System.out.println("Loaded File: " + listOfFiles[i].getName() + " AS: " + listOfFiles[i].getName().split("\\.")[0]);
                AssetHandler.loadImage("./assets/" + listOfFiles[i].getName(), listOfFiles[i].getName().split("\\.")[0], this);
            } else if (listOfFiles[i].isDirectory()) {
                //System.out.println("Directory " + listOfFiles[i].getName());
            }
        }

        // limited framerate
        frameRate(200);

        PFont font = createFont("Arial", 50);
        textFont(font);
    }

    /**
     * Draws all the entities, along with their assigned sprites within the scene. Calls the drawRect() method to
     * visualise the bounding box of the colliding entities. Checks if the sprite is dimensionless or not, to call the
     * correct draw function.
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

        currentScene.getEntities().stream()
                .sorted((e1, e2) -> {
                    int layer1 = e1.get(RenderComponent.class).orElse(new RenderComponent(e1, 0)).getLayer();
                    int layer2 = e2.get(RenderComponent.class).orElse(new RenderComponent(e2, 0)).getLayer();

                    return layer1 - layer2;
                }).forEach((e) -> {

                    drawSprites(e);
                    drawTexts(e);

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

        drawing = false;

        notifyAll();
    }

    private void drawFramerate() {
        textSize(10);
        text(frameRate, 20, 20);
    }

    private void drawSensors(GameEntity e){
        Optional<SensorComponent> osc = e.get(SensorComponent.class);

        if(!osc.isPresent()) return;

        SensorComponent sc = osc.get();

        for(Sensor sensor : sc.getSensors()) {
            AABBCollider ab = sensor.collider.getAABBBoundingBox();
            drawAABB(e, ab, new Color(0,255,0));
        }
    }

    private void drawCollider(GameEntity e){
        Optional<ColliderComponent> occ = e.get(ColliderComponent.class);

        if(!occ.isPresent()) return;

        AABBCollider ab = occ.get().getCollider().getAABBBoundingBox();
        drawAABB(e, ab, new Color(255,0,0));
    }

    //NOT AFFECTED BY RENDER COMPONENT
    private void drawAABB(GameEntity e, AABBCollider ab, Color c){
        Vector loc = getPixelLoc(e.getTransform(), ab.getMin());

        stroke(c.getRGB());
        noFill();
        rect(loc.x, loc.y, ab.getDimension().x * s, ab.getDimension().y * s);
    }

    private void drawSprites(GameEntity e){
        Optional<SpriteComponent> osc = e.get(SpriteComponent.class);

        if(!osc.isPresent()) return;

        for(Sprite s : osc.get().getSprites()){
            drawSprite(e, s);
        }
    }

    private void drawTexts(GameEntity e){
        Optional<TextComponent> otc = e.get(TextComponent.class);

        if(!otc.isPresent()) return;

        for(Text t : otc.get().getTexts()){
            drawText(e, t);
        }
    }

    //EFFECTED BY RENDER COMPONENT
    private void drawSprite(GameEntity e, Sprite sprite){
        RenderComponent rc = e.get(RenderComponent.class).orElse(new RenderComponent(e));
        Vector entityTransform = e.getTransform();

        if(!rc.getMode().equals(RenderComponent.Mode.FULLSCREEN)) {
            Vector loc;
            if (rc.getMode().equals(RenderComponent.Mode.NORMAL)) { //NORMAL GETS TRANSFORM
                loc = getPixelLoc(entityTransform, sprite.transform);
            }else{ //ELSE IS TRANSFORM LESS
                loc = new Vector((entityTransform.x + sprite.transform.x) * s + b.x, (entityTransform.y + sprite.transform.y) * s + b.y);
            }

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

    //EFFECTED BY RENDER COMPONENT
    private void drawText(GameEntity e, Text text){
        RenderComponent rc = e.get(RenderComponent.class).orElse(new RenderComponent(e));
        Vector entityTransform = e.getTransform();

        Vector loc;
        if (rc.getMode().equals(RenderComponent.Mode.NORMAL) || rc.getMode().equals(RenderComponent.Mode.FULLSCREEN)) {
            loc = getPixelLoc(entityTransform, text.transform); //FULLSCREEN IS THE SAME AS NORMAL
        }else{
            //NO TRANS
            loc = new Vector((entityTransform.x + text.transform.x) * s + b.x, (entityTransform.y + text.transform.y) * s + b.y);
        }

        textSize(text.fontSize);
        text(text.text, loc.x, loc.y);
    }

    private Vector getPixelLoc(Vector meters){
        return new Vector(meters.x*s + t.x + b.x, meters.y*s + t.y + b.y);
    }

    private Vector getPixelLoc(Vector entityTransform, Vector componentTransform){
        Vector meters = new Vector(entityTransform.x + componentTransform.x, entityTransform.y + componentTransform.y);
        return getPixelLoc(meters);
    }

    private void displayError(String text){
        textSize(40);
        background(255,0,0);
        fill(0);
        //TEXT THROWS ERRORS PROCESSINGS FAULT
        //text(text, 10, 40);
        drawing = false;
    }

    /**
     * Should return the world location in meters of a provided pixel co-ordinate.
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
        keyListeners.forEach((keyListener -> keyListener.keyPressed(event.getKeyCode())));
    }

    /**
     * Passes the key release event to all key listeners.
     */
    @Override
    public void keyReleased(KeyEvent event) {
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

    @Override
    public void mouseMoved(MouseEvent event) {
        Vector position = pixelToWorld(event.getX(), event.getY());
        mouseListeners.forEach(mouseListener -> mouseListener.mouseMoved(position));
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        Vector position = pixelToWorld(event.getX(), event.getY());
        mouseListeners.forEach(mouseListener -> mouseListener.mouseMoved(position));
    }
}
