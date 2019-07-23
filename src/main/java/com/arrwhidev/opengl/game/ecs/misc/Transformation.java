package com.arrwhidev.opengl.game.ecs.misc;

import com.arrwhidev.opengl.engine.Camera;
import com.arrwhidev.opengl.game.ecs.components.movement.Movement;
import com.arrwhidev.opengl.game.ecs.components.position.Position;
import org.joml.Matrix4f;

public class Transformation {

    private static final Matrix4f MODEL_VIEW_MATRIX = new Matrix4f();

    public static Matrix4f getModelMatrix(Camera camera, Position position, Movement movement, float interp) {

        float newX = ((position.x() - position.prevX()) * interp) + position.x();
        float newY = ((position.y() - position.prevY()) * interp) + position.y();

        return MODEL_VIEW_MATRIX.identity()
                .mul(camera.getProjection())
                .translate(newX, newY,0)
                .scale(position.getScale());


        // TODO: Add rotation here when implemented in game objects.
        // .rotateX((float)Math.toRadians(-rotation.x))
        // .rotateY((float)Math.toRadians(-rotation.y))
    }
}
