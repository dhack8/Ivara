package ivara.highscore;

import java.util.List;

public interface HighscoreCallback {
    /**
     * The highscores returned from the database
     * This entire interface be provided as a lambda, e.g. (data) -> {code utilizing the data variable}
     * @param highscores The highscore data
     */
    void callback(List<Highscore> highscores);
}
