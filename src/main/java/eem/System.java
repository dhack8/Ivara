package eem;

/**
 * Created by Callum Li on 9/28/17.
 */
public abstract class System {
    private final World world;

    public System(World world) {
        this.world = world;
    }

    public abstract void update(int dt);
}
