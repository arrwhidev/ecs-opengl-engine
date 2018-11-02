package com.arrwhidev.opengl.game.ecs.components.movement;

import com.arrwhidev.opengl.engine.ecs.Component;
import org.joml.Vector2f;

public class Movement implements Component {

    private Vector2f velocity;
    private Vector2f acceleration;
    private boolean facingRight = true;

    public Movement(Vector2f velocity, Vector2f acceleration) {
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    public Vector2f getVelocity() {
        return velocity;
    }

    public Vector2f getAcceleration() {
        return acceleration;
    }

    public void stop() {
        this.velocity.x = 0;
        this.velocity.y = 0;
        this.acceleration.x = 0;
        this.acceleration.y = 0;
    }

    public void setVelocity(Vector2f velocity) {
        this.velocity = velocity;
    }

    public boolean isFacingRight() {
        return facingRight;
    }

    public boolean isFacingLeft() {
        return !facingRight;
    }

    public void turnAround() {
        facingRight = !facingRight;
    }

    public void faceLeft() {
        facingRight = false;
    }

    public void faceRight() {
        facingRight = true;
    }
}
