package ivara.entities;

import core.Script;
import core.components.*;
import core.entity.GameEntity;
import core.input.SensorHandler;
import core.scene.Scene;
import core.struct.AnimatedSprite;
import core.struct.Sensor;
import ivara.scenes.DefaultScene;
import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;
import maths.Vector;
import physics.AABBCollider;

import java.util.Arrays;

/**
 * This class handles the operation of a CheckPoint Entity.
 * This entity sets the spawn position in the level.
 * @author David Hack
 */
public class CheckpointEntity extends GameEntity{

    // Constants
    private static final float WIDTH = 1f;
    private static final float HEIGHT = 1f;
    private static final float POLE_WIDTH = 0.3f;
    private static final float YOFFSET = 0.01f;
    private final static int ANIMATION_RATE = 1000;

    private final AnimatedSprite as;

    private static final Sound checkpointPass = TinySound.loadSound("checkpointpass.wav");

    /**
     * Constructs a CheckPointEntity at a specified position.
     * @param x The x position.
     * @param y The y position.
     */
    public CheckpointEntity(float x, float y){
        super(new Vector(x,y));

        // Script
        SpriteComponent sc = new SpriteComponent(this);
        as = new FlagSprite(new Vector(WIDTH, HEIGHT), ANIMATION_RATE);
        sc.add(as);
        addComponent(sc);

        // Layer
        addComponent(new RenderComponent(this, 9999999));

        // Sensors
        Vector sTopLeft = new Vector(0f, 0f);
        Vector sDimensions = new Vector(POLE_WIDTH, HEIGHT);
        AABBCollider ab = new AABBCollider(AABBCollider.MIN_DIM, sTopLeft, sDimensions);
        Sensor leftSensor = new Sensor(ab);
        addComponent(new SensorComponent(this, leftSensor));

        // Enable Listening for Sensor Events
        addComponent(new SensorHandlerComponent(this));

        // Scripts
        CheckpointScript c = new CheckpointScript(leftSensor);
        ScriptComponent sComp = new ScriptComponent(this);
        sComp.add(c);
        addComponent(sComp);
    }

    /**
     * Handles the sprite of the flag.
     * The flag sprite changes on game ticks.
     * @author David Hack
     */
    private class FlagSprite extends AnimatedSprite {
        /**
         * Constructs the FlagSprite that is used by the CheckPointEntity.
         * @param dimensions The size of the sprite.
         * @param frameTick The update ticks for the sprite.
         */
        private FlagSprite(Vector dimensions, int frameTick){
            super(new Vector(0,0), dimensions, frameTick);

            String state = "normal";
            String[] resources = new String[]{
                    "flag-purple",
                    "flag-purple2"
            };
            addResources(state, Arrays.asList(resources));
            state = "down";
            resources = new String[]{
                    "flag-purple-down",
                    "flag-purple-down2"
            };
            addResources(state, Arrays.asList(resources));

            setState("normal");
        }
    }

    /**
     * This script sets the spawn location of the scene when the flag is touched by the player.
     * @author David Hack
     * @author Alex Mitchell
     */
    private class CheckpointScript implements Script {

        private final Sensor sensor;
        private boolean entered = false; // Avoiding the flag being entered multiple times

        /**
         * Constructs the Checkpoint script with the sensor.
         * @param s The sensor that detects the player.
         */
        public CheckpointScript(Sensor s){
            this.sensor = s;
        }

        @Override
        public void update(int dt, GameEntity entity) {
            SensorHandler sensorHandler = entity.get(SensorHandlerComponent.class).get().getSensorHandler();

            if(sensorHandler.isActive(sensor)){
                boolean playerCollision = sensorHandler.getActivatingEntities(sensor).stream().anyMatch((e) -> e instanceof PlayerEntity);

                if(!entered && playerCollision) {
                    entered = true;
                    Scene current = entity.getScene();
                    if(current instanceof DefaultScene){
                        DefaultScene currentDefault = (DefaultScene) current;
                        currentDefault.updateCheckpoint(entity.getTransform()); // Set the spawn in the level.
                    }
                    checkpointPass.play();
                    as.setState("down");
                }
            }
        }
    }
}
