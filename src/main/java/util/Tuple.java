package util;

/**
 * Created by Callum Li on 9/29/17.
 */
public class Tuple<A, B> {
    private final A fst;
    private final B snd;

    public Tuple(A fst, B snd) {
        this.fst = fst;
        this.snd = snd;
    }
}
