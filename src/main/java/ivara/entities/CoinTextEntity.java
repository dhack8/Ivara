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
import ivara.scenes.DefaultScene;
import maths.Vector;

/**
 * Created by Callum Li on 10/12/17.
 */
public class CoinTextEntity extends GameEntity {

    private final TextComponent score;
    private static final float TEXTSIZE = 25;
    private long coinCount = 0;
    private boolean coinCountSet = false;

    private static final Vector offset = new Vector(-45f,-31f);

    public CoinTextEntity(Vector transform, PlayerEntity player) {
        super(transform);

        score = new TextComponent(this, new Text(TEXTSIZE, "0"));

        addComponent(score);

        addComponent(new SpriteComponent(this, new Sprite(new ResourceID("coin"), offset, null)));

        addComponent(new ScriptComponent(
                this,
                new Script() {
                    @Override
                    public void update(int dt, GameEntity entity) {
                        if(!coinCountSet){
                            coinCount = getScene().getEntities().stream().filter((e) -> e instanceof CoinEntity).count();
                            if(getScene() instanceof DefaultScene){
                                DefaultScene ds = (DefaultScene) getScene();
                                coinCount += ds.getCollectedCoins().size();
                            }
                            coinCountSet = true;
                        }
                        score.clear();
                        score.add(Integer.toString(player.coinsCollected) + "/" + coinCount, TEXTSIZE);
                    }
                }));

        addComponent(new RenderComponent(this, 999999999, RenderComponent.Mode.PIXEL_NO_TRANS));
    }
}
