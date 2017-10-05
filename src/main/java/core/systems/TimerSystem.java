package core.systems;

import core.entity.GameEntity;
import core.struct.Timer;
import scew.System;
import scew.World;

import java.util.HashSet;
import java.util.Set;

public class TimerSystem extends System<GameEntity> {

    private Set<Timer> timers = new HashSet<>();

    public void addTimer(Timer timer) {
        timers.add(timer);
    }

    @Override
    public void update(int dt, World<GameEntity> world) {
        Set<Timer> finishedTimers = new HashSet<>();

        for (Timer timer : timers) {
            timer.update(dt);
            if (timer.isFinished()) {
                finishedTimers.add(timer);
            }
        }

        timers.removeAll(finishedTimers);

        for (Timer timer : finishedTimers) {
            timer.performAction();
        }
    }
}
