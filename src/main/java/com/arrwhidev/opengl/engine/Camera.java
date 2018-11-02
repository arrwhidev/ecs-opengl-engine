package com.arrwhidev.opengl.engine;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Camera {

    private Vector2f position;
    private Matrix4f projection;
    private float scale;

    public Camera(Window window) {
        scale = 1;
        position = new Vector2f(0,0);
        projection = new Matrix4f().setOrtho2D(0, window.getWidth(), window.getHeight(), 0); // top left
    }

    public void zoomIn() {
        this.scale *= 1.1f;
    }

    public void zoomOut() {
        this.scale *= 0.9f;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void setPosition(Vector2f pos) {
        position = pos;
    }

    public void addPosition(Vector2f pos) {
        position.add(pos);
    }

    public Vector2f getPosition() {
        return position;
    }

    public Matrix4f getProjection() {
        Matrix4f target = new Matrix4f();
        Matrix4f pos = new Matrix4f()
                .scale(scale)
                .setTranslation(new Vector3f(position.x, position.y, 0));

        target = projection.mul(pos, target);
        return target;
    }
}
