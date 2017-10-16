package backends.processing;

import core.components.ColliderComponent;
import core.components.RenderComponent;
import core.components.SpriteComponent;
import core.entity.GameEntity;
import core.scene.Scene;
import core.struct.Camera;
import core.struct.ResourceID;
import core.struct.Sprite;
import ivara.entities.BackgroundEntity;
import ivara.entities.PlatformEntity;
import maths.Vector;
import org.junit.Before;
import org.junit.Test;
import physics.AABBCollider;
import processing.core.PApplet;

import javax.swing.*;

import static org.junit.Assert.*;

/**
 * Created by David Hack Local on 27-Sep-17.
 */
public class PWindowTest {

    int delay = 800;
    PWindow testWindow;
    TestScene testScene;
    ResourceID background;

    {
        testWindow = new PWindow(false, new Vector(1600, 900));
        testWindow.setMask(1);
        PApplet.runSketch(new String[]{"PWindow"}, testWindow);
    }

    @Before
    public void startScene(){
        background = new ResourceID("background");
        testScene = new TestScene();
    }

    @Test
    public void nullRender(){
        testScene = null;
        dispalyAndAskUser("Do you see red error screen?");
    }

    @Test
    public void normalRender(){
        dispalyAndAskUser("Do you see a little man standing at the start of a nice platform with nice corners?");
        testScene.moveSprite();
        dispalyAndAskUser("Do you see a little man standing at the end of the platform?");
    }

    @Test
    public void debugRender(){
        testWindow.setMask(2);
        dispalyAndAskUser("Do you see a scene with red testing boxes? note vegetation is not intended to have red boxes.");
        testWindow.setMask(1);
    }

    @Test
    public void sunriseRender(){
        background = new ResourceID("background-sunrise");
        testScene = new TestScene();
        dispalyAndAskUser("Do you see a scene with a pink sun RISE background, that spans the whole screen?");
    }

    @Test
    public void dayRender(){
        background = new ResourceID("background");
        testScene = new TestScene();
        dispalyAndAskUser("Do you see a scene with a blue sky background, that spans the whole screen?");
    }

    @Test
    public void sunsetRender(){
        background = new ResourceID("background-sunset");
        testScene = new TestScene();
        dispalyAndAskUser("Do you see a scene with a orange sun SET background, that spans the whole screen?");
    }

    @Test
    public void darkRender(){
        background = new ResourceID("background-dark");
        testScene = new TestScene();
        dispalyAndAskUser("Do you see a scene with a dark red background, that spans the whole screen?");
    }

    public void dispalyAndAskUser(String s){
        long start = System.currentTimeMillis();
        while(true) {
            testWindow.render(testScene);
            if(System.currentTimeMillis() - start > delay){
                break;
            }
        }

        int o = JOptionPane.showConfirmDialog(new JFrame(), s, "Render result", JOptionPane.YES_NO_OPTION);

        if(o == 1)fail("Human tester detected a error with rendering");
    }

    public class TestScene extends Scene{
        TestEntity tE;

        @Override
        public void startScene() {
            tE = new TestEntity(2, 1.5f);

            addEntity(tE);
            addEntity(new PlatformEntity(new Vector(2,3), 10, false));
            addEntity(new BackgroundEntity(background));
            setCamera(new Camera(new Vector(0,0), new Vector(32,18)));
        }

        public void moveSprite(){
            tE.moveSprite();
        }
    }

    public class TestEntity extends GameEntity {
        public TestEntity(float x, float y) {
            super(new Vector(x,y));
            addComponent(new SpriteComponent(this, new Sprite(new ResourceID("player"), new Vector(0,0), new Vector(1,1.5f))));
            addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.MIN_DIM, new Vector(0,0), new Vector(1,1.5f))));
            addComponent(new RenderComponent(this, 999999999));
        }

        public void moveSprite(){
            getTransform().incrementBy(9,0);
        }
    }
}