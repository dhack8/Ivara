package ivara.scenes.boots;

import ivara.entities.PlayerEntity;
import ivara.scenes.Level;

public class DoubleJump3 extends Level {

    public DoubleJump3() {
        super(
                "Double & Beyond",
                "At the end of the northern path, Pablo discovered an enormous alien facility. Outside the facility, there were large obstacle courses. These were set up to test another advanced feature of the boots. Pablo travelled towards the back of the facility. As he moved around he discovered a trash heap of discarded boot augmentations. Pablo's boots now have bigger batteries.",
                "Pablo's rocket boots can now provide a third small jump.",
                120, 60, 40
        );
    }

    @Override
    public void updateRewards() {
        PlayerEntity.setItemFlag("boots-collected", 1f);
        PlayerEntity.setItemFlag("boots-num-additional-jumps", 2f);
    }

    @Override
    public void initialize() {

    }
}
