package ivara.entities;

import core.components.RenderComponent;
import core.components.TextComponent;
import core.entity.GameEntity;
import core.struct.Text;
import maths.Vector;

/**
 * Created by David Hack Local on 11-Oct-17.
 */
public class BasicTextEntity extends GameEntity{

    public BasicTextEntity(Vector transform, Text t){
        super(transform);
        addComponent(new TextComponent(this, t));
        addComponent(new RenderComponent(this, 999999991));
    }

}
