package core;

import core.input.InputHandler;
import processing.core.PApplet;

/**
 * Created by Callum Li on 9/14/17.
 */
public abstract class Game extends PApplet {

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
