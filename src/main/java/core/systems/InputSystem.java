package core.systems;

import core.components.InputHandlerComponent;
import core.entity.GameEntity;
import core.input.InputHandler;
import scew.System;
import scew.World;

/**
 * Created by Callum Li on 10/1/17.
 */
public class InputSystem extends System<GameEntity> {

    private InputHandler handler;

    public InputSystem(InputHandler handler) {
        this.handler = handler;
    }

    @Override
    public void update(int dt, World<GameEntity> world) {
        world.get(InputHandlerComponent.class).stream()
                .forEach((inputHandlerComponent -> inputHandlerComponent.setInput(handler)));
    }
}
