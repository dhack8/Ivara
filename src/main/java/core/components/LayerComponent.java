package core.components;

import core.entity.Entity;

/**
 * Created by dhack on 17-Sep-17.
 */
public class LayerComponent extends Component{

    int layer;

    public LayerComponent(Entity entity, int layer) {
        super(entity);
        this.layer = layer;
    }
}
