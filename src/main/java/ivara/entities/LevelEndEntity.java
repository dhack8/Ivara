package ivara.entities;

import core.Game;
import core.entity.GameEntity;
import core.Script;
import core.components.*;
import core.input.SensorHandler;
import core.struct.AnimatedSprite;
import core.struct.Sensor;
import ivara.scenes.Level;
import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;
import maths.Vector;
import physics.AABBCollider;

import java.util.Arrays;

import static ivara.Ivara.MAP;

/**
 * This class handles the behaviour of a Flag that changes level on collision with the player.
 * @author Alex Mitchell 
 */
public class LevelEndEntity extends GameEntity {

    // Constants
    private static final float WIDTH = 1f;
    private static final float HEIGHT = 1f;
    private static final float POLE_WIDTH = 0.1f;
    private static final float YOFFSET = 0.01f;
    private final static int ANIMATION_RATE = 1000;

    private static final Sound playerWin = TinySound.loadSound("win.wav");

    /**
     * Constructs a LevelEndEntity at a specified positions.
     * @param x The x coordinate of the flag.
     * @param y The y coordinate of the flag.
     */
    public LevelEndEntity(float x, float y){
        super(new Vector(x,y));

        // Sprites
        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new FlagSprite(new Vector(WIDTH, HEIGHT), ANIMATION_RATE));
        addComponent(sc);

        // Layer
        addComponent(new RenderComponent(this, 9999999));

        // Sensors
        Vector sTopLeft = new Vector(0f, 0f);
        Vector sDimensions = new Vector(POLE_WIDTH, HEIGHT);
        AABBCollider ab = new AABBCollider(AABBCollider.MIN_DIM, sTopLeft, sDimensions);
        Sensor leftSensor = new Sensor(ab);
        addComponent(new SensorComponent(this, leftSensor));
        addComponent(new SensorHandlerComponent(this));

        // Scripts
        LevelChangeScript l = new LevelChangeScript(leftSensor);
        ScriptComponent sComp = new ScriptComponent(this);
        sComp.add(l);
        addComponent(sComp);
    }

    /**
     * This class handles the Sprite relating to the end level entity.
     * @author David Hack
     */
    private class FlagSprite extends AnimatedSprite {
        /**
         * Constructs a flag sprite that changes it's appearance based upon the current state.
         * @param dimensions The size of the sprite.
         * @param frameTick The update tick delay.
         */
        private FlagSprite(Vector dimensions, int frameTick){
            super(new Vector(0,0), dimensions, frameTick);

            String state = "normal";
            String[] resources = new String[]{
                    "flag-orange",
                    "flag-orange2"
            };
            addResources(state, Arrays.asList(resources));

            setState("normal");
        }
    }

    /**
     * This script causes the current level in the game to change on contact with the flag.
     * @author Alex Mitchell
     */
    private class LevelChangeScript implements Script {
        private final Sensor sensor;
        private boolean entered = false; // Flag to avoid the scene being changed multiple times.

        /**
         * Constructs a LevelChangeScript with a sensor the size of the flag.
         * @param s The sensor.
         */
        public LevelChangeScript(Sensor s){
            this.sensor = s;
        }

        @Override
        public void update(int dt, GameEntity entity) {
            SensorHandler sensorHandler = entity.get(SensorHandlerComponent.class).get().getSensorHandler();

            if(sensorHandler.isActive(sensor)){
                boolean playerCollision = sensorHandler.getActivatingEntities(sensor).stream().anyMatch((e) -> e instanceof PlayerEntity);

                if(!entered && playerCollision) {
                    entered = true;
                    playerWin.play();
                    ((Level)entity.getScene()).complete();
                    Game game = entity.getScene().getGame();
                    game.getLevelManager().setToBookmarkedScene(MAP); // Goes to the next scene on a player collision
                }
            }
        }
    }
}
