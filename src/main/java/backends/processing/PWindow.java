package backends.processing;

import backends.InputBroadcaster;
import backends.Renderer;
import core.components.PSpriteComponent;
import core.input.KeyListener;
import core.input.MouseListener;
import core.scene.Entity;
import core.scene.Scene;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Callum Li on 9/16/17.
 */
public class PWindow extends PApplet implements InputBroadcaster, Renderer{

    private Scene currentScene;

    private List<KeyListener> keyListeners = new ArrayList<>();
    private List<MouseListener> mouseListeners = new ArrayList<>();

    @Override
    public void setMask(int mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void render(Scene scene) {
        currentScene = scene;
        redraw();
    }

    @Override
    public void settings(){
        size(500, 500);
        noLoop();
    }

    @Override
    public void draw(){
        for (Entity e : currentScene.getEntities()) {
            for (PSpriteComponent spriteComponent : e.getComponents(PSpriteComponent.class)) {

                // todo: render sprite based on scene camera
                rect(e.getPosition().x, e.getPosition().y, 10, 10);
            }

            rect(e.getPosition().x, e.getPosition().y, 10, 10);
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
