package ivara.scenes.boots;

import ivara.entities.PlayerEntity;
import ivara.scenes.Level;

public class MovementSpeed2 extends Level {

    public MovementSpeed2() {
        super(
                "Matey Got Moves",
                "Boosting around the southern area of the alien island was giving Pablo a rush. Each step felt better then the last. Pablo was becoming more skilled at using the advanced boots.",
                "Pablo can now sprint even faster.",
                120, 60, 40
        );
    }

    @Override
    public void updateRewards() {
        PlayerEntity.setItemFlag("boots-sprint", 1f);
        PlayerEntity.setItemFlag("boots-sprint-multiplier", 1.5f);
    }

    @Override
    public void initialize() {

    }
}
