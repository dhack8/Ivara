package core.scene;

import maths.Vector;

/**
 * Created by Callum Li on 9/15/17.
 */
public abstract class Entity {

    Vector position;

    void translate(float dx, float dy){
        position.add(dx, dy);
    }
}
