package core.renderer;

import core.Game;
import core.components.SpriteComponent;
import core.input.InputBroadcaster;
import core.input.InputHandler;
import core.input.KeyListener;
import core.input.MouseListener;
import core.scene.Camera;
import core.scene.Entity;
import core.scene.Scene;
import maths.Vector;
import processing.core.PApplet;

import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Class that will render the given scene when asked to.
 * Also processes input and passes it to it's listeners.
 *
 * @author David Hack
 */
public class PWindow extends PApplet implements Renderer, InputBroadcaster{

    private Game game;
    private List<KeyListener> keyListeners = new ArrayList<>();
    private List<MouseListener> mouseListeners = new ArrayList<>();

    public PWindow(Game g) {
        game = g;
    }

    @Override
    public void settings(){
        size(1800, 900);
        noLoop();
    }

    public void render(Scene scene){
        Collection<Entity> entities = new ArrayList<>();

        Camera camera = scene.getCamera();
        Vector cameraLoc = camera.getLocation();

        scale(camera.getScale());
        translate(-cameraLoc.x, -cameraLoc.y);

        for(Entity e : entities){
            //Collection<SpriteComponent> sprite = e.getComponents(SpriteComponent.class);

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
