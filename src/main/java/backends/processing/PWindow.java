package backends.processing;

import backends.InputBroadcaster;
import backends.Renderer;
import core.AssetHandler;
import core.components.PSpriteComponent;
import core.input.KeyListener;
import core.input.MouseListener;
import core.scene.Camera;
import core.scene.Entity;
import core.scene.Scene;
import maths.Vector;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

/**
 * Window that the game runs in. Extends the Processing window PApplet.
 * Created by Callum Li on 9/16/17.
 */
public class PWindow extends PApplet implements InputBroadcaster, Renderer{

    public static final int intWidth = 1800;
    public static final int intHeight = 900;

    private Scene currentScene;
    private AssetHandler handler;

    private List<KeyListener> keyListeners = new ArrayList<>();
    private List<MouseListener> mouseListeners = new ArrayList<>();

    private Camera camera;

    /**
     * TODO
     * @param mask
     */
    @Override
    public void setMask(int mask) {
        throw new UnsupportedOperationException();
    }

    /**
     * Draws the scene that is passed in.
     * @param scene current scene
     */
    @Override
    public void render(Scene scene) {
        if(scene == null){
            textSize(40);
            fill(255,0,0);
            text("The scene provided to the renderer is NULL!", 10, 40);
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
        size(intWidth, intHeight);
        noLoop();
    }

    /**
     * Processing's setup, for now loads the images to the asset handler.
     */
    @Override
    public void setup(){
        AssetHandler.loadImage("./assets/dirt.png", "dirt");
        AssetHandler.loadImage("./assets/dirt-bottom.png", "dirt-bottom");
        AssetHandler.loadImage("./assets/grass-bottom.png", "grass-bottom");
        AssetHandler.loadImage("./assets/grass-bottom-left.png", "grass-bottom-left");
        AssetHandler.loadImage("./assets/grass-bottom-right.png", "grass-bottom-right");
        AssetHandler.loadImage("./assets/grass-left.png", "grass-left");
        AssetHandler.loadImage("./assets/grass-right.png", "grass-right");
        AssetHandler.loadImage("./assets/grass-top.png", "grass-top");
        AssetHandler.loadImage("./assets/grass-top-left.png", "grass-top-left");
        AssetHandler.loadImage("./assets/grass-top-right.png", "grass-top-right");
        AssetHandler.loadImage("./assets/player.png", "player");
    }

    /**
     * Draws all the entities, along with their assigned sprites within the scene.
     */
    @Override
    public void draw(){
        background(220, 220, 220);
        camera = currentScene.getCamera();

        Vector cameraLoc = camera.getLocation();
        translate(-cameraLoc.x, -cameraLoc.y);

        for (Entity e : currentScene.getEntities()) {
            for (PSpriteComponent spriteComponent : e.getComponents(PSpriteComponent.class)) {
                // TODO: render sprite based on scene camera
                Vector dimen = spriteComponent.getDimensions();
                drawSprite(e.getPosition().x, e.getPosition().y, dimen.x, dimen.y, spriteComponent);
            }
        }
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
        float scale = camera.getScale();
        image(AssetHandler.getImage(sprite.getResourceID()), x*scale, y*scale, width*scale, height*scale);
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
