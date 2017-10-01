package core.components;

import core.entity.GameEntity;
import core.struct.ResourceID;
import core.struct.Sensor;
import core.struct.Sprite;
import ivara.entities.PlayerEntity;
import ivara.scripts.PlayerScript;
import maths.Vector;
import org.junit.Before;
import org.junit.Test;
import physics.AABBCollider;

import static org.junit.Assert.*;

/**
 * Test for the SensorComponent.
 * @author David Hack
 */
public class SensorComponentTest {

    GameEntity testEntity;

    /**
     * Sets up game entity.
     */
    @Before
    public void setup() {
        testEntity = new GameEntity(new Vector(0,0)){};
    }

    /**
     * Test the single sensor constructor.
     * @throws Exception
     */
    @Test
    public void singleConstruct() throws Exception{
        Sensor testSensor = new Sensor(new AABBCollider(new Vector(0,0), new Vector(0,0)), new PlayerScript(new PlayerEntity(0,0)));
        SensorComponent sc = new SensorComponent(testEntity, testSensor);
        assertEquals(testSensor, sc.getSensors().get(0));
    }

    /**
     * Test the array sensor constructor.
     * @throws Exception
     */
    @Test
    public void arrayConstruct() throws Exception{
        Sensor testSensor1 = new Sensor(new AABBCollider(new Vector(0,0), new Vector(0,0)), new PlayerScript(new PlayerEntity(0,0)));

        Sensor testSensor2 = new Sensor(new AABBCollider(new Vector(0,0), new Vector(0,0)), new PlayerScript(new PlayerEntity(0,0)));

        SensorComponent sc = new SensorComponent(testEntity, new Sensor[]{
                testSensor1,
                testSensor2
        });

        assertEquals(testSensor1, sc.getSensors().get(0));
        assertEquals(testSensor2, sc.getSensors().get(1));
    }


    @Test
    public void add() throws Exception {
        Sensor testSensor = new Sensor(new AABBCollider(new Vector(0,0), new Vector(0,0)), new PlayerScript(new PlayerEntity(0,0)));
        SensorComponent sc = new SensorComponent(testEntity);
        sc.add(testSensor);
        assertEquals(testSensor, sc.getSensors().get(0));
    }
}