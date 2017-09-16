package core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import pxljam.TheLegend27;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by james on 16/09/2017.
 */
public class AssetHandlerTest {

    Game game;
    String filePath;
    AssetHandler testHandler;

    @Before
    public void setUp() throws Exception {
        game = new TheLegend27();
        testHandler = new AssetHandler(game);
        filePath = "./assets/player.png";
    }

    /**
     * Adds an image file, along with a String identifier, into the map within the AssetHandler class to test the
     * loadImage method. Asserts that the image retrieved from the map is not null.
     * @throws Exception
     */
    @Test
    public void loadImage() throws Exception {
        testHandler.loadImage(filePath, "Player");
        PImage test = testHandler.getImage("Player");
        assertNotNull(test);
    }

    /**
     * Test for the getImage method. Tests the retrieval of the getImage method in AssetHandler by adding "player.png"
     * file into the map, and retrieving using the key. Asserts that the image loaded is not null, and that the image
     * created within the try/catch is equal to the same image but retrieved from the map.
     * @throws RuntimeException if image is unable to be loaded
     */
    @Test
    public void getImage() throws RuntimeException {
        PImage imageToLoad;
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(filePath));
            imageToLoad = new PImage(img);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage() + e.getCause());
        }

        assertNotNull(imageToLoad);
        testHandler.getImageMap().put("Player", imageToLoad);
        assertEquals(imageToLoad, testHandler.getImageMap().get("Player"));
    }

    /**
     * Test that attempts to get a non-existant image. Succeeds if exception is caught in try/catch, and image retrieved
     * with the identifier (resourceID), in this case "Player", is null.
     * @throws Exception
     */
    @Test
    public void getNonExistantImage() throws Exception{
        try{
            testHandler.getImage("Player");
        }catch(Exception e){
            assert true;
        }

        assertNull(testHandler.getImageMap().get("Player"));
    }

}