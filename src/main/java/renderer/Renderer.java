package renderer;

import core.Game;
import core.input.InputBroadcaster;
import core.input.KeyListener;
import core.input.MouseListener;
import core.scene.Camera;
import core.scene.Entity;
import core.scene.Scene;
import maths.Vector;
import processing.core.PApplet;
import processing.core.PFont;
import pxljam.TheLegend27;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A class for drawing the game.
 *
 * @author David Hack
 */
public class Renderer extends PApplet implements InputBroadcaster {

    public static final int intWidth = 1800;
    public static final int intHeight = 900;

    Game game;

    private List<KeyListener> keyListeners = new ArrayList<>();
    private List<MouseListener> mouseListeners = new ArrayList<>();

    /**
     * Constructs a new renderer this is called first and sets up the game.
     */
    public Renderer(){
        game = new TheLegend27();
    }

    /**
     * A method to set some PApplet settings before drawing.
     */
    @Override
    public void settings() {
        size(intWidth, intHeight);
    }

    /**
     * A method to setup some settings for the PApplet before drawing.
     */
    @Override
    public void setup() {
        super.setup();
        frameRate(60);
    }

    /**
     * The main draw loop this should NOT be connected to the games tick in anyway.
     */
    @Override
    public void draw() {
        renderScene(game.getScene());
    }

    /**
     * Takes a scene from the game and draws it with the camera scaling.
     * @param scene
     */
    private void renderScene(Scene scene){
        if(scene == null){
            textSize(40);
            fill(255,0,0);
            text("The scene provided to the renderer is NULL!", 10, 40);
            return;
        }

        Collection<Entity> entities = new ArrayList<>();

        Camera camera = scene.getCamera();
        Vector cameraLoc = camera.getLocation();

        scale(camera.getScale());
        translate(-cameraLoc.x, -cameraLoc.y);

        for(Entity e : entities){
            //Collection<PSpriteComponent> sprite = e.getComponents(PSpriteComponent.class);

            Vector position = e.getPosition();

            color(0,0,0);
            rect(position.x, position.y, 1, 1);
        }
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
