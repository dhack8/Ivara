package core.components;

import core.entity.GameEntity;
import core.input.InputHandler;
import scew.Component;

/**
 * Created by Callum Li on 10/1/17.
 */
public class InputComponent extends Component<GameEntity> {

    public InputHandler input = new InputHandler();

    /**
     * Constructs a new component with the given entity as it's
     * parent.
     *
     * @param entity
     */
    public InputComponent(GameEntity entity) {
        super(entity);
    }
}
