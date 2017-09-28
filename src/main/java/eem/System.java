package eem;

/**
 * Created by Callum Li on 9/28/17.
 */
public abstract class System<T extends Entity> {

    public abstract void update(int dt, World<T> world);
}
