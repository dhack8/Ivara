package ivara.entities.ui;

import core.components.RenderComponent;
import core.components.TextComponent;
import core.entity.GameEntity;
import core.struct.Text;
import maths.Vector;

/**
 * This Entity displays a specified text object in the scene.
 * @author David Hack
 */
public class BasicTextEntity extends GameEntity{

    /**
     * Constructs a textEntity with a given location and text.
     * @param transform The position of the text.
     * @param t The text object to display.
     */
    public BasicTextEntity(Vector transform, Text t){
        super(transform);
        addComponent(new TextComponent(this, t));
        addComponent(new RenderComponent(this, 999999991));
    }

}
