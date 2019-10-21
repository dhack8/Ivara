package ivara.scenes.boots;

import ivara.entities.PlayerEntity;
import ivara.scenes.Level;

public class DoubleJump2 extends Level {

    public DoubleJump2() {
        super(
                "The Double Devil",
                "Continuing his journey across the northern part of the alien island was a lot easier with the double jump. Pablo marvelled at the design of the boots as they appeared to be almost magical. As he continued he discovered a human sympathiser who helped improve the double jump feature of the boots..",
                "Pablo's double jump has improved with a more powerful second jump.",
                120, 60, 40
        );
    }

    @Override
    public void updateRewards() {
        PlayerEntity.setItemFlag("boots-collected", 1f);
        PlayerEntity.setItemFlag("boots-num-additional-jumps", 1f);
        PlayerEntity.setItemFlag("boots-successive-jump-power", 0.8f);
    }

    @Override
    public void initialize() {

    }
}
