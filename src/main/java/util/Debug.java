package util;

/**
 * Basic debug class with room for expansion.
 * @author Callum Li
 */
public class Debug {
    //Whether to print debug
    private static final boolean ON = false;

    /**
     * Prints a string. only if debug is on
     * @param s string to print
     */
    public static void log(String s) {
        if(ON) System.out.println(s);
    }

    /**
     * Prints a object using to string. only if debug is on.
     * @param o object to print out
     */
    public static void log(Object o) {
        if(ON) System.out.println(o.toString());
    }
}
