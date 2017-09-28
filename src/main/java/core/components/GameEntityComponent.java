package core.components;

import core.entity.GameEntity;
import eem.Component;

abstract class GameEntityComponent extends Component<GameEntity> {

    /**
     * Constructs a new component with the given entity as it's
     * parent.
     *
     * @param entity
     */
    GameEntityComponent(GameEntity entity) {
        super(entity);
    }
}
