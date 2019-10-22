package ivara.scenes;

import core.components.TextComponent;
import core.scene.Scene;
import core.struct.Camera;
import core.struct.ResourceID;
import core.struct.Text;
import ivara.Ivara;
import ivara.entities.BackgroundEntity;
import ivara.entities.PlayerEntity;
import ivara.entities.ui.BasicTextEntity;
import ivara.entities.ui.ButtonEntity;
import ivara.entities.ui.TextInputEntity;
import maths.Vector;

public class NameInputScene extends Scene {
    private static int MAX_CHARS = 22;
    private static int TEXT_SIZE = 35;
    private TextInputEntity textInputEntity;

    @Override
    public void initialize() {

        Camera c = new Camera();
        setCamera(c);


        addEntity(new BackgroundEntity(new ResourceID("background-sea")));
        addEntity(new BackgroundEntity(new ResourceID("map")));
        addEntity(new BackgroundEntity(new ResourceID("name-input-popup-50")));

        addEntity(new BasicTextEntity(new Vector(10.75f, 6f), new Text(50 , "Welcome to the adventures of Pablo!")));
        addEntity(new BasicTextEntity(new Vector(9.85f, 7.5f), new Text(35 , "Before you set off on your journey, please type in a name for your\ncharacter. When you're done typing, click the confirm button.")));

        ButtonEntity confirm = new ButtonEntity(new Vector(13.2f, 11f), new Vector(5.5f, 1.5f), "confirm");
        confirm.addListener(() -> {
            PlayerEntity.PLAYER_NAME = textInputEntity.getText();
            getGame().getLevelManager().setToBookmarkedScene(Ivara.MAP);
        });
        addEntity(confirm);

        this.textInputEntity = new TextInputEntity(new Vector(11.5f, 10.05f), MAX_CHARS, TEXT_SIZE, "");
        addEntity(textInputEntity);
    }
}
