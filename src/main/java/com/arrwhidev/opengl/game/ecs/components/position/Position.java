package com.arrwhidev.opengl.game.ecs.components.position;

import com.arrwhidev.opengl.engine.ecs.Component;
import org.joml.Vector2f;

public class Position implements Component {

    public Vector2f position;
    private int width, height;
    private float scale;

    public Position(float x, float y, int width, int height, float scale) {
        this.position = new Vector2f(x, y);
        this.width = width;
        this.height = height;
        this.scale = scale;
    }

    public Vector2f center() {
        float cx = left() + ((right() - left()) / 2);
        float cy = top() + ((bottom() - top()) / 2);
        return new Vector2f(cx, cy);
    }

    public void add(Vector2f pos) {
        position.add(pos);
    }

    public void sub(Vector2f pos) {
        position.sub(pos);
    }

    public float x() {
        return position.x;
    }

    public float y() {
        return position.y;
    }

    public float left() {
        return position.x;
    }

    public float right() {
        return position.x + width * scale;
    }

    public void setLeft(float left) {
        position.x = left;
    }

    public void setRight(float right) {
        position.x = right - (width * scale);
    }

    public void setTop(float top) {
        position.y = top;
    }

    public void setBottom(float bottom) {
        position.y = bottom - (height * scale);
    }

    public float top() {
        return position.y;
    }

    public float bottom() {
        return position.y + height * scale;
    }

    public float getScale() {
        return scale;
    }

    public float getWidth() {
        return width * scale;
    }

    public float getHeight() {
        return height * scale;
    }

//    public boolean isFacingRight() {
//        return facingRight;
//    }
//
//    public boolean isFacingLeft() {
//        return !facingRight;
//    }
//
//    public void turnAround() {
//        facingRight = !facingRight;
//    }
//
//    public void faceLeft() {
//        facingRight = false;
//    }
//
//    public void faceRight() {
//        facingRight = true;
//    }
}
