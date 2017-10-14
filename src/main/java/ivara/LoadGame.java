package ivara;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by Alex Mitchell on 14/10/2017.
 */
public class LoadGame {
    public static final String fileName = "./savefile.sav";

    public static void load(){
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        br.readLine()
    }
}
