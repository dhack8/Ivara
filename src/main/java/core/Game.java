package core;

import core.input.InputHandler;
import processing.core.PApplet;

/**
 * Created by Callum Li on 9/14/17.
 */
public abstract class Game extends PApplet {

    @Override
    public void setup() {
        super.setup();
    }

    @Override
    public void settings() {
        size(600, 660);
    }

    @Override
    public void draw() {
        super.draw();
        rect(20, 20, 100, 100);
    }

    //----------------------Input Handler---------------------

    @Override
    public void mousePressed() {
        InputHandler.setMousePressed(true, mouseButton);
    }

    @Override
    public void mouseReleased() {
        InputHandler.setMousePressed(false, mouseButton);
    }

    @Override
    public void keyPressed() {
        InputHandler.setKeyPressed(true, keyCode);
    }

    @Override
    public void keyReleased() {
        InputHandler.setKeyPressed(false, keyCode);
    }
}
