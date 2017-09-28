package core.components;

import core.entity.GameEntity;

/**
 * Layer component gives a entity a layer for renderering.
 *
 * @author David Hack
 */
public class LayerComponent extends Component{

    private int layer;

    /**
     * Creates a layer component with an int value
     * @param entity parent entity
     * @param layer layer of entity
     */
    public LayerComponent(GameEntity entity, int layer) {
        super(entity);
        this.layer = layer;
    }

    public int getLayer() {
        return layer;
    }
}
