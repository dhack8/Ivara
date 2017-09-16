package pxljam.entities;

import core.components.ColliderComponent;
import core.components.PSpriteComponent;
import core.components.ScriptComponent;
import core.scene.Entity;
import maths.Vector;
import pxljam.scripts.PlayerController;

/**
 * This class handles the entity of the player within the game
 * @author Alex Mitchell
 */
public class PlayerEntity extends Entity{
    private PSpriteComponent sprite;
    private ScriptComponent script;
    private ColliderComponent collider;

    /**
     * Creates a PlayerEntity at a given position
     * @param x The x position
     * @param y The y position
     */
    public PlayerEntity(float x, float y){
        super(new Vector(x,y));
        this.sprite = new PSpriteComponent(this, "player"); //Todo change the PSprite component
        this.script = new PlayerController(this);
        this.collider = new ColliderComponent(this, null); //Todo Change the Collider component
    }
}


