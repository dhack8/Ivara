package backends.processing;

import backends.InputBroadcaster;
import backends.Renderer;
import core.AssetHandler;
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
    private AssetHandler handler;

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

    /**
     * Draws a sprite in the given location.
     * @param x x location
     * @param y y location
     * @param width width
     * @param height height
     * @param sprite the sprite to draw
     */
    private void drawSprite(int x, int y, int width, int height, PSpriteComponent sprite){
        image(AssetHandler.getImage(sprite.getResourceID()), x, y, width, height);
    }

    @Override
    public void mousePressed(){
        background(64);
    }
}
