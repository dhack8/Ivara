package ivara.entities.scripts;

import core.Script;
import core.components.TextComponent;
import core.entity.GameEntity;
import core.struct.Text;

public class TimerScript implements Script {

    public long start;
    public long currentTime;
    private float textSize;

    public TimerScript(int t, float s) {
        start = t;
        this.textSize = s;
    }

    @Override
    public void update(int dt, GameEntity entity) {
        TextComponent tc = entity.get(TextComponent.class).get();
        tc.clear();
        currentTime += dt;
        tc.add(timeString(), textSize);
    }

    private String timeString() {
        long millis = currentTime - start;
        long seconds = (millis / 1000) % 60 ;
        long minutes = (millis / (1000*60)) % 60;
        millis = (millis % 1000)/10;
        return String.format("%02d:%02d.%02d", minutes, seconds, millis);
    }
}
