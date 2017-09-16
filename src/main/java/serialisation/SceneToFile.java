package serialisation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Created by james on 16/09/2017.
 */
public class SceneToFile {



    public void write() {
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            fw = new FileWriter("./SaveGame.txt");
            bw = new BufferedWriter(fw);

            Field[] cameraFields = camera.getClass().getDeclaredFields();

            for(int i = 0; i < cameraFields.length; i++){
                System.out.printf("%d: %s\n", i+1, cameraFields[i].getName());
                bw.write(cameraFields[i].getName());
                bw.write(cameraFields[i].get)
            }






            //get camera location, dimension, scale
            //iterate through all entities
            //within buffered writer
            //get class (as name)
            //check constructor
            entities.getClass().getDeclaredFields();


        } catch (IOException e) {
            e.printStackTrace();
        }





        //closing writers
        try {
            if (bw != null)
                bw.close();

            if (fw != null)
                fw.close();

        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

}
