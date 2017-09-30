package eem;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author David Hack
 */
public abstract class Entity {

    /**
     *
     */
    private Set<Component> components = new HashSet<>();

    /**
     *
     * @param component
     */
    final public void addComponent(Component component) {

        //TODO this only checks for exact class matches not supers
        if(components.stream().anyMatch((c) -> c.getClass().equals(component.getClass()))){
            throw new DuplicateComponentException("You can only add one component of each type");
        }

        components.add(component);
    }

    final public <T extends Component> Optional<T> get(Class<T> type) {

        return components.stream()
                .filter((component -> component.getClass().equals(type)))
                .map((component -> type.cast(component)))
                .findAny();
    }

    final public Collection<Component> getComponents() {
        return components;
    }

    class DuplicateComponentException extends RuntimeException{
        public DuplicateComponentException(String msg){
            super(msg);
        }
    }
}
