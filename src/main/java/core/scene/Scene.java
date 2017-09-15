package core.scene;

import core.components.Component;
import core.components.SpriteComponent;
import util.ClassMap;

import java.io.File;
import java.util.*;

/**
 * Created by Callum Li on 9/15/17.
 */
public abstract class Scene {

    // Need a Map of Class -> Set, since we need to access the set of components from a given class type
    private Camera camera;
    ClassMap classMap = new ClassMap();

    /**
     * Empty scene construction
     */
    public Scene(){
        camera = new Camera();
        //components = new HashMap<>();
        throw new UnsupportedOperationException();
    }

    /**
     * Scene construction from a file
     */
    public Scene(File file){
        throw new UnsupportedOperationException();
    }

    private void addEntity(Entity entity, String name) {
        // Add the entity to the entity collection
        // Add the relevant components to the Set of those specific components
        throw new UnsupportedOperationException();

    }

    private void addEntity(Entity entity) {
        throw new UnsupportedOperationException();

    }

    private Entity getEntity(String name) {
        throw new UnsupportedOperationException();

    }


    public <T> Collection<T> getComponents(Class<T> type) { // Want to be able to access the components in the map by a type
        return classMap.get(type);
    }

    public Camera getCamera() {
        return camera;
    }
}
