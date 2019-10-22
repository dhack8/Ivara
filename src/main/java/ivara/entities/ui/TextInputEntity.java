package ivara.entities.ui;

import core.Script;
import core.components.ScriptComponent;
import core.components.TextComponent;
import core.entity.GameEntity;
import core.input.Constants;
import core.input.InputHandler;
import core.struct.Text;
import maths.Vector;

import java.util.HashSet;
import java.util.Set;

public class TextInputEntity extends GameEntity {
    private String textInput = "";
    private TextComponent textComponent;
    private int textSize;

    /**
     * Constructs a new game entity, with a transform, required for there is no default constructor.
     *
     * @param transform transform/location of entity
     */
    public TextInputEntity(Vector transform, int maxChars, int textSize) {
        super(transform);
        this.textSize = textSize;
        textComponent = new TextComponent(this, new Text(textSize, textInput));
        addComponent(textComponent);
        addComponent(new ScriptComponent(this, new TextScript(maxChars)));
    }

    public String getText(){
        return textInput;
    }

    public class TextScript implements Script {

        private Set<Character> priorPressed;
        private int maxChars;

        public TextScript(int maxChars) {
            priorPressed = new HashSet<>();
            this.maxChars = maxChars;
        }

        @Override
        public void update(int dt, GameEntity entity) {
            InputHandler.InputFrame input = getInput();
            Set<Character> pressedKeys = input.getPressedKeys();
            Set<Character> releasedKeys = input.getReleasedKeys();

            priorPressed.removeAll(releasedKeys);

            if(pressedKeys.size() != 1) return;
            Character newChar = pressedKeys.stream().findFirst().get();

            if(!(Character.isLetter(newChar) || Character.isSpaceChar(newChar) || ((int) newChar) == Constants.BACKSPACE)) return;
            if(priorPressed.contains(newChar)) return;

            if(((int) newChar) == Constants.BACKSPACE && textInput.length() > 0) textInput = textInput.substring(0, textInput.length()-1);
            else if(textInput.length() != maxChars) textInput += newChar;

            textComponent.clear();
            textComponent.add(textInput, textSize);

            priorPressed.add(newChar);
        }
    }
}
