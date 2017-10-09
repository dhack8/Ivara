package core.struct;

import maths.Vector;

/**
 * Basic data object for a sprite
 * @author David Hack
 */
public class Text {

    public final Vector transform;
    public final float fontSize;
    public final String text;

    /**
     * Constructs a text data object
     * @param transform transform from entity location
     * @param fontSize
     * @param text
     */
    public Text(Vector transform, float fontSize, String text) {
        this.transform = transform;
        this.fontSize = fontSize;
        this.text = text;
    }
}
