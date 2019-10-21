package ivara.scenes.boots;

import ivara.entities.PlayerEntity;
import ivara.scenes.Level;

public class MovementSpeed1 extends Level {

    public MovementSpeed1() {
        super(
                "Move It!",
                "The southern part of the island was mysterious and begging to be explored. There were large open areas barren of all life. What had the aliens done? As Pablo was exploring the southern part of the alien island he discovered a new feature of the boots. By activating a special mode the boots could boost his speed.",
                "Pablo can now sprint using the shift key.",
                120, 60, 40
        );
    }

    @Override
    public void updateRewards() {
        PlayerEntity.setItemFlag("boots-sprint", 1f);
        PlayerEntity.setItemFlag("boots-sprint-multiplier", 1.3f);
    }

    @Override
    public void initialize() {

    }
}
