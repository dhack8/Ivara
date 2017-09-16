package backends;

import core.input.KeyListener;
import core.input.MouseListener;

/**
 * Created by Callum Li on 9/16/17.
 */
public interface InputBroadcaster {

    void addKeyListener(KeyListener listener);
    void addMouseListener(MouseListener listener);
}
