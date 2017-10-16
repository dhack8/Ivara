package scew;

/**
 * Abstract class that outlines the requirements a
 * System must fulfil.
 *
 * Systems are responsible for updating all a given
 * world.
 * @param <T>
 */
public abstract class System<T extends Entity> {

    /**
     * Update call to system so it may perform its role.
     * @param dt time passed
     * @param world the game world
     */
    public abstract void update(int dt, World<T> world);
}
