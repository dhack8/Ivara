package ivara.scenes.boots;

import ivara.entities.PlayerEntity;
import ivara.scenes.Level;

public class DoubleJump1 extends Level {

    public DoubleJump1() {
        super(
                "Double Trouble",
                "Pablo's journey took him to the northern sector of the alien island. Here the aliens were working on a different technology from the other areas. The northern area was scattered with large ravines making travel difficult. In a thrilling series of alien challenges, the aliens reluctantly provided an augmentation to Pablo's boots.",
                "Pablo now has a double jump, weaker than the first jump.",
                120, 60, 40
        );
    }

    @Override
    public void updateRewards() {
        PlayerEntity.setItemFlag("boots-collected", 1f);
        PlayerEntity.setItemFlag("boots-num-additional-jumps", 1f);
        PlayerEntity.setItemFlag("boots-successive-jump-power", 0.6f);
    }

    @Override
    public void initialize() {

    }
}
