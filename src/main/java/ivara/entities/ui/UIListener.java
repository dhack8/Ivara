package ivara.entities.ui;

import java.io.Serializable;

/**
 * This interface enforces the UIElement to respond to click events.
 * @author Callum Li
 */
public interface UIListener extends Serializable {
    void onClick();
}
