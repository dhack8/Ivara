package core.components;

import core.entity.Entity;

/**
 * Layer component gives a entity a layer for renderering.
 *
 * @author David Hack
 */
public class LayerComponent extends Component{

    int layer;

    /**
     * Creates a layer component with a int values
     * @param entity parent entity
     * @param layer layer of entity
     */
    public LayerComponent(Entity entity, int layer) {
        super(entity);
        this.layer = layer;
    }
}
