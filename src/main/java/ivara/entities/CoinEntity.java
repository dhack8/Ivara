package ivara.entities;

import core.Script;
import core.components.*;
import core.entity.GameEntity;
import core.struct.*;
import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;
import maths.Vector;
import physics.AABBCollider;

import java.util.Arrays;

/**
 * This class handles the behaviour and setup of a CoinEntity within the game.
 * @author Callum Li
 */
public class CoinEntity extends GameEntity {

    // Constants
    private final static int ANIMATION_RATE = 100;
    private final static float SIZE = 0.6f;

    private static final Sound coinPickup = TinySound.loadSound("coin.wav");

    /**
     * Constructs a CoinEntity.
     * The constructor takes a player to update the coin count of, a position, and whether to snap to the closest block.
     * @param player The player to update coins of.
     * @param transform The location.
     * @param snapToGrid Whether to snap to the closest grid.
     */
    public CoinEntity(PlayerEntity player, Vector transform, boolean snapToGrid) {
        super(transform);
        if (snapToGrid)
            snapToGrid(transform);

        // Layer
        addComponent(new RenderComponent(this, 999999999));

        // Sprite
        addComponent(new SpriteComponent(
                this,
                new CoinSprite(
                        new Vector(0, 0),
                        new Vector(SIZE, SIZE),
                        ANIMATION_RATE
                )
                )
        );

        // Sensors
        Sensor coinSensor = new Sensor(
                new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), new Vector(SIZE, SIZE))
        );
        addComponent(new SensorComponent(
                this,
                coinSensor
        ));
        SensorHandlerComponent sensorHandler = new SensorHandlerComponent(this);
        addComponent(sensorHandler);

        // Scripts
        CoinEntity coinEntity = this;
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
                                getScene().removeEntity(coinEntity);
                            }
                        }
                    }
                })
        );
    }

    /**
     * Snaps the entity to a centered position.
     * @param transform The location of the entity.
     */
    private void snapToGrid(Vector transform){
        transform.x = transform.x + (1f - SIZE)/2f;
        transform.y = transform.y + (1f - SIZE)/2f;
    }

    /**
     * Animated coin sprite.
     * @author David Hack
     */
    public class CoinSprite extends AnimatedSprite {
        /**
         * Constructs the sprite to represent the coin.
         * This coin sprite changes over frame ticks.
         * @param transform The location.
         * @param dimensions The size of the coin sprite.
         * @param frameTick The update tick rate.
         */
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
