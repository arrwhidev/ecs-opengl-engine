package com.arrwhidev.opengl.game.ecs.misc;

import com.arrwhidev.opengl.engine.Camera;
import com.arrwhidev.opengl.game.ecs.components.movement.Movement;
import com.arrwhidev.opengl.game.ecs.components.position.Position;
import org.joml.Matrix4f;

public class Transformation {

    private static final Matrix4f MODEL_VIEW_MATRIX = new Matrix4f();

    public static Matrix4f getModelMatrix(Camera camera, Position position, Movement movement) {
        // TODO: If facing left we need to flip the texture horizontally.
        // Is this the best way to achieve this?
        if(movement.isFacingLeft()) {
            return MODEL_VIEW_MATRIX.identity()
                .mul(camera.getProjection())
                .translate(position.x() + (position.getWidth() * position.getScale()), position.y(), 0)
                .scale(position.getScale())
                .scale(-1, 1 ,1);
        } else {
            return MODEL_VIEW_MATRIX.identity()
                .mul(camera.getProjection())
                .translate(position.x(), position.y(), 0)
                .scale(position.getScale());
        }

        // TODO: Add rotation here when implemented in game objects.
        // .rotateX((float)Math.toRadians(-rotation.x))
        // .rotateY((float)Math.toRadians(-rotation.y))
    }
}
