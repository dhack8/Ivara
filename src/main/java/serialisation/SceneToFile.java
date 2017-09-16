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

            for(int i = 0; i < cameraFields.length; i++){
                System.out.printf("%d: %s\n", i+1, cameraFields[i].getName());
                bw.write(cameraFields[i].getName() + " "); //TODO REMOVE AS LOADING DOESNT USE FIELD NAMES
                //bw.write(cameraFields[i].get) TODO get camera field values
                bw.newLine();
            }


            for(Entity e : entities){
                System.out.println(e.getClass());
            }

            //iterate through all entities
            //get class (as name)
            //check constructor
            entities.getClass().getDeclaredFields();


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
