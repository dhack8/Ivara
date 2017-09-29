package core.components;

import core.Script;
import core.entity.GameEntity;
import eem.Component;

/**
 * Script component belongs to an entity. Gets overridden with
 * proper implementation of a script. E.g. NPC1, NPC2, Player...
 */
public class ScriptComponent extends Component<GameEntity> {

    public final Script script;

    public ScriptComponent(GameEntity entity, Script script) {
        super(entity);
        this.script = script;
    }

}
