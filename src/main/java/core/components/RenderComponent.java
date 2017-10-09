package core.components;

import core.entity.GameEntity;
import scew.Component;

/**
 * Layer component gives a entity a layer for rendering.
 *
 * @author David Hack
 */
public class RenderComponent extends Component<GameEntity> {

    public enum Mode{
        NORMAL,
        NO_TRANS,
        FULLSCREEN
    }

    private int layer = 0;
    private Mode mode = Mode.NORMAL;

    /**
     * Creates a layer component with an int value
     * @param entity parent entity
     * @param layer layer of entity
     */
    public RenderComponent(GameEntity entity, int layer) {
        super(entity);
        this.layer = layer;
    }

    public RenderComponent(GameEntity entity, Mode mode) {
        super(entity);
        this.mode = mode;
    }

    public RenderComponent(GameEntity entity, int layer, Mode mode) {
        super(entity);
        this.layer = layer;
        this.mode = mode;
    }

    public RenderComponent(GameEntity entity) {
        super(entity);
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

}
