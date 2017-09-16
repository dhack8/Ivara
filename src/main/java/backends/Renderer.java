package backends;

import core.scene.Scene;

/**
 * Created by Callum Li on 9/16/17.
 */
public interface Renderer {
    int SPRITE = 1;
    int COLLIDIER = 2;

    void setMask(int mask);

    void render(Scene scene);

}
