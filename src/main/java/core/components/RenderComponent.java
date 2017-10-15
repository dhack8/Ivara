package core.components;

import core.entity.GameEntity;
import scew.Component;

/**
 * Layer component gives a entity a layer for rendering.
 * And other render types.
 *
 * @author David Hack
 */
public class RenderComponent extends Component<GameEntity> {

    //Types
    public enum Mode{
        NORMAL,
        PIXEL_NO_TRANS,
        FULLSCREEN
    }

    private int layer = 0;
    private Mode mode = Mode.NORMAL;

    /**
     * Creates a layer component with an int value.
     * @param entity parent of component
     * @param layer layer of entity
     */
    public RenderComponent(GameEntity entity, int layer) {
        super(entity);
        this.layer = layer;
    }

    /**
     * Crates a render component with just a mode.
     * @param entity parent of component
     * @param mode mode of rendering
     */
    public RenderComponent(GameEntity entity, Mode mode) {
        super(entity);
        this.mode = mode;
    }

    /**
     * Creates a rendering component with all the parameters
     * @param entity parent of component
     * @param layer layer of entity
     * @param mode mode of rendering
     */
    public RenderComponent(GameEntity entity, int layer, Mode mode) {
        super(entity);
        this.layer = layer;
        this.mode = mode;
    }

    /**
     * Creates a rendering component with no other parameters.
     * @param entity parent of component
     */
    public RenderComponent(GameEntity entity) {
        super(entity);
    }

    /**
     * Getter for the layer.
     * @return layer value
     */
    public int getLayer() {
        return layer;
    }

    /**
     * Setter for the layer.
     * @param layer layer value
     */
    public void setLayer(int layer) {
        this.layer = layer;
    }

    /**
     * Returns the render mode.
     * @return render mode
     */
    public Mode getMode() {
        return mode;
    }

    /**
     * Setter for the mode
     * @param mode render mode
     */
    public void setMode(Mode mode) {
        this.mode = mode;
    }

}
