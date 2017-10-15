package backends;

import core.input.KeyListener;
import core.input.MouseListener;

/**
 * Input Broadcaster for key inputs.
 */
public interface InputBroadcaster {

    void addKeyListener(KeyListener listener);
    void addMouseListener(MouseListener listener);
}
