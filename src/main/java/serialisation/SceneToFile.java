package serialisation;

import core.scene.Camera;
import core.entity.Entity;
import core.scene.Scene;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collection;

/**
 * Class to save the current scene state to an external file SaveGame.txt
 * Created by james on 16/09/2017.
 */
public class SceneToFile {

    private Scene scene;
    private Camera camera;
    private Collection<Entity> entities;

    public SceneToFile(Scene sc){
        this.scene = sc;
        this.camera = scene.getCamera();
        entities = scene.getEntities();
    }

    /**
     * The method that does all the writing to the external file.
     * Collects the camera fields (location, direction vectors and camera scale) and the entities' class along with
     * their values. Uses BufferedWriter to output to file.
     * @throws IOException
     */
    public void write() throws IOException {
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
//            fw = new FileWriter("./SaveGame.txt");
            fw = new FileWriter(new File("./SaveGame.txt"));
            bw = new BufferedWriter(fw);

            //get camera location, dimension, scale values
            Field[] cameraFields = camera.getClass().getDeclaredFields();

            bw.write(cameraFields[0].getName() + " ");
            bw.write(Float.toString(camera.getLocation().x) + " ");
            bw.write(Float.toString(camera.getLocation().x) + " ");
            bw.newLine();
            bw.write(cameraFields[1].getName() + " ");
            bw.write(Float.toString(camera.getDimension().x) + " ");
            bw.write(Float.toString(camera.getDimension().x) + " ");
            bw.newLine();
            bw.write(cameraFields[2].getName() + " ");
            bw.write(Float.toString(camera.getScale()));
            bw.newLine();

            int count = 1;
            for(Entity e : entities){
                //bw.write(e.getClass().getSimpleName());
                bw.write(Integer.toString(count) + " ");
                bw.write(e.getClass().getName());
                bw.newLine();
                count++;
                //format: name | value
            }

            //iterate through all entities
            //get class (as name)
            //check constructor


        } catch (IOException e) {
            e.printStackTrace();
        }

        //closing writers
        try {
            //if (bw != null)
            bw.flush();

            bw.close();

           // if (fw != null)
            fw.close();

        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }
}
