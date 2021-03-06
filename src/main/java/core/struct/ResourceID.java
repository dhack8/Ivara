package core.struct;

import java.io.Serializable;

/**
 * Resource ID class, A little lacking at the moment but is in place for future changes.
 */
public class ResourceID implements Serializable {

    public final String id;

    /**
     * Simply constructs a ResourceID with a string.
     * @param id string id
     */
    public ResourceID(String id) {
        this.id = id;
    }
}
