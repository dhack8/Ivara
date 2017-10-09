package ivara.scenes;

import core.scene.Scene;
import core.struct.Camera;
import core.struct.ResourceID;
import core.struct.Sprite;
import ivara.entities.UIEntity;
import ivara.entities.UIListener;
import maths.Vector;
import physics.AABBCollider;
import util.Debug;

/**
 * This scene acts as a pause menu for the game
 * @author Alex Mitchell 
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
                //Todo: Reset the previous scene on exit to avoid problems such as there not being a new game when the scene is set to level 0
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
                resume.getScene().getGame().pause();
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
