package core.struct;

import physics.Collider;

/**
 * Sensor struct containing a collider
 * @author Callum Li
 */
public class Sensor {

    public final Collider collider;

    /**
     * Creates a sensor with the given collider as its activation
     * zone.
     * @param collider The collider.
     */
    public Sensor(Collider collider) {
        this.collider = collider;
    }
}
