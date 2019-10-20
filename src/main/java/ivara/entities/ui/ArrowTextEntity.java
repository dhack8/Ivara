package ivara.entities.ui;

import core.Script;
import core.components.RenderComponent;
import core.components.ScriptComponent;
import core.components.SpriteComponent;
import core.components.TextComponent;
import core.entity.GameEntity;
import core.struct.ResourceID;
import core.struct.Sprite;
import core.struct.Text;
import ivara.entities.PlayerEntity;
import ivara.scenes.Level;
import maths.Vector;

public class ArrowTextEntity extends GameEntity {

    private static final float TEXT_SIZE = 25;
    private static final Vector OFFSET = new Vector(-70f,-20f);

    private TextComponent arrows;

    /**
     * Constructs a new game entity, with a transform, required for there is no default constructor.
     *
     * @param transform transform/location of entity
     */
    public ArrowTextEntity(Vector transform, PlayerEntity player) {
        super(transform);

        // Text
        arrows = new TextComponent(this, new Text(TEXT_SIZE, "0"));
        addComponent(arrows);

        // Sprite
        addComponent(new SpriteComponent(this, new Sprite(new ResourceID("arrow"), OFFSET, new Vector(1f, 0.6f))));

        // Script
        addComponent(new ScriptComponent(
                this,
                new Script() {
                    @Override
                    public void update(int dt, GameEntity entity) {
                        int fired = player.getArrowsFired();
                        int total = PlayerEntity.getCrossbowQuiverSize();

                        arrows.clear();
                        arrows.add(total-fired + "/" + total, TEXT_SIZE);
                    }
                })
        );

        addComponent(new RenderComponent(this, 999999999, RenderComponent.Mode.PIXEL_NO_TRANS));
    }
}
