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
            cam = readCamera(read);






        }catch (IOException e){

        }

        return null;
    }

    private static Camera readCamera(BufferedReader reader) throws IOException{
        Vector location;
        Vector dimension;
        float scale;

        String[] locationInfo = reader.readLine().split(" ");
        location = new Vector(Float.parseFloat(locationInfo[0]), Float.parseFloat(locationInfo[1]));
        String[] dimensionInfo = reader.readLine().split(" ");
        dimension = new Vector(Float.parseFloat(dimensionInfo[0]), Float.parseFloat(dimensionInfo[1]));
        String[] scaleInfo = reader.readLine().split(" ");
        scale = Float.parseFloat(scaleInfo[0]);

        return new Camera(location, dimension, scale);
    }

    private static Camera readEntity(BufferedReader reader) throws IOException{
        Vector location;
        Vector dimension;
        float scale;

        String[] locationInfo = reader.readLine().split(" ");
        location = new Vector(Float.parseFloat(locationInfo[0]), Float.parseFloat(locationInfo[1]));
        String[] dimensionInfo = reader.readLine().split(" ");
        dimension = new Vector(Float.parseFloat(dimensionInfo[0]), Float.parseFloat(dimensionInfo[1]));
        String[] scaleInfo = reader.readLine().split(" ");
        scale = Float.parseFloat(scaleInfo[0]);

        return new Camera(location, dimension, scale);
    }
}
