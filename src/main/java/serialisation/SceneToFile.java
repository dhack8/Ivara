package serialisation;

import core.scene.Camera;
import core.scene.Entity;
import core.scene.Scene;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Set;

/**
 * Created by james on 16/09/2017.
 */
public class SceneToFile {

    private Scene scene;
    private Camera camera;
    private Set<Entity> entities;

    public SceneToFile(Scene sc){
        this.scene = sc;
        this.camera = scene.getCamera();
        entities = scene.getEntities();
    }

    public void write() {
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
                bw.write(e.getClass().getName());
                bw.newLine();
                bw.write(Integer.toString(count));
                count++;
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
            //fw.flush();

            fw.close();

        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

}
