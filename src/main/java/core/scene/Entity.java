package core.scene;

import maths.Vector;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

/**
 * This class represents the entities within the game
 * @author Alex Mitchell
 */
public abstract class Entity {

    private Vector position;

    public Entity(Vector position) {
        this.position = position;
    }

    /**
     * Translate the entity
     *
     * @param dx Position change in the X plane
     * @param dy Position change in the Y plane
     */
    public void translate(float dx, float dy) {
        position.add(dx, dy);
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    /**
     * Returns
     * // todo: Do javadoc
     * @param type
     * @param <T>
     * @return
     */
    public <T> Collection<T> getComponents(Class<T> type) {
        Collection<T> components = new HashSet<>();

        // For each field, add the value of the field if the type
        // of the field matches the given type.
        for (Field field : this.getClass().getDeclaredFields()) {

            if (type.equals(field.getType())) {

                try {
                    components.add(type.cast(field.get(this)));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }

        return components;
    }
}
