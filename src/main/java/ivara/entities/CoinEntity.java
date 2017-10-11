package ivara.entities;

import core.Script;
import core.components.*;
import core.entity.GameEntity;
import core.input.SensorHandler;
import core.struct.ResourceID;
import core.struct.Sensor;
import core.struct.Sprite;
import maths.Vector;
import physics.AABBCollider;
import util.Debug;

/**
 * Created by Callum Li on 10/12/17.
 */
public class CoinEntity extends GameEntity {
    public CoinEntity(Vector transform, PlayerEntity player) {
        super(transform);

        addComponent(new SpriteComponent(
                this,
                new Sprite(
                        new ResourceID("player"),
                        new Vector(0, 0),
                        new Vector(1, 1.5f)
                )
                )
        );

        Sensor coinSesnor = new Sensor(
                new AABBCollider(AABBCollider.MIN_DIM, new Vector(0, 0), new Vector(1, 1.5f))
        );

        addComponent(new SensorComponent(
                this,
                coinSesnor
        ));

        SensorHandlerComponent sensorHandler = new SensorHandlerComponent(this);
        addComponent(sensorHandler);

        addComponent(new ScriptComponent(
                this,
                new Script() {
                    @Override
                    public void update(int dt, GameEntity entity) {
                        if (sensorHandler.getSensorHandler().isActive(coinSesnor)) {
                            if (sensorHandler.getSensorHandler().
                                    getActivatingEntities(coinSesnor)
                                    .stream()
                                    .anyMatch((e) -> e.equals(player))) {
                                Debug.log("Coin Sensor Activated");
                            }
                        }
                    }
                })
        );
    }
}
