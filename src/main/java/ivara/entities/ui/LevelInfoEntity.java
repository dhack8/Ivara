package ivara.entities.ui;

import core.components.SpriteComponent;
import core.components.TextComponent;
import core.entity.GameEntity;
import core.struct.AnimatedSprite;
import core.struct.ResourceID;
import core.struct.Sprite;
import core.struct.Text;
import ivara.scenes.HighscoreScene;
import ivara.scenes.Level;
import maths.Vector;

import java.util.Arrays;

import static ivara.scenes.Level.*;

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

    public static final Vector TITLE_LOCATION = new Vector(1.15f, 4f);
    public static final Vector TITLE_DIMEN = new Vector(7.2f, 1f);
    private static final int TITLE_FONT_SIZE = 50;

    public static final Vector DESCRIPTION_LOCATION = new Vector(1.15f, 5f);
    public static final Vector DESCRIPTION_DIMEN = new Vector(7.2f, 3.375f);
    private static final int DESCRIPTION_FONT_SIZE = 20;

    public static final Vector REWARD_TITLE_LOCATION = new Vector(1.4f, 9.125f);
    private static final int REWARD_TITLE_FONT_SIZE = 40;

    public static final Vector REWARD_DESC_LOCATION = new Vector(1.4375f, 9.525f);
    public static final Vector REWARD_DESC_DIMEN = new Vector(6.625f, 1.725f);
    private static final int REWARD_DESC_FONT_SIZE = 20;

    public static final Vector WARNING_LOCATION = new Vector(2.95f, 11.8f);
    private static final int WARNING_FONT_SIZE = 20;

    private static final int LABEL_FONT_SIZE = 20;

    Level currentLevel = null;
    AnimatedSprite infoSprite;
    ButtonEntity playButton;
    ButtonEntity highscore;

    TextComponent text;

    public LevelInfoEntity(Vector transform, ButtonEntity playButton, ButtonEntity highscore) {
        super(transform);

        this.playButton = playButton;
        this.playButton.setActive(false);
        this.highscore = highscore;
        this.highscore.setActive(false);

        // Text
        text = new TextComponent(this);
        text.add(new Text(TITLE_LOCATION, TITLE_DIMEN, TITLE_FONT_SIZE, "Pablo's House"));
        text.add(new Text(DESCRIPTION_LOCATION, TITLE_DIMEN, DESCRIPTION_FONT_SIZE, "Click on a level icon to start Pablo's adventures"));
        addComponent(text);

        // Sprite
        SpriteComponent sc = new SpriteComponent(this);
        infoSprite = new LevelInfoSprite(new Vector(0,0), new Vector(9.5f, 15f), 200);
        infoSprite.setState(NOTHING);
        sc.add(infoSprite);
        addComponent(sc);
    }

    public void displayLevel(Level level, boolean canPlay) {
        currentLevel = level;
        text.clear();

        text.add(MEDAL_LABEL_LOCATION, "Medal Times:", LABEL_FONT_SIZE);
        text.add(BRONZE_TIME_LOCATION, String.valueOf(level.getBronzeTime()) + 's', MEDAL_FONT_SIZE);
        text.add(SILVER_TIME_LOCATION, String.valueOf(level.getSilverTime()) + 's', MEDAL_FONT_SIZE);
        text.add(GOLD_TIME_LOCATION, String.valueOf(level.getGoldTime()) + 's', MEDAL_FONT_SIZE);

        text.add(BEST_TIME_LABEL_LOCATION, "Your Best Time", LABEL_FONT_SIZE);
        text.add(BEST_TIME_LOCATION, formatTime(level), BEST_TIME_FONT_SIZE);

        text.add(COIN_LABEL_LOCATION, "Coins Collected", LABEL_FONT_SIZE);
        text.add(COIN_LOCATION, formatCoins(level), COIN_FONT_SIZE);

        text.add(new Text(TITLE_LOCATION, TITLE_DIMEN, TITLE_FONT_SIZE, level.getTitle()));
        text.add(new Text(DESCRIPTION_LOCATION, DESCRIPTION_DIMEN, DESCRIPTION_FONT_SIZE, level.getDescription()));

        text.add(REWARD_TITLE_LOCATION, "Rewards", REWARD_TITLE_FONT_SIZE);
        text.add(new Text(REWARD_DESC_LOCATION, REWARD_DESC_DIMEN, REWARD_DESC_FONT_SIZE, level.getRewardDescription()));

        if(!canPlay) text.add(WARNING_LOCATION, "Complete levels up to this level to play", WARNING_FONT_SIZE);

        infoSprite.setState(level.getMedalLevel());
        playButton.setActive(canPlay);
        highscore.setActive(canPlay);
    }

    public void refresh(boolean canPlay) {
        displayLevel(currentLevel, canPlay);
    }

    public void playLevel() {
        currentLevel.resetScene();
        getScene().getGame().getLevelManager().setScene(currentLevel);
    }

    public void displayHighscore(){
        getScene().getGame().getLevelManager().setScene(new HighscoreScene(currentLevel.getTitle()));
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

    public class LevelInfoSprite extends AnimatedSprite {

        /**
         * Constructor does not take any resource ID upon creation as you need
         * to define the map resource map structure that the sprite will use. This
         * is done with the addResource() method.
         *
         * @param transform  The animated sprite's relative position.
         * @param dimensions The width and height of the animated sprite.
         * @param frameTick  The time taken before the image should switch.
         */
        public LevelInfoSprite(Vector transform, Vector dimensions, int frameTick) {
            super(transform, dimensions, frameTick);
            addInfoState(NOTHING);
            addInfoState(BRONZE);
            addInfoState(SILVER);
            addInfoState(GOLD);
        }

        private void addInfoState(String state) {
            String[] resources = new String[] {
                    "level-info-" + state
            };
            addResources(state, Arrays.asList(resources));
        }
    }
}
