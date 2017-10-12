package ivara.entities;

import core.Script;
import core.components.RenderComponent;
import core.components.ScriptComponent;
import core.components.SpriteComponent;
import core.components.TextComponent;
import core.entity.GameEntity;
import core.struct.ResourceID;
import core.struct.Sprite;
import core.struct.Text;
import maths.Vector;

/**
 * Created by Callum Li on 10/12/17.
 */
public class CoinTextEntity extends GameEntity {

    private final TextComponent score;
    private static final float TEXTSIZE = 25;

    public CoinTextEntity(Vector transform, PlayerEntity player) {
        super(transform);

        score = new TextComponent(this, new Text(TEXTSIZE, "0"));

        addComponent(score);

        addComponent(new SpriteComponent(this, new Sprite(new ResourceID("coin"), new Vector(-0.95f,-0.63f), null)));

        addComponent(new ScriptComponent(
                this,
                new Script() {
                    @Override
                    public void update(int dt, GameEntity entity) {
                        score.clear();
                        score.add(Integer.toString(player.coinsCollected), TEXTSIZE);
                    }
                }));

        addComponent(new RenderComponent(this, 10000, RenderComponent.Mode.NO_TRANS));
    }
}
