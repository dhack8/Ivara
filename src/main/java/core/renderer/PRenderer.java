package core.renderer;

import core.Game;
import core.scene.Scene;

/**
 * Class that will render the given scene when asked to.
 *
 * @author David Hack
 */
public class PRenderer {

    private Game game;
    private Scene scene;

    private void render(Scene scene, Game game) {
        this.game = game;
        this.scene = scene;
    }

    public void draw(){

    }
}
