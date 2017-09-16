package backends.processing;

import backends.InputBroadcaster;
import backends.Renderer;
import core.components.PSpriteComponent;
import core.input.KeyListener;
import core.input.MouseListener;
import core.scene.Entity;
import core.scene.Scene;
import processing.core.PApplet;

/**
 * Created by Callum Li on 9/16/17.
 */
public class PWindow extends PApplet implements InputBroadcaster, Renderer{

    private Scene currentScene;

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
    public void addKeyListener(KeyListener listener) {

    }

    @Override
    public void addMouseListener(MouseListener listener) {

    }

    @Override
    public void settings(){
        size(500, 500);
        noLoop();
    }

    @Override
    public void draw(){
        System.out.println("Loop");
        for (Entity e : currentScene.getEntities()) {
            for (PSpriteComponent spriteComponent : e.getComponents(PSpriteComponent.class)) {
                rect(e.getPosition().x, e.getPosition().y, 10, 10);
            }
            rect(e.getPosition().x, e.getPosition().y, 10, 10);
        }
    }

    @Override
    public void mousePressed(){
        background(64);
    }
}
