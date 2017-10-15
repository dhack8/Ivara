package core.struct;

/**
 * Timer struct for the timer system.
 * @author Callum Li
 */
public class Timer {
    /**
     * Current elapsed time in milliseconds
     */
    private int current = 0;
    private final int length;
    private final Runnable callback;

    /**
     * Constructs a timer with the given length and action.
     * @param length The timer's length in milliseconds
     * @param action The action to execution once the timer is finished.
     */
    public Timer(int length, Runnable action) {
        this.length = length;
        this.callback = action;
    }

    /**
     * Returns true if the amount of elapsed time has been
     * exceeded.
     * @return
     */
    public boolean isFinished() {
        return current >= length;
    }

    /**
     * Performs the actions specified in the constructor.
     */
    public void performAction() {
        callback.run();
    }

    /**
     * Elapses the given amount of time for this timer.
     * @param delta The amount of time to elapse.
     */
    public void update(int delta) {
        current += delta;
    }
}
