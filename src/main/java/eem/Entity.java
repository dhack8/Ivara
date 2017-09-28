package eem;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Callum Li on 9/28/17.
 */
public abstract class Entity {

    /**
     *
     */
    private Collection<Component> components = new HashSet<>();

    /**
     *
     * @param component
     */
    final public void addComponent(Component component) {
        components.add(component);
    }

    final public <T extends Component> T getComponent(Class<T> type) {
        return type.cast(components.stream()
                .filter((component -> component.getClass().equals(type)))
                .findAny()
                .orElse(null));
    }
}
