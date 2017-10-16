package core.systems;

import core.entity.GameEntity;
import core.struct.Timer;
import scew.System;
import scew.World;

import java.util.HashSet;
import java.util.Set;

/**
 * This system maintains the timers.
 * @author Callum Li
 */
public class TimerSystem extends System<GameEntity> {

    private Set<Timer> timers = new HashSet<>();

    public void addTimer(Timer timer) {
        timers.add(timer);
    }

    /**
     * Ticks the timers and performs their actions if they are finished.
     * @param dt time passed
     * @param world not used
     */
    @Override
    public void update(int dt, World<GameEntity> world) {
        // Prevent asynchronous modification.
        Set<Timer> finishedTimers = new HashSet<>();
        Set<Timer> runningTimers = new HashSet<>(timers);

        // Update timers.
        for (Timer timer : runningTimers) {
            timer.update(dt);
            if (timer.isFinished()) {
                finishedTimers.add(timer);
            }
        }

        // Remove finished timers and perform actions.
        timers.removeAll(finishedTimers);

        for (Timer timer : finishedTimers) {
            timer.performAction();
        }
    }
}
