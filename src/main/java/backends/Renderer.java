package backends;

import core.scene.Scene;
import processing.core.PApplet;

import java.util.concurrent.CountDownLatch;

/**
 * Generalization of the renderer that grantees mask setting and render call with a scene.
 */
public abstract class Renderer extends PApplet{
    public static final int SPRITE = 1;
    public static final int COLLIDIER = 2;

    /**
     * A mask method for debugging.
     * @param mask type of mask
     */
    public abstract void setMask(int mask);

    /**
     * A render method that takes a scene and draws it.
     * This process should be 'blocking'
     * @param scene the scene to draw.
     */
    public abstract void render(Scene scene);
}
