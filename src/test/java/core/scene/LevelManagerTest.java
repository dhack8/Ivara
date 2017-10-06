package core.scene;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Alex Mitchell
 */
public class LevelManagerTest {
    private LevelManager lm;

    @Test
    /**
     * Testing that a scene cannot be constructed with a null scene
     */
    public void testConstruction1(){
        Scene s = null;
        try{
            lm = new LevelManager(s);
            fail("Should not have been able to construct a levelmanager with a null scene.");
        }catch(IllegalArgumentException e){

        }
    }

    @Test
    /**
     *
     */
    public void testConstructor2(){

    }
}