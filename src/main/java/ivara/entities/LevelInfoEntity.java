package ivara.entities;

import core.components.SpriteComponent;
import core.components.TextComponent;
import core.entity.GameEntity;
import core.struct.ResourceID;
import core.struct.Sprite;
import ivara.scenes.Level;
import maths.Vector;

public class LevelInfoEntity extends GameEntity {

    public static final Vector MEDAL_LABEL_LOCATION = new Vector(0.6f, 1.4f);
    public static final Vector BRONZE_TIME_LOCATION = new Vector(2.5f, 1.48f);
    public static final Vector SILVER_TIME_LOCATION = new Vector(5f, 1.48f);
    public static final Vector GOLD_TIME_LOCATION = new Vector(7.5f, 1.48f);
    public static final int MEDAL_FONT_SIZE = 30;

    Level currentLevel = null;

    public LevelInfoEntity(Vector transform) {
        super(transform);

        // Sprite
        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new Sprite(new ResourceID("level-info-none"), new Vector(0,0), null));
        addComponent(sc);
    }

    public void displayLevel(Level level) {
        currentLevel = level;
        TextComponent tc = new TextComponent(this);

        tc.add(MEDAL_LABEL_LOCATION, "Medal Times:", MEDAL_FONT_SIZE/1.5f);
        tc.add(BRONZE_TIME_LOCATION, String.valueOf(level.getBronzeTime()) + 's', MEDAL_FONT_SIZE);
        tc.add(SILVER_TIME_LOCATION, String.valueOf(level.getSilverTime()) + 's', MEDAL_FONT_SIZE);
        tc.add(GOLD_TIME_LOCATION, String.valueOf(level.getGoldTime()) + 's', MEDAL_FONT_SIZE);



        addComponent(tc);
    }
}
