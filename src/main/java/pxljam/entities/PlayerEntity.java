package pxljam.entities;

import core.components.ColliderComponent;
import core.components.PSpriteComponent;
import core.components.ScriptComponent;
import core.scene.Entity;
import maths.Vector;
import pxljam.scripts.PlayerController;

/**
 * This class handles the entity of the player within the game
 */
public class PlayerEntity extends Entity{
    private PSpriteComponent sprite;
    private ScriptComponent script;
    private ColliderComponent collider;


    /**
     * Creates a PlayerEntity object
     * @param vector The position of the entity
     * @param sprite The relative display of the player
     * @param script The controller of the player
     * @param collider The collision aspect of the player
     */
    public PlayerEntity(Vector vector, PSpriteComponent sprite, ScriptComponent script, ColliderComponent collider) {
        super(vector);
        this.sprite = sprite;
        this.script = script;
        this.collider = collider;
    }

    /**
     * Creates a PlayerEntity at a given position
     * @param x The x position
     * @param y The y position
     */
    public PlayerEntity(float x, float y){
        super(new Vector(x,y));
        this.sprite = new PSpriteComponent();
        this.script = new PlayerController(this);
        this.collider = new ColliderComponent();
    }
}


