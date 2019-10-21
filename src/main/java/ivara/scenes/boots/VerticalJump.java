package ivara.scenes.boots;

import ivara.scenes.Level;

public class VerticalJump extends Level {

    public VerticalJump() {
        super(
                "Vertical Jump Vagabond",
                "After continuing his journey through the strange alien land Pablo discovered another alien lab. The lab has very tall buildings, surrounded by large cliffs. Almost as if the aliens had been testing something... After exploring the facility Pablo discovered a firmware upgrade for the alien boots.",
                "If Pablo stands completely stationary he gets a large boost to his jump height.",
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
