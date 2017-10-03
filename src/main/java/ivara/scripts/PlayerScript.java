package ivara.scripts;

import core.Script;
import core.SensorListener;
import core.components.SensorHandlerComponent;
import core.components.VelocityComponent;
import core.entity.GameEntity;
import core.input.Constants;
import core.input.InputHandler;
import core.input.SensorHandler;
import core.struct.Sensor;
import ivara.entities.BulletEntity;
import ivara.entities.PlayerEntity;
import maths.Vector;
import physics.AABBCollider;

/**
 * Script to control the player entity. Relies on the current input
 * stored in InputHandler to determine control.
 * Assumes y axis goes top down and x axis goes left right.
 * @author Alex Mitchell
 * @author Will Pearson
 */
public class PlayerScript implements Script, SensorListener {

    private float metresPerSecond = 3f;

    private final PlayerEntity player;
    private final Sensor bottomSensor;


    /**
     * Creates a player script without a bottom sensor,
     * exists for backwards compatibility with the sensorComponentTest.
     * @param player The player this script belongs to.
     * @deprecated Prefer other constructors for PlayerScript.
     */
    public PlayerScript(PlayerEntity player) {
        this.player = player;
        this.bottomSensor = new Sensor(new AABBCollider(0, new Vector(0, 0), new Vector(0, 0)));
    }

    public PlayerScript(PlayerEntity player, Sensor bottomSensor) {
        this.player = player;
        this.bottomSensor = bottomSensor;
    }

    /**
     * Updates the player entity.
     * @param dt elapsed milliseconds since last update
     */
    @Override
    public void update(int dt, GameEntity entity) { // Todo change how these are handled -- temp fix for the removal of translate
        SensorHandler sensorHandler = entity.get(SensorHandlerComponent.class).get().getSensorHandler();
        if (sensorHandler.isActive(bottomSensor)) {
            GameEntity entity1 = sensorHandler.getActivatingEntities(bottomSensor).stream().findAny().get();
            //System.out.println("Hello World");
            onActive(bottomSensor, entity1);
        }

        InputHandler input = entity.getInput();


        float speed = metresPerTick(dt);

        PlayerEntity pEntity = (PlayerEntity) entity;
        VelocityComponent vComp = pEntity.get(VelocityComponent.class).get();

        if (input.isKeyPressed(Constants.W)) {
            //System.out.println("UP");
            if (pEntity.canJump) {
                vComp.setY(-7f);
                pEntity.canJump = false;
            }
        } else if (input.isKeyPressed(Constants.S)) {
            vComp.setY(3f);
        }

        if (input.isKeyPressed(Constants.A)) {
            vComp.setX(-3f);
        } else if (input.isKeyPressed(Constants.D)) {
            vComp.setX(3f);
        } else {
            vComp.setX(0f);
        }

        if (input.isMousePressed(Constants.LEFT_MOUSE)) {

            entity.getScene().addEntity(new BulletEntity(entity.transform, input.getMousePosition(), 1000));
        }


    }

    /**
     * Calculates the metres travelled per game tick.
     * @param dmt milliseconds since last game tick
     * @return metres travelled
     */
    private float metresPerTick(long dmt) {
        return metresPerSecond / 1000f * dmt;
    }

    @Override
    public void onEnter(Sensor sensor, GameEntity entity) {

    }

    @Override
    public void onActive(Sensor sensor, GameEntity entity) {
        player.canJump = true;
        VelocityComponent v = player.get(VelocityComponent.class).get();
        Vector c = entity.get(VelocityComponent.class)
                .map(VelocityComponent::getVelocity)
                .orElse(new Vector(0, 0));
        v.setX(c.x);
        v.setY(c.y);

    }

    @Override
    public void onExit(Sensor sensor, GameEntity entity) {

    }
}
