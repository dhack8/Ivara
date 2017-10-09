package core.components;

import core.entity.GameEntity;
import core.input.InputHandler;
import scew.Component;

/**
 * Created by Callum Li on 10/1/17.
 * @deprecated Input Handling is now built into the game
 */
public class InputHandlerComponent extends Component<GameEntity> {

    private InputHandler input = new InputHandler();

    /**
     * Constructs a new component with the given entity as it's
     * parent.
     *
     * @param entity
     */
    public InputHandlerComponent(GameEntity entity) {
        super(entity);
    }

    public InputHandler getInput() {
        return input;
    }

    public void setInput(InputHandler input) {
        this.input = input;
    }
}
