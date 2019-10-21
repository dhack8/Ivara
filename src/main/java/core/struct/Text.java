package core.struct;

import maths.Vector;

import java.io.Serializable;

/**
 * Basic data object for a sprite
 * @author David Hack
 */
public class Text implements Serializable {

    public final Vector transform;
    public final Vector dimensions;
    public final float fontSize;
    public final String text;

    /**
     * Constructs a text data object
     * @param transform transform from entity location
     * @param fontSize font size
     * @param text text to store
     */
    public Text(Vector transform, float fontSize, String text) {
        this.transform = transform;
        this.fontSize = fontSize;
        this.text = text;
        this.dimensions = null;
    }

    /**
     * Constructs a text data object without a transform
     * @param fontSize font size
     * @param text text to store
     */
    public Text(float fontSize, String text) {
        this.transform = new Vector(0,0);
        this.fontSize = fontSize;
        this.text = text;
        this.dimensions = null;
    }

    public Text(Vector transform, Vector dimensions, float fontSize, String text) {
        this.transform = transform;
        this.dimensions = dimensions;
        this.fontSize = fontSize;
        this.text = text;
    }

    public boolean hasDimension() {
        return !(dimensions == null);
    }
}
