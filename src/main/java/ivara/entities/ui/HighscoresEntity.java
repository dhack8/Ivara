package ivara.entities.ui;

import core.Script;
import core.components.ScriptComponent;
import core.components.TextComponent;
import core.entity.GameEntity;
import core.struct.Text;
import ivara.highscore.Highscore;
import ivara.highscore.HighscoreDatabaseAdapter;
import ivara.util.TimeFormat;
import maths.Vector;

import java.util.*;
import java.util.stream.Collectors;

public class HighscoresEntity extends GameEntity {
    private static Map<String, List<Highscore>> HIGHSCORE_CACHE = new HashMap<>();

    private String levelName;
    private List<Highscore> highscores;
    private TextComponent textComponent;
    private boolean loaded;

    public HighscoresEntity(Vector transform) {
        super(transform);
        this.levelName = levelName;
        this.highscores = new ArrayList<>();
        this.loaded = false;

        ScriptComponent scriptComponent = new ScriptComponent(this);
        scriptComponent.add(new HighscoreDataScript());
        scriptComponent.add(new HighscoreTextScript());
        addComponent(scriptComponent);

        textComponent = new TextComponent(this);
        addComponent(textComponent);
    }


    public class HighscoreDataScript implements Script {

        private static final int MAX_RETRY_ATTEMPTS = 3;

        private int attempts;
        private boolean canRetry;

        public HighscoreDataScript() {
            this.attempts = 0;
            this.canRetry = true;
        }

        @Override
        public void update(int dt, GameEntity entity) {

            if(highscores.isEmpty()){
                if (HIGHSCORE_CACHE.containsKey(levelName)) {
                    highscores = HIGHSCORE_CACHE.get(levelName);
                }else {

                }
            }

            if(loaded || (attempts >= MAX_RETRY_ATTEMPTS && canRetry)) return;

            canRetry = false;

            HighscoreDatabaseAdapter.getHighScores(levelName, (scores)-> {
                List<Highscore> top10 = scores.stream()
                        .sorted(Comparator.comparingLong(Highscore::getTime))
                        .limit(10)
                        .collect(Collectors.toList());

                highscores = top10;

                HIGHSCORE_CACHE.put(levelName, new ArrayList<>(highscores));

                loaded = true;
                canRetry = true;
            }, (t) -> {
                t.printStackTrace();
                canRetry = true;
            });

            attempts++;
        }
    }

    public class HighscoreTextScript implements Script {

        @Override
        public void update(int dt, GameEntity entity) {
            textComponent.clear();

            if(!loaded){
                textComponent.add(new Text(new Vector(12.375f, 4f), 40f, "Loading highscores..."));
                return;
            }else if(highscores.isEmpty()){
                textComponent.add(new Text(new Vector(12.375f, 4f), 40f, "There are no highscores"));
                return;
            }

            for (int i = 0; i < highscores.size(); i++) {
                Highscore hs = highscores.get(i);
                String name = hs.getName().equals("") ? "No name" : hs.getName();
                String time = TimeFormat.formatString(hs.getTime());
                textComponent.add(new Text(new Vector(12.375f, (i * 0.5f) + 4f), 40f, name));
                textComponent.add(new Text(new Vector(17.65f, (i * 0.5f) + 4f), 40f, time));
            }
        }
    }
}
