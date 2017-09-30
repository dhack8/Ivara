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

    public abstract void update(int dt, World<T> world);
}
