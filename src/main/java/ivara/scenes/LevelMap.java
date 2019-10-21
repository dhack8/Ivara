package ivara.scenes;

import core.scene.Scene;
import core.struct.Camera;
import core.struct.ResourceID;
import core.struct.Sprite;
import ivara.entities.*;
import ivara.entities.ui.*;
import ivara.scenes.boots.*;
import ivara.scenes.crossbow.*;
import maths.Vector;
import physics.AABBCollider;

import java.io.Serializable;

import static ivara.Ivara.MAIN_MENU;

public class LevelMap extends Scene {

    private static final Vector PLAY_BUTTON_DIMEN = new Vector(7.225f, 1.625f);
    private static final Vector MINI_BUTTON_DIMEN = new Vector(4.6f, 0.84375f);

    @Override
    public void initialize() {
        Camera c = new Camera();
        setCamera(c);

        addGrassLevels();
        addSnowLevels();
        addAlienLevels();
        addDesertLevels();

        addEntity(new PlayerMiniFigureEntity(new Vector(10.225f, 9.65f)));

        ButtonEntity playButton = new ButtonEntity(new Vector(22.65f, 13.4f), PLAY_BUTTON_DIMEN, "play");
        LevelInfoEntity levelInfo = new LevelInfoEntity(new Vector(21.5f, 1.4f), playButton);
        addEntity(levelInfo);
        addEntity(playButton);
        playButton.addListener(levelInfo::playLevel);

        addEntity(new MiniMenuEntity(new Vector(1.2f, 13f)));
        addEntity(
                new ButtonEntity(new Vector(1.4f, 13.2f), MINI_BUTTON_DIMEN, "save")
                        .addListener(() -> getGame().save()));
        addEntity(new ButtonEntity(
                new Vector(1.4f, 14.1f), MINI_BUTTON_DIMEN, "load")
                        .addListener(() -> getGame().load()));
        addEntity(
                new ButtonEntity(new Vector(1.4f, 15f), MINI_BUTTON_DIMEN, "back")
                        .addListener(() -> getGame().getLevelManager().setToBookmarkedScene(MAIN_MENU)));
        addEntity(
                new ButtonEntity(new Vector(1.4f, 15.9f), MINI_BUTTON_DIMEN, "quit")
                        .addListener(() -> getGame().getWindow().exit()));

        addEntity(new BackgroundEntity(new ResourceID("background-sea")));
        addEntity(new BackgroundEntity(new ResourceID("map")));
    }

    private void addGrassLevels() {
        // Grass
        Level initialCrossbowLevel = new InitialCrossbowLevel();
        addEntity(new LevelIconEntity(new Vector(7.275f, 8.4355f), initialCrossbowLevel, null));

        // Grass: Bottom path, quiver and split
        Level quiverSize1 = new QuiverSize1();
        Level quiverSize2 = new QuiverSize2();
        Level splitShot = new SplitShot();
        addEntity(new LevelIconEntity(new Vector(7.25f, 10.65f), quiverSize1, initialCrossbowLevel));
        addEntity(new LevelIconEntity(new Vector(3.875f, 10.65f), quiverSize2, quiverSize1));
        addEntity(new LevelIconEntity(new Vector(5f, 9.525f), splitShot, quiverSize1));

        // Grass: Top path, movement and monsters
        Level rangeAndSpeed = new RangeAndSpeed();
        Level monsterBoost1 = new MonsterBoost1();
        Level monsterBoost2 = new MonsterBoost2();
        Level monsterBoost3 = new MonsterBoost3();
        Level quickDraw = new QuickDraw();
        Level hipFire = new HipFire();
        addEntity(new LevelIconEntity(new Vector(7.175f, 6.4f), rangeAndSpeed, initialCrossbowLevel));
        addEntity(new LevelIconEntity(new Vector(4.975f, 6.925f), monsterBoost1, rangeAndSpeed));
        addEntity(new LevelIconEntity(new Vector(3.875f, 7.875f), monsterBoost2, monsterBoost1));
        addEntity(new LevelIconEntity(new Vector(5f, 8.4355f), monsterBoost3, monsterBoost2));
        addEntity(new LevelIconEntity(new Vector(3.875f, 6.15f), quickDraw, rangeAndSpeed));
        addEntity(new LevelIconEntity(new Vector(3.875f, 5.05f), hipFire, quickDraw));
    }

    private void addSnowLevels() {
        // Snow
        addEntity(new LevelIconEntity(new Vector(11.7625f, 6.1625f), new StubLevel(), new StubLevel()));
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
        addEntity(new LevelIconEntity(new Vector(10.65f, 11.8f), new StubLevel(), new StubLevel()));
    }
}

