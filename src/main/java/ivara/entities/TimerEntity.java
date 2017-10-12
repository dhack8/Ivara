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

    public TimerEntity(Vector transform, int time, float textSize){
        super(transform);

        TextComponent tc = new TextComponent(this);
        tc.add("00:00.00", textSize);
        addComponent(tc);

        addComponent(new SpriteComponent(this, new Sprite(new ResourceID("timer"), new Vector(-1.4f,-0.88f), null)));

        addComponent(new RenderComponent(this, 100, RenderComponent.Mode.NO_TRANS));

        addComponent(new ScriptComponent(this, new TimerScript(time, textSize)));
    }
}
