package core.components;

import core.entity.GameEntity;
import eem.Component;

/**
 * Layer component gives a entity a layer for renderering.
 *
 * @author David Hack
 */
public class LayerComponent extends Component<GameEntity> {

    private int layer;

    public int getLayer() {
        return layer;
    }

    /**
     * Creates a layer component with an int value
     * @param entity parent entity
     * @param layer layer of entity
     */
    public LayerComponent(GameEntity entity, int layer) {
        super(entity);
        this.layer = layer;
    }
}
