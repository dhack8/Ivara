package ivara.scenes.crossbow;

import ivara.scenes.Level;

public class InitialCrossbowLevel extends Level {

    public InitialCrossbowLevel() {
        super(
                "Crossbow Conquest",
                "Pablo starts his quest on the alien island here. Pablo gains access to advanced alien rocket boots.",
                "Pablo now has rocket boots providing a boost in jump height.",
                120, 60, 40
        );
    }

    @Override
    public void initialize() {

    }
}
