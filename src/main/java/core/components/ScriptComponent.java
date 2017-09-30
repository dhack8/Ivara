package core.components;

import core.Script;
import core.entity.GameEntity;
import scew.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Script component belongs to an entity. Gets overridden with
 * proper implementation of a script. E.g. NPC1, NPC2, Player...
 */
public class ScriptComponent extends Component<GameEntity> {

    //public final Script script;

    public Set<Script> scripts;

    /**
     * Constructor for a ScriptComponent that takes a single script
     * @param entity The entity for the script
     * @param script The script to add
     */
    public ScriptComponent(GameEntity entity, Script script) {
        super(entity);
        scripts = new HashSet<Script>();
        //this.script = script;
        scripts.add(script);
    }

    /**
     * Constructor for a ScriptComponent that takes a collection of scripts
     * @param entity The entity for the script
     * @param scripts The scripts to add
     */
    public ScriptComponent(GameEntity entity, Collection<Script> scripts) {
        super(entity);
        this.scripts = new HashSet<Script>();
        this.scripts.addAll(scripts);
    }

}
