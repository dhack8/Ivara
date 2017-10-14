package ivara.entities;

import core.Script;
import core.components.*;
import core.entity.GameEntity;
import core.input.SensorHandler;
import core.scene.Scene;
import core.struct.AnimatedSprite;
import core.struct.Sensor;
import ivara.scenes.DefaultScene;
import maths.Vector;
import physics.AABBCollider;

import java.util.Arrays;

/**
 * Created by David Hack Local on 14-Oct-17.
 */
public class CheckpointEntity extends GameEntity{

    //Dimensions
    private float width = 1f;
    private float height = 1f;

    private float poleWidth = 0.1f;

    private float yOffset = 0.01f;

    private final static int ANIMATION_RATE = 1000;

    public CheckpointEntity(float x, float y){
        super(new Vector(x,y));

        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new FlagSprite(new Vector(width, height), ANIMATION_RATE));
        addComponent(sc);

        addComponent(new RenderComponent(this, 9999999));

        //Sensors---
        //AABB for the sensor
        Vector sTopLeft = new Vector(0f, 0f);
        //Vector sDimensions = new Vector(0.1f, height);
        Vector sDimensions = new Vector(poleWidth, height);
        AABBCollider ab = new AABBCollider(AABBCollider.MIN_DIM, sTopLeft, sDimensions);
        Sensor leftSensor = new Sensor(ab);
        addComponent(new SensorComponent(this, leftSensor));

        //Enable Listening for Sensor Events
        addComponent(new SensorHandlerComponent(this));

        CheckpointScript c = new CheckpointScript(leftSensor);
        ScriptComponent sComp = new ScriptComponent(this);
        sComp.add(c);

        addComponent(sComp);
    }

    private class FlagSprite extends AnimatedSprite {
        private FlagSprite(Vector dimensions, int frameTick){
            super(new Vector(0,0), dimensions, frameTick);

            String state = "normal";
            String[] resources = new String[]{
                    "flag-green",
                    "flag-green2"
            };
            addResources(state, Arrays.asList(resources));

            setState("normal");
        }
    }

    private class CheckpointScript implements Script {
        private final Sensor sensor;

        private boolean entered = false; // Todo sort out a way to reset a scene

        /**
         * Create a LevelChangeScript
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
                        currentDefault.setSpawn(entity.getTransform());
                    }
                }
            }
        }
    }
}
