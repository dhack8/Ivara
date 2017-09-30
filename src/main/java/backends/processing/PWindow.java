package backends.processing;

import backends.InputBroadcaster;
import backends.Renderer;
import core.AssetHandler;
import core.Game;
import core.components.*;
import core.entity.GameEntity;
import core.input.KeyListener;
import core.input.MouseListener;
import core.scene.Scene;
import core.struct.Camera;
import core.struct.Sensor;
import core.struct.Sprite;
import maths.Vector;
import physics.AABBCollider;
import processing.core.PApplet;

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

    /**
     * TODO
     * @param mask
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
    public void render(Scene scene) {
        if(scene == null){
            textSize(40);
            background(255,0,0);
            //text("The scene provided to the renderer is NULL!", 10, 40);
            return;
        }

        currentScene = scene;
        redraw();
    }

    /**
     * Configures the width and height of the window.
     */
    @Override
    public void settings(){
        fullScreen();
        noLoop();
    }

    /**
     * Processing's setup, for now loads the images to the asset handler.
     */
    @Override
    public void setup(){
        AssetHandler.loadImage("./assets/dirt.png", "dirt", this);
        AssetHandler.loadImage("./assets/dirt-bottom.png", "dirt-bottom", this);
        AssetHandler.loadImage("./assets/grass-bottom.png", "grass-bottom", this);
        AssetHandler.loadImage("./assets/grass-bottom-left.png", "grass-bottom-left", this);
        AssetHandler.loadImage("./assets/grass-bottom-right.png", "grass-bottom-right", this);
        AssetHandler.loadImage("./assets/grass-left.png", "grass-left", this);
        AssetHandler.loadImage("./assets/grass-right.png", "grass-right", this);
        AssetHandler.loadImage("./assets/grass-top.png", "grass-top", this);
        AssetHandler.loadImage("./assets/grass-top-left.png", "grass-top-left", this);
        AssetHandler.loadImage("./assets/grass-top-right.png", "grass-top-right", this);
        AssetHandler.loadImage("./assets/player.png", "player", this);
        AssetHandler.loadImage("./assets/background.png", "background", this);
    }

    /**
     * Draws all the entities, along with their assigned sprites within the scene. Calls the drawRect() method to
     * visualise the bounding box of the colliding entities. Checks if the sprite is dimensionless or not, to call the
     * correct draw function.
     */
    @Override
    public void draw(){
        if(currentScene == null) return;

        Camera camera = currentScene.getCamera();

        Vector gameDimensions = camera.dimensions;
        Vector topLeft = camera.transform;

        Vector screenScale = new Vector(displayWidth/gameDimensions.x, displayHeight/gameDimensions.y);
        //Scale
        s = Math.min(screenScale.x, screenScale.y);
        //Translate
        t = new Vector(-topLeft.x * s, -topLeft.y * s);
        //Buffer (bars)
        b = new Vector(displayWidth/2 - (s*gameDimensions.x/2), displayHeight/2 - (s*gameDimensions.y/2));

        background(0, 0, 0);

        fill(200);
        noStroke();
        rect(b.x, b.y, s*gameDimensions.x, s*gameDimensions.y);

        currentScene.getEntities().stream()
                .sorted((e1, e2) -> {
                    int layer1 = e1.get(LayerComponent.class).orElse(new LayerComponent(e1, 0)).layer;
                    int layer2 = e2.get(LayerComponent.class).orElse(new LayerComponent(e1, 0)).layer;

                    return layer1 - layer2;
                }).forEach((e) -> {

                    drawSprites(e);

                    if (mask == 2) {
                        drawAABB(e);

                        drawSensors(e);
                    }
                }
        );
    }

    private void drawSensors(GameEntity e){
        Optional<SensorComponent> osc = e.get(SensorComponent.class);

        if(!osc.isPresent()) return;

        SensorComponent sc = osc.get();

        for(Sensor sensor : sc.getSensors()) {

            AABBCollider ab = sensor.collider.getAABBBoundingBox();

            Vector loc = new Vector((e.getTransform().x + ab.getMin().x) * s + t.x + b.x, (e.getTransform().y + ab.getMin().y) * s + t.y + b.y);

            stroke(0, 255, 0);
            noFill();
            rect(loc.x, loc.y, ab.getDimension().x * s, ab.getDimension().y * s);
        }
    }

    private void drawAABB(GameEntity e){
        Optional<ColliderComponent> occ = e.get(ColliderComponent.class);

        if(!occ.isPresent()) return;

        AABBCollider ab = occ.get().getCollider().getAABBBoundingBox();

        Vector loc = new Vector((e.getTransform().x + ab.getMin().x)*s + t.x + b.x, (e.getTransform().y + ab.getMin().y)*s + t.y + b.y);

        stroke(255,0,0);
        noFill();
        rect(loc.x, loc.y, ab.getDimension().x * s, ab.getDimension().y * s);
    }

    private void drawSprites(GameEntity e){
        Optional<SpriteComponent> osc = e.get(SpriteComponent.class);

        if(!osc.isPresent()) return;

        for(Sprite s : osc.get().getSprites()){
            drawSprite(s, e.getTransform());
        }
    }

    private void drawSprite(Sprite sprite, Vector entityTransform){
        Vector loc = new Vector((entityTransform.x + sprite.transform.x)*s + t.x + b.x, (entityTransform.y + sprite.transform.y)*s + t.y + b.y);
        if(sprite.hasDimension()) {
            Vector dimension = new Vector(sprite.dimensions.x * s, sprite.dimensions.y * s);
            image(AssetHandler.getImage(sprite.resourceID.id), loc.x, loc.y, dimension.x, dimension.y);
        }else{
            image(AssetHandler.getImage(sprite.resourceID.id), loc.x, loc.y);
        }
    }

    /**
     * Draws a rectangle of the specified values passed in. This is called in the draw() method to visualise the
     * bounding box of the entities for collision.
     * @param x float value of x position
     * @param y float value of y position
     * @param width float value of rectangle width
     * @param height float value of rectangle height
     */
    private void drawRect(float x, float y, float width, float height){
        stroke(255,0,0);
        noFill();
        rect(x*s + t.x, y*s + t.y, width * s, height * s);
    }

    /**
     * Draws a rectangle of the specified values passed in. This is called in the draw() method to visualise the
     * box of the sensors.
     * @param x value of x position
     * @param y value of y position
     * @param width rectangle width
     * @param height rectangle height
     */
    private void drawSensor(float x, float y, float width, float height){
        strokeWeight(2); //thicker line to make sensor box more visible
        stroke(0,255,0);
        noFill();
        rect(x*s + t.x, y*s + t.y, width * s, height * s);
        strokeWeight(1); //set back to thin line for bounding boxes
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
     * Passes the mouse press event to all mouse listeners.
     */
    @Override
    public void mousePressed() {
        for (MouseListener mL : mouseListeners)
            mL.setMousePressed(true, super.mouseButton);
    }

    /**
     * Passes the mouse release event to all mouse listeners.
     */
    @Override
    public void mouseReleased() {
        for (MouseListener mL : mouseListeners)
            mL.setMousePressed(false, super.mouseButton);
    }

    /**
     * Passes the key press event to all key listeners.
     */
    @Override
    public void keyPressed() {
        for (KeyListener kL : keyListeners)
            kL.setKeyPressed(true, super.keyCode);
    }

    /**
     * Passes the key release event to all key listeners.
     */
    @Override
    public void keyReleased() {
        for (KeyListener kL : keyListeners)
            kL.setKeyPressed(false, super.keyCode);
    }
}
