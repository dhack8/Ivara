package ivara.entities;

import core.components.SpriteComponent;
import core.components.TextComponent;
import core.entity.GameEntity;
import core.struct.ResourceID;
import core.struct.Sprite;
import ivara.scenes.Level;
import maths.Vector;

public class LevelInfoEntity extends GameEntity {

    private static final Vector MEDAL_LABEL_LOCATION = new Vector(0.6f, 1.4f);
    private static final Vector BRONZE_TIME_LOCATION = new Vector(2.5f, 1.48f);
    private static final Vector SILVER_TIME_LOCATION = new Vector(5f, 1.48f);
    private static final Vector GOLD_TIME_LOCATION = new Vector(7.5f, 1.48f);
    private static final int MEDAL_FONT_SIZE = 30;

    private static final Vector BEST_TIME_LABEL_LOCATION  = new Vector(1.8f, 2f);
    private static final Vector BEST_TIME_LOCATION  = new Vector(2f, 2.55f);
    private static final int BEST_TIME_FONT_SIZE = 50;

    private static final Vector COIN_LABEL_LOCATION  = new Vector(6.15f, 2f);
    private static final Vector COIN_LOCATION  = new Vector(6.35f, 2.55f);
    private static final int COIN_FONT_SIZE = 50;

    private static final int LABEL_FONT_SIZE = 20;

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

        tc.add(MEDAL_LABEL_LOCATION, "Medal Times:", LABEL_FONT_SIZE);
        tc.add(BRONZE_TIME_LOCATION, String.valueOf(level.getBronzeTime()) + 's', MEDAL_FONT_SIZE);
        tc.add(SILVER_TIME_LOCATION, String.valueOf(level.getSilverTime()) + 's', MEDAL_FONT_SIZE);
        tc.add(GOLD_TIME_LOCATION, String.valueOf(level.getGoldTime()) + 's', MEDAL_FONT_SIZE);

        tc.add(BEST_TIME_LABEL_LOCATION, "Your Best Time", LABEL_FONT_SIZE);
        tc.add(BEST_TIME_LOCATION, formatTime(level), BEST_TIME_FONT_SIZE);

        tc.add(COIN_LABEL_LOCATION, "Coins Collected", LABEL_FONT_SIZE);
        tc.add(COIN_LOCATION, formatCoins(level), COIN_FONT_SIZE);

        addComponent(tc);
    }

    private String formatCoins(Level level) {
        return String.valueOf(level.getCollectedCoinCount()) + '/' + String.valueOf(level.getTotalCoinCount());
    }

    private String formatTime(Level level) {
        long millis = level.getBestTimeInMillis();
        if (millis == 0) return "Not Set";
        long seconds = (millis / 1000) % 60 ;
        long minutes = (millis / (1000*60)) % 60;
        millis = (millis % 1000)/10;
        return String.format("%02d:%02d.%02d", minutes, seconds, millis);
    }
}
