package ivara.scenes;

import core.scene.Scene;
import core.struct.Camera;
import core.struct.ResourceID;
import core.struct.Sprite;
import ivara.entities.MenuButtonEntity;
import ivara.entities.UIEntity;
import ivara.entities.UIListener;
import maths.Vector;
import physics.AABBCollider;
import util.Debug;

/**
 * Created by Alex Mitchell on 9/10/2017.
 */
public class MenuScene extends Scene{
    @Override
    public void startScene() {

        //--- New game button
        UIEntity start = new UIEntity(new Vector(1f,1f),
                new Sprite(new ResourceID("player"), new Vector(0, 0), new Vector(1f, 1.5f)),
                new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), new Vector(1f, 1.5f)));
        start.addListener(new UIListener() {
            @Override
            public void onClick() {
                start.getScene().getGame().setCurrentScene(0); // Start from level 1 (level 0 is menu)
            }
        });
        addEntity(start);

        //--- Resume button
        UIEntity resume = new UIEntity(new Vector(1f,3f),
                new Sprite(new ResourceID("player"), new Vector(0, 0), new Vector(1f, 1.5f)),
                new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), new Vector(1f, 1.5f)));
        resume.addListener(new UIListener() {
            @Override
            public void onClick() {
                resume.getScene().getGame().pause(); // Todo: There will be a problem clicking pause on the starting menu, maybe set the initial game state as pause??? Change the first scene to be first level, not the pause menu
            }
        });
        addEntity(resume);

        //--- save button

        UIEntity save = new UIEntity(new Vector(1f,5f),
                new Sprite(new ResourceID("player"), new Vector(0, 0), new Vector(1f, 1.5f)),
                new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), new Vector(1f, 1.5f)));
        start.addListener(new UIListener() {
            @Override
            public void onClick() {
                Debug.log("Have not implemented Save.");
            }
        });
        addEntity(save);

        //--- load button

        UIEntity load = new UIEntity(new Vector(1f,7f),
                new Sprite(new ResourceID("player"), new Vector(0, 0), new Vector(1f, 1.5f)),
                new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), new Vector(1f, 1.5f)));
        start.addListener(new UIListener() {
            @Override
            public void onClick() {
                Debug.log("Have not implemented Load.");
            }
        });
        addEntity(load);

        setCamera(new Camera());
    }
}
