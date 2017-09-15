package core;

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


}
