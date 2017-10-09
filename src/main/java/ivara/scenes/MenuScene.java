package ivara.scenes;

import core.scene.Scene;
import core.struct.Camera;
import ivara.entities.MenuButtonEntity;
import maths.Vector;

/**
 * Created by Alex Mitchell on 9/10/2017.
 */
public class MenuScene extends Scene{
    @Override
    public void startScene() {
        addEntity(new MenuButtonEntity(new Vector(1,1), MenuButtonEntity.MenuOption.PLAY));
        setCamera(new Camera());
    }
}
