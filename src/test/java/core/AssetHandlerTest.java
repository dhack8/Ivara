package core;

import backends.processing.PWindow;
import org.junit.Before;
import org.junit.Test;
import processing.core.PApplet;
import processing.core.PImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by james on 16/09/2017.
 */
public class AssetHandlerTest {

    String filePath;
    PApplet renderer;
    PImage img;

    @Before
    public void setUp() throws Exception {
        filePath = "./assets/player.png";
        try {
            img = new PImage(ImageIO.read(new File(filePath)));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage() + e.getCause());
        }
        renderer = new PWindow(false){
            public PImage loadImage(String filename) {
                return img;
            }
        };
    }

    /**
     * Adds an image file, along with a String identifier, into the map within the AssetHandler class to test the
     * loadImage method. Asserts that the image retrieved is correct.
     * @throws RuntimeException
     */
    @Test
    public void loadImage() throws RuntimeException { //TODO runtime exception
        try {
            AssetHandler.loadImage(filePath, "Player", renderer);
            assertTrue(AssetHandler.getImageMap().size() == 1);
        }catch(Exception e){
            throw new RuntimeException(e.getMessage() + e.getCause());
        }
        PImage test = AssetHandler.getImageMap().get("Player");
        assertEquals(img, test);
    }

    /**
     * Test for the getImage method. Tests the retrieval of the getImage method in AssetHandler by adding "player.png"
     * file into the map, and retrieving using the key. Asserts that the image loaded is not null, and that the image
     * created within the try/catch is equal to the same image but retrieved from the map.
     * @throws RuntimeException if image is unable to be loaded
     */
    @Test
    public void getImage() throws RuntimeException {
        AssetHandler.getImageMap().put("Player", img);
        assertEquals(img, AssetHandler.getImage("Player"));
    }

    /**
     * Test that attempts to get a non-existent image. Succeeds if exception is caught in try/catch, and image retrieved
     * with the identifier (resourceID), in this case "Player", is null.
     * @throws Exception
     */
    @Test
    public void getNonExistentImage() throws Exception{
        try{
            AssetHandler.getImage("Player");
            assert false;
        }catch(Exception e){
            assert true;
        }
        assertNull(AssetHandler.getImageMap().get("Player"));
    }

}