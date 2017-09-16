package serialisation;

import core.scene.Camera;
import core.scene.Entity;
import core.scene.Scene;
import maths.Vector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author Alex Mitchell
 */
public class SceneFromFile {
    private static final String filePath = "./SaveGame.txt";

    public static Scene read(){

        Camera cam;
        Set<Entity> entities;


        BufferedReader read;


        try{

            read = new BufferedReader(new FileReader(filePath));

            //Reading the camera
            Vector location;
            Vector dimension;
            float scale;

            String[] locationInfo = read.readLine().split(" ");
            location = new Vector(locationInfo[0], location[1])
            String[] dimensionInfo = read.readLine().split(" ");
            String[] scaleInfo = read.readLine().split(" ");



        }catch (IOException e){

        }

    }
}
