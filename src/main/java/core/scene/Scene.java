package core.scene;

import core.components.CollidierComponent;

import java.util.Collection;

/**
 * Created by Callum Li on 9/15/17.
 */
public abstract class Scene {

    private void addEntity(Entity entity, String name) {
        throw new UnsupportedOperationException();

    }

    private void addEntity(Entity entity) {
        throw new UnsupportedOperationException();

    }

    private Entity getEntity(String name) {
        throw new UnsupportedOperationException();

    }

    private <T extends CollidierComponent> Collection<T> getComponent(Class<T> type) {
        throw new UnsupportedOperationException();

    }
}
