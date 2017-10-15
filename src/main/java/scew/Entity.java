package scew;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Abstract class that has the basic functionality required
 * to allow extending classes to act as entities.
 *
 * @see Component
 * @author Callum Li
 */
public abstract class Entity {

    /**
     * The collection of components associated with the entity.
     */
    private Set<Component> components = new HashSet<>();

    /**
     * Adds a component to the entity.
     * @param component The component to add.
     */
    final public void addComponent(Component component) {

        //TODO this only checks for exact class matches not supers
        if(components.stream().anyMatch((c) -> c.getClass().equals(component.getClass()))){
            throw new DuplicateComponentException("You can only add one component of each type");
        }

        components.add(component);
    }

    /**
     * Removes a component from the entity.
     * @param component The component to remove.
     * @return True if a component was removed, false otherwise.
     */
    final public boolean removeComponent(Component component) {
        return components.remove(component);
    }

    final public boolean removeComponent(Class<? extends Component> type) {
        return removeComponent(get(type).orElse(null));
    }

    /**
     * Retrieves a component of the given type if one exists.
     * @param type The type of component to find.
     * @param <T> The type of component to find.
     * @return The component, or empty if it does not exist.
     */
    final public <T extends Component> Optional<T> get(Class<T> type) {
        return components.stream()
                .filter((component -> component.getClass().equals(type)))
                .map((component -> type.cast(component)))
                .findAny();
    }

    /**
     * Returns all the components belonging to this entity.
     * @return
     */
    final public Collection<Component> getComponents() {
        return components;
    }

    /**
     * Exception that occurs if multiple components of the same type are
     * added.
     */
    class DuplicateComponentException extends RuntimeException{
        public DuplicateComponentException(String msg){
            super(msg);
        }
    }
}
