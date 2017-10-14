package backends;

import core.scene.Scene;
import processing.core.PApplet;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Callum Li on 9/16/17.
 */
public abstract class Renderer extends PApplet{
    int SPRITE = 1;
    int COLLIDIER = 2;

    public abstract void setMask(int mask);

    public abstract void render(Scene scene);
}
