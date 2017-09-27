package backends.processing;

import backends.InputBroadcaster;
import backends.Renderer;
import core.AssetHandler;
import core.components.*;
import core.input.KeyListener;
import core.input.MouseListener;
import core.entity.Entity;
import core.scene.Scene;
import maths.Vector;
import physics.AABBCollider;
import physics.Collider;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

/**
 * Window that the game runs in. Extends the Processing window PApplet.
 * Created by Callum Li on 9/16/17.
 */
public class PWindow extends PApplet implements InputBroadcaster, Renderer{

    private Scene currentScene;
    private AssetHandler handler;
    private int mask = 2;

    private float scale = 100;
    private Vector t = new Vector(0,0); //translation

    private List<KeyListener> keyListeners = new ArrayList<>();
    private List<MouseListener> mouseListeners = new ArrayList<>();

    private BasicCameraComponent camera;

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
        AssetHandler.loadImage("./assets/slime.png", "slime", this);
    }

    /**
     * Draws all the entities, along with their assigned sprites within the scene. Calls the drawRect() method to
     * visualise the bounding box of the colliding entities. Checks if the sprite is dimensionless or not, to call the
     * correct draw function.
     */
    @Override
    public void draw(){
        if(currentScene == null) return;
        currentScene.setDrawing(true);
            camera = currentScene.getCamera();

            scale = displayWidth / camera.getWidth();

            Vector cameraPos = camera.getPointOfInterest();

            t = new Vector(-cameraPos.x * scale + displayWidth / 3, -cameraPos.y * scale + displayHeight / 2);

            background(220, 220, 220);

            currentScene.getEntities().stream()
                    .sorted((e1, e2) -> {
                        int layer1 = e1.getComponents(LayerComponent.class).stream().findAny().orElse(new LayerComponent(e1, 0)).getLayer();
                        int layer2 = e2.getComponents(LayerComponent.class).stream().findAny().orElse(new LayerComponent(e1, 0)).getLayer();

                        return layer1 - layer2;
                    }).forEach((e) -> {
                        for (PSpriteComponent spriteComponent : e.getComponents(PSpriteComponent.class)) {
                            // TODO: render sprite based on scene camera

                            Vector transform = spriteComponent.getTransform();

                            if (spriteComponent.isDimensionless()) {
                                drawSprite(e.getPosition().x + transform.x, e.getPosition().y + transform.y, spriteComponent);
                            } else {
                                Vector dimen = spriteComponent.getDimensions();
                                drawSprite(e.getPosition().x + transform.x, e.getPosition().y + transform.y, dimen.x, dimen.y, spriteComponent);
                            }
                        }
                        //TODO point of mask?
                        if (mask == 2) {
                            for (ColliderComponent cc : e.getComponents(ColliderComponent.class)) {
                                AABBCollider ab = cc.getCollider().getAABBBoundingBox();
                                Vector location = ab.getMin();
                                Vector dimension = ab.getDimension();
                                drawRect(location.x, location.y, dimension.x, dimension.y);
                            }

                            for (SensorComponent sc : e.getComponents(SensorComponent.class)) {
                                AABBCollider ab = sc.getCollider().getAABBBoundingBox();
                                Vector location = ab.getMin();
                                Vector dimension = ab.getDimension();
                                drawSensor(location.x, location.y, dimension.x, dimension.y);
                            }
                        }
                    }
            );
            currentScene.setDrawing(false);
    }

    /**
     * Draws a sprite in the given location.
     * @param x x location
     * @param y y location
     * @param width width
     * @param height height
     * @param sprite the sprite to draw
     */
    private void drawSprite(float x, float y, float width, float height, PSpriteComponent sprite){
        image(AssetHandler.getImage(sprite.getResourceID()), x*scale + t.x, y*scale + t.y, width*scale, height*scale);
    }

    /**
     * Draws a sprite in the given location at its native resolution, without specifying a width and height.
     * @param x x location
     * @param y y location
     * @param sprite the sprite to draw
     */
    private void drawSprite(float x, float y, PSpriteComponent sprite){
        image(AssetHandler.getImage(sprite.getResourceID()), x*scale + t.x, y*scale + t.y);
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
        rect(x*scale + t.x, y*scale + t.y, width * scale, height * scale);
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
        rect(x*scale + t.x, y*scale + t.y, width * scale, height * scale);
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
