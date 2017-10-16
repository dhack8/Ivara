package ivara.entities;

import backends.tinysound.Sound;
import backends.tinysound.TinySound;
import core.Script;
import core.components.*;
import core.entity.GameEntity;
import core.struct.*;
import maths.Vector;
import physics.AABBCollider;

import java.util.Arrays;

/**
 * Created by Callum Li on 10/12/17.
 */
public class CoinEntity extends GameEntity {

    private final static int ANIMATION_RATE = 100;
    private final static float SIZE = 0.6f;

    private static final Sound coinPickup = TinySound.loadSound("coin.wav");

    public CoinEntity(PlayerEntity player, Vector transform, boolean snapToGrid) {
        super(transform);
        if (snapToGrid)
            snapToGrid(transform);

        addComponent(new RenderComponent(this, 999999999));

        CoinEntity coinEntity = this;

        addComponent(new SpriteComponent(
                this,
                new CoinSprite(
                        new Vector(0, 0),
                        new Vector(SIZE, SIZE),
                        ANIMATION_RATE
                )
                )
        );

        Sensor coinSensor = new Sensor(
                new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), new Vector(SIZE, SIZE))
        );

        addComponent(new SensorComponent(
                this,
                coinSensor
        ));

        SensorHandlerComponent sensorHandler = new SensorHandlerComponent(this);
        addComponent(sensorHandler);

        addComponent(new ScriptComponent(
                this,
                new Script() {
                    @Override
                    public void update(int dt, GameEntity entity) {
                        if (sensorHandler.getSensorHandler().isActive(coinSensor)) {
                            if (sensorHandler.getSensorHandler().
                                    getActivatingEntities(coinSensor)
                                    .stream()
                                    .anyMatch((e) -> e.equals(player))) {
                                coinPickup.play();
                                player.coinsCollected += 1;
                                getScene().removeEntity(coinEntity);

                                //getScene().addTimer(new Timer(1000, () -> getScene().addEntity(new CoinEntity(coinEntity.transform, player))));
                            }
                        }
                    }
                })
        );
    }

    private void snapToGrid(Vector transform){
        transform.x = transform.x + (1f - SIZE)/2f;
        transform.y = transform.y + (1f - SIZE)/2f;
    }

    /**
     * Animated coin sprite.
     * @author David Hack
     */
    public class CoinSprite extends AnimatedSprite {
        public CoinSprite(Vector transform, Vector dimensions, int frameTick){
            super(transform, dimensions, frameTick);

            String state = "normal";
            String[] resources = new String[] {
                    "coin",
                    "coin2",
                    "coin3",
                    "coin4",
                    "coin5",
                    "coin6",
                    "coin7",
                    "coin8",
            };
            addResources(state, Arrays.asList(resources));

            setState("normal");
        }
    }
}
