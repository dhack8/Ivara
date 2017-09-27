package ivara;

import core.scene.Scene;
import maths.Vector;

/**
 * Created by Callum Li on 9/16/17.
 */
public class TestScene extends Scene {

    public TestScene() {
        addEntity(new TestEntity(new Vector(10, 10)));
        addEntity(new TestEntity2(new Vector(20, 20)));
    }
}
