package backends.processing;

import core.components.ColliderComponent;
import core.components.RenderComponent;
import core.components.SensorComponent;
import core.components.SpriteComponent;
import core.entity.GameEntity;
import core.scene.Scene;
import core.struct.*;
import ivara.entities.BackgroundEntity;
import ivara.entities.BasicTextEntity;
import ivara.entities.PlatformEntity;
import maths.Vector;
import org.junit.Before;
import org.junit.Test;
import physics.AABBCollider;
import processing.core.PApplet;

import javax.swing.*;

import static org.junit.Assert.*;

/**
 * Tests for the PWindow using a mixture of specific test classes and some from the implementation.
 * @author David Hack
 */
public class PWindowTest {

    int delay = 800;
    PWindow testWindow;
    TestScene testScene;
    ResourceID background;
    Camera camera;

    {
        testWindow = new PWindow(false, new Vector(1600, 900));
        testWindow.setMask(1);
        PApplet.runSketch(new String[]{"PWindow"}, testWindow);
        camera = new Camera(new Vector(0,0), new Vector(32,18));
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
        dispalyAndAskUser("Do you see a little man standing at the start of a nice platform with nice corners?\n" +
                "With test text below?");
        testScene.moveSprite();
        dispalyAndAskUser("Do you see a little man standing at the end of the platform?\n" +
                "With test text below?");
    }

    @Test
    public void debugRender(){
        testWindow.setMask(2);
        dispalyAndAskUser("Do you see a scene with red testing boxes? note vegetation is not intended to have red boxes.\n" +
                "Pablo should have a green box covering half of his body");
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

    @Test
    public void testVertLetterBoxRender(){
        camera = new Camera(new Vector(0,0), new Vector(40,30));
        testScene = new TestScene();
        dispalyAndAskUser("Do you see a scene with vertical letter boxing?");
    }

    @Test
    public void testHorLetterBoxRender(){
        camera = new Camera(new Vector(0,0), new Vector(43,18));
        testScene = new TestScene();
        dispalyAndAskUser("Do you see a scene with horizontal letter boxing?");
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
            addEntity(new BasicTextEntity(new Vector(3, 7f), new Text(20, "Test text here")));
            addEntity(new BackgroundEntity(background));
            setCamera(camera);
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
            addComponent(new SensorComponent(this, new Sensor(new AABBCollider(AABBCollider.MIN_DIM, new Vector(0,0.75f), new Vector(1,0.75f)))));
            addComponent(new RenderComponent(this, 999999999));
        }

        public void moveSprite(){
            getTransform().incrementBy(9,0);
        }
    }
}