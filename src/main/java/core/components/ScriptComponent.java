package core.components;

import core.Script;
import core.entity.GameEntity;
import scew.Component;

import java.util.*;

/**
 * Script component belongs to an entity. Gets overridden with
 * proper implementation of a script. E.g. NPC1, NPC2, Player...
 */
public class ScriptComponent extends Component<GameEntity> {

    private final List<Script> scripts = new ArrayList<>();

    /**
     * Constructor for a ScriptComponent with no scripts.
     * @param entity The entity for the script
     */
    public ScriptComponent(GameEntity entity) {
        super(entity);
    }

    /**
     * Constructor for a ScriptComponent that takes a single script.
     * @param entity The entity for the script
     */
    public ScriptComponent(GameEntity entity, Script script) {
        super(entity);
        scripts.add(script);
    }

    /**
     * Constructor for a ScriptComponent that takes a array of scripts.
     * @param entity The entity for the script
     */
    public ScriptComponent(GameEntity entity, Script[] scripts) {
        super(entity);
        for(Script s : scripts) this.scripts.add(s);
    }

    /**
     * Adds a single script to the component.
     * @param s script to add
     */
    public void add(Script s){
        scripts.add(s);
    }
}
