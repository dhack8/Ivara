package ivara.scenes;

import ivara.entities.PlayerEntity;

public class StubLevel extends Level {

    public StubLevel(PlayerEntity playerEntity) {
        super(
                playerEntity,
                "Under Construction",
                "Woah hold your horses, the path is blocked! Check back for new chapters in Pablo's adventures.",
                "You wish.",
                999, 999, 999
        );
    }

    @Override
    public void updateRewards() {

    }
}
