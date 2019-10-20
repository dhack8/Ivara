package ivara.entities.ui;

import core.Script;
import core.components.RenderComponent;
import core.components.ScriptComponent;
import core.components.SpriteComponent;
import core.components.TextComponent;
import core.entity.GameEntity;
import core.struct.ResourceID;
import core.struct.Sprite;
import maths.Vector;

/**
 * TimerEntity handles the creation of a timer that displays the time spent in a level.
 * @author Will Pearson
 */
public class TimerEntity extends GameEntity {

    // Constants
    private static final float TEXTSIZE = 25;
    private static final Vector OFFSET = new Vector(-67.5f,-44.5f);

    public long start;
    public long currentTime;

    public TimerEntity(Vector transform, int time){
        super(transform);

        // Text
        TextComponent tc = new TextComponent(this);
        tc.add("00:00.00", TEXTSIZE);
        addComponent(tc);

        // Sprite
        addComponent(new SpriteComponent(this, new Sprite(new ResourceID("timer"), OFFSET, null)));

        // Layer
        addComponent(new RenderComponent(this, 999999999, RenderComponent.Mode.PIXEL_NO_TRANS));

        // Script
        addComponent(new ScriptComponent(this, new TimerScript(time, TEXTSIZE)));
    }

    /**
     * The TimerScript is used to add a running timer to the game.
     * @author Will Pearson
     * @author David Hack
     */
    public class TimerScript implements Script {
        private float textSize;

        /**
         * Constructs a TimerScript with an initial time and a text size.
         * @param startTime The time of the system at creation of the TimerScript (when the level starts).
         * @param textSize The size of the text being displayed.
         */
        public TimerScript(int startTime, float textSize) {
            start = startTime;
            this.textSize = textSize;
        }

        @Override
        public void update(int dt, GameEntity entity) {
            TextComponent tc = entity.get(TextComponent.class).get();
            tc.clear();
            currentTime += dt;
            tc.add(timeString(), textSize);
        }

        /**
         * Converts the time that the timer has been running into a string.
         * @return A string representing the time.
         */
        private String timeString() {
            long millis = getTimeInMillis();
            long seconds = (millis / 1000) % 60 ;
            long minutes = (millis / (1000*60)) % 60;
            millis = (millis % 1000)/10;
            return String.format("%02d:%02d.%02d", minutes, seconds, millis);
        }
    }

    public long getTimeInMillis() {
        return currentTime - start;
    }
}
