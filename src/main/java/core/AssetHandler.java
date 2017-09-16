package core;

import processing.core.PApplet;
import processing.core.PConstants;
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

    //TODO: static fields? or better workaround available?
    private static Map<String, PImage> images;

    public AssetHandler(Game game){
        this.images = new HashMap<>();
    }


    public static void loadImage(String file, String resourceID) {
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

    public static PImage getImage(String resourceID) throws FileNotFoundException{
        try{
            return images.get(resourceID);
        }catch(Exception e){
            throw new FileNotFoundException();
        }
    }

    public Map<String, PImage> getImageMap(){
        return images;
    }
}
