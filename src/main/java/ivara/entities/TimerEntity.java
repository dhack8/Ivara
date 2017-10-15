package ivara.entities;

import core.components.RenderComponent;
import core.components.ScriptComponent;
import core.components.SpriteComponent;
import core.components.TextComponent;
import core.entity.GameEntity;
import core.struct.ResourceID;
import core.struct.Sprite;
import ivara.entities.scripts.TimerScript;
import maths.Vector;

public class
TimerEntity extends GameEntity {

    private static final float TEXTSIZE = 25;
    private static final Vector offset = new Vector(-67.5f,-44.5f);

    public TimerEntity(Vector transform, int time){
        super(transform);

        TextComponent tc = new TextComponent(this);
        tc.add("00:00.00", TEXTSIZE);
        addComponent(tc);

        addComponent(new SpriteComponent(this, new Sprite(new ResourceID("timer"), offset, null)));

        addComponent(new RenderComponent(this, 999999999, RenderComponent.Mode.PIXEL_NO_TRANS));

        addComponent(new ScriptComponent(this, new TimerScript(time, TEXTSIZE)));
    }
}
