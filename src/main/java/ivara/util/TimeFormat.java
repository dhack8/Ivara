package ivara.util;

public class TimeFormat {
    public static String formatString(long time){
        long seconds = (time / 1000) % 60 ;
        long minutes = (time / (1000*60)) % 60;
        long millis = (time % 1000)/10;
        return String.format("%02d:%02d.%02d", minutes, seconds, millis);
    }
}
