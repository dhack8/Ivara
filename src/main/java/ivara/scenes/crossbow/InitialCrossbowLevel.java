package ivara.scenes.crossbow;

import ivara.scenes.Level;

public class InitialCrossbowLevel extends Level {

    public InitialCrossbowLevel() {
        super(
                "Crossbow Conquest",
                "Pablo started his exploration of the western island here. Pablo was interested in the socially advanced nation. Yet for some reason it was infested with slimes and other nasty creatures... Pablo would squash them all! Or would he use the crossbow he found as he entered the grassy island?",
                "Pablo now has a crossbow which he can only use stationary. Pablo can only carry 2 bolts per level.",
                120, 60, 40
        );
    }

    @Override
    public void updateRewards() {

    }

    @Override
    public void initialize() {

    }
}
