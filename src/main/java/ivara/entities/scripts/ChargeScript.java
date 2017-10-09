package ivara.entities.scripts;

import core.Script;
import core.components.SensorHandlerComponent;
import core.components.VelocityComponent;
import core.entity.GameEntity;
import core.input.InputHandler;
import core.input.SensorHandler;
import core.struct.Sensor;
import core.struct.Timer;
import maths.Vector;

/**
 * This script floats aimlessly and then charges at the player's last position, unless a collision occurs
 * Created by Alex Mitchell on 9/10/2017.
 */
public class ChargeScript implements Script {
    private GameEntity thisEntity;
    private GameEntity toChase;
    private boolean chasing;
    private Sensor wholeSensor;
    private Vector initialPos;

    private final float SPEED = 2f; // 2 ms^-1 total (x + y component)

    public ChargeScript(GameEntity thisEntity, GameEntity toChase, Sensor wholeSensor){
        this.thisEntity = thisEntity;
        this.toChase = toChase;
        this.wholeSensor = wholeSensor;
        this.initialPos = new Vector(thisEntity.getTransform());
        chasing = false;

    }

    @Override
    public void update(int dt, GameEntity entity) {
        toChase.getScene().addTimer(new Timer(2000,
                !chasing?()->{chasing = true; chase();}:()->{chasing = false; hover();}
        ));


        SensorHandler sensorHandler = entity.get(SensorHandlerComponent.class).get().getSensorHandler();
        if(sensorHandler.isActive(wholeSensor)){
            entity.getTransform().set(initialPos); // on collision, set the position back to the start
            chasing = false;
        }
    }

    private void chase(){ // go get em b o i <3
        VelocityComponent vComp = thisEntity.get(VelocityComponent.class).get();
        Vector target = toChase.getTransform();
        Vector from = thisEntity.getTransform();
        vComp.set(getV(target, from));
    }

    private void hover(){// sit patiently :)
        VelocityComponent vComp = thisEntity.get(VelocityComponent.class).get();
        vComp.set(new Vector(0f,0f));
    }

    private Vector getV(Vector target, Vector from){
        float dx = target.x - from.x;
        float dy = target.y - from.y;
        double angle = Math.atan(dx/dy);
        float xVel = (float)(SPEED * Math.sin(angle));
        float yVel = (float)(SPEED * Math.cos(angle));

        if(dy <0){xVel *= -1; yVel *= -1;}
        return new Vector(xVel,yVel);
    }
}
