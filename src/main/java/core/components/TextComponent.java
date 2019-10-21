package core.components;

import core.entity.GameEntity;
import core.struct.Sprite;
import core.struct.Text;
import maths.Vector;
import scew.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Text Component stores text within the scene.
 * @author David Hack
 */
public class TextComponent extends Component<GameEntity>{

    private final List<Text> texts = new ArrayList<>();

    //Constructors --------------------------------------------

    /**
     * Constructor for a text component that has no texts.
     * @param entity parent entity
     */
    public TextComponent(GameEntity entity){
        super(entity);
    }

    /**
     * Constructor for a text component that takes one text.
     * @param entity parent entity
     */
    public TextComponent(GameEntity entity, Text text){
        super(entity);
        texts.add(text);
    }

    //End of constructors----------------------------------------

    //TODO: Remove all of these other adds and just keep the text add, check if other components do this to!
    /**
     * Adds a text to this text component.
     * @param t text to add
     */
    public void add(Text t){
        texts.add(t);
    }

    /**
     * Adds a text to this text component.
     * @param s string to add
     * @param f font size
     */
    public void add(String s, float f){
        texts.add(new Text(new Vector(0,0), f, s));
    }

    /**
     * Adds a text to this text component.
     * @param v transform vector
     * @param s string to add
     * @param f font size
     */
    public void add(Vector v, String s, float f){
        texts.add(new Text(v, f, s));
    }

    /**
     * Getter for the texts
     * @return list of texts
     */
    public List<Text> getTexts(){
        return texts;
    }

    /**
     * Clears the stored texts.
     */
    public void clear() {
        texts.clear();
    }
}
