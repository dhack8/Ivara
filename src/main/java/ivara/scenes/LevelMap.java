package ivara.scenes;

import core.scene.Scene;
import core.struct.Camera;
import core.struct.ResourceID;
import core.struct.Sprite;
import ivara.entities.*;
import ivara.entities.ui.*;
import ivara.scenes.boots.*;
import maths.Vector;
import physics.AABBCollider;

public class LevelMap extends Scene {

    private static final Vector PLAY_BUTTON_DIMEN = new Vector(7.225f, 1.625f);

    @Override
    public void initialize() {
        Camera c = new Camera();
        setCamera(c);

        addGrassLevels();
        addSnowLevels();
        addAlienLevels();
        addDesertLevels();

        addEntity(new PlayerMiniFigureEntity(new Vector(10.225f, 9.65f)));
        LevelInfoEntity levelInfo = new LevelInfoEntity(new Vector(21.5f, 1.4f));
        addEntity(levelInfo);

//        UIEntity button = new UIEntity(
//                new Vector(22.65f, 13.4f),
//                new Sprite(new ResourceID("play"), new Vector(0, 0), PLAY_BUTTON_DIMEN),
//                new AABBCollider(new Vector(0, 0), PLAY_BUTTON_DIMEN)
//        );
//        button.addListener(levelInfo::playLevel);
        addEntity(new ButtonEntity(new Vector(22.65f, 13.4f), PLAY_BUTTON_DIMEN, "play", "play-hover", "play-click", "play-deactivated"));

        addEntity(new BackgroundEntity(new ResourceID("background-sea")));
        addEntity(new BackgroundEntity(new ResourceID("map")));
    }

    private void addGrassLevels() {
        // Grass
        addEntity(new LevelIconEntity(new Vector(7.275f, 8.4355f), new Level3(), null));
    }

    private void addSnowLevels() {
        // Snow
        addEntity(new LevelIconEntity(new Vector(11.7625f, 6.1625f), new Level3(), null));
    }

    private void addAlienLevels() {
        // Alien
        Level initialBootsLevel = new InitialBootsLevel();
        addEntity(new LevelIconEntity(new Vector(15.125f, 9.5375f), initialBootsLevel, null));

        // Alien: Top path, double jump
        Level doubleJump1 = new DoubleJump1();
        Level doubleJump2 = new DoubleJump2();
        Level doubleJump3 = new DoubleJump3();
        addEntity(new LevelIconEntity(new Vector(15.7f, 8.4125f), doubleJump1, initialBootsLevel));
        addEntity(new LevelIconEntity(new Vector(17.375f, 7.8625f), doubleJump2, doubleJump1));
        addEntity(new LevelIconEntity(new Vector(19.65f, 9.5375f), doubleJump3, doubleJump2));

        // Alien: Middle path, vertical jump
        Level verticalJump = new VerticalJump();
        addEntity(new LevelIconEntity(new Vector(16.275f, 9.5375f), verticalJump, initialBootsLevel));

        // Alien: Bottom path, movement speed
        Level movementSpeed1 = new MovementSpeed1();
        Level movementSpeed2 = new MovementSpeed2();
        Level movementSpeed3 = new MovementSpeed3();
        addEntity(new LevelIconEntity(new Vector(16.275f, 11.225f), movementSpeed1, initialBootsLevel));
        addEntity(new LevelIconEntity(new Vector(17.975f, 10.65f), movementSpeed2, movementSpeed1));
        addEntity(new LevelIconEntity(new Vector(17.3625f, 9.5375f), movementSpeed3, movementSpeed2));
    }

    private void addDesertLevels() {
        // Desert
        addEntity(new LevelIconEntity(new Vector(10.65f, 11.8f), new Level3(), null));
    }
}

