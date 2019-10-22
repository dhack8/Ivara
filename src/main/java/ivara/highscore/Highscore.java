package ivara.highscore;

import java.util.Objects;

public class Highscore {
    private String name;
    private long time;

    public Highscore(String name, long time) {
        this.name = name;
        this.time = time;
    }

    // DO NOT REMOVE
    public Highscore(){

    }

    public String getName() {
        return name;
    }

    public long getTime() {
        return time;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof Highscore)) return false;
        Highscore hs = (Highscore) obj;

        return this.name.equals(hs.name) && this.time == hs.time;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.time);
    }

    @Override
    public String toString() {
        return name + " " + time;
    }
}
