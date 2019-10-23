package ivara.scenes;

import core.scene.Scene;
import core.struct.Camera;
import core.struct.ResourceID;
import core.struct.Text;
import ivara.Ivara;
import ivara.entities.BackgroundEntity;
import ivara.entities.ui.BasicTextEntity;
import ivara.entities.ui.ButtonEntity;
import ivara.entities.ui.HighscoresEntity;
import maths.Vector;

public class HighscoreScene extends Scene {

    public HighscoreScene(String level){
        Camera c = new Camera();


        addEntity(new BackgroundEntity(new ResourceID("background-sea")));
        addEntity(new BackgroundEntity(new ResourceID("highscore-board")));

        setCamera(c);
        HighscoresEntity hse = new HighscoresEntity(new Vector(0,0), level);
        addEntity(hse);

        ButtonEntity back = new ButtonEntity(new Vector(12.38f, 14f), new Vector(7.225f, 1.625f), "back");
        back.addListener(() -> getGame().getLevelManager().setToBookmarkedScene(Ivara.MAP));
        addEntity(back);

        addEntity(new BasicTextEntity(new Vector(12.375f, 3f), new Text(50, "Highscores")));
    }

}
