package ivara.scenes.boots;

import ivara.entities.PlayerEntity;
import ivara.scenes.Level;

public class MovementSpeed3 extends Level {

    public MovementSpeed3() {
        super(
                "Manic Movements",
                "By the end of the southern path Pablo had been using the boots so much he was starting to loose control. It was like they had a mind of there own powering forwards every step. Pablo was concerned, but was he going to stop? No!",
                "Pablo now has boosted walking speed and slightly faster sprint speed then before.",
                120, 60, 40
        );
    }

    @Override
    public void updateRewards() {
        PlayerEntity.setItemFlag("boots-sprint", 1f);
        PlayerEntity.setItemFlag("boots-sprint-multiplier", 1.5f);
        PlayerEntity.setItemFlag("boots-walk-multiplier", 1.1f);
    }

    @Override
    public void initialize() {

    }
}
