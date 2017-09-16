package pxljam;

import core.scene.Entity;
import maths.Vector;

/**
 * Created by Callum Li on 9/16/17.
 */
public class TestEntity extends Entity {

    private TestScript testScript = new TestScript(this);

    public TestEntity(Vector position) {
        super(position);
    }

}
