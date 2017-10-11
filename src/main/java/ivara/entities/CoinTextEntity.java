package ivara.entities;

import core.Script;
import core.components.RenderComponent;
import core.components.ScriptComponent;
import core.components.TextComponent;
import core.entity.GameEntity;
import core.struct.Text;
import maths.Vector;

/**
 * Created by Callum Li on 10/12/17.
 */
public class CoinTextEntity extends GameEntity {

    private final TextComponent score;

    public CoinTextEntity(Vector transform, PlayerEntity player) {
        super(transform);

        score = new TextComponent(this, new Text(16, "0"));

        addComponent(score);

        addComponent(new ScriptComponent(
                this,
                new Script() {
                    @Override
                    public void update(int dt, GameEntity entity) {
                        score.clear();
                        score.add(Integer.toString(player.coinsCollected), 16);
                    }
                }));

        addComponent(new RenderComponent(this, 10000, RenderComponent.Mode.NO_TRANS));
    }
}
