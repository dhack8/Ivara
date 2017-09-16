package core;

import processing.core.PImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by james on 16/09/2017.
 */
public class AssetHandler {
    
    private static Map<String, PImage> images = new HashMap<>();

    /**
     * Links a specified image to a resource identifier (ID) and puts into the map of images.
     * @param file file path where image is located
     * @param resourceID name linked to the image, for retrieval purposes
     * @throws RuntimeException if no file is found/loaded, or unable to be assigned and added into map.
     */
    public static void loadImage(String file, String resourceID) throws RuntimeException{
//        PImage imageToLoad = game.loadImage("/assets/player.png");
        PImage imageToLoad;
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(file));
            imageToLoad = new PImage(img);
            images.put(resourceID, imageToLoad);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage() + e.getCause());
        }
    }

    /**
     * Retrieves the image linked to a resource identifier (ID) from the map of all images.
     * @param resourceID identifier linked to image
     * @return desired PImage linked to identifier
     * @throws RuntimeException if no image is retrieved.
     */
    public static PImage getImage(String resourceID){
        try{
            return images.get(resourceID);
        }catch(Exception e){
            throw new RuntimeException("File(" + resourceID + ") not found in the AssetHandler! MESSAGE: " + e.getMessage() + " CAUSE: " + e.getCause());
        }
    }

    /**
     * Gets map of images
     * @return map of all images
     */
    public Map<String, PImage> getImageMap(){
        return images;
    }
}
