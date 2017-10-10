package ivara.entities.scripts;

import core.Script;
import core.entity.GameEntity;
import core.input.InputHandler;
import core.struct.ResourceID;
import core.struct.Timer;
import ivara.entities.BulletEntity;
import maths.Vector;

/**
 * Created by Alex Mitchell on 10/10/2017.
 */
public class ShootScript implements Script{
    private GameEntity entity;
    private GameEntity target;
    private Timer t; // delay timer
    private final int DELAY = 1000; // how long between each shot
    private final int DURATION = 1000; // how long till the bullet resets
    private final int TIME_TO_TARGET = 1000; // how long it takes to reach the player position
    private final float SPEED = 1f; // in ms^-1

    public ShootScript(GameEntity entity, GameEntity target){
        this.entity = entity;
        this.target = target;
        t = new Timer(0, ()->{});
    }

    @Override
    public void update(int dt, GameEntity entity) {


        if(t.isFinished()){

            t = new Timer(
                    DELAY,
                    ()->{
                        GameEntity bullet = new BulletEntity(entity.transform, target.getTransform(), new ResourceID("slimeball"), TIME_TO_TARGET); // Todo change this as speed won't be constant
                        entity.getScene().addEntity(bullet);
                        entity.getScene().addTimer(new Timer(DURATION, ()->entity.getScene().removeEntity(bullet)));
                    }
            );
            entity.getScene().addTimer(t);
        }
    }

}
