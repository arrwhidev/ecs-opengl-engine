package com.arrwhidev.opengl.game.util;

import org.joml.Vector2f;

public class MathUtils {

    public static Vector2f rotate(Vector2f vec, double angle) {
        return new Vector2f(
            (float) (vec.x * Math.cos(angle) - vec.y * Math.sin(angle)),
            (float) (vec.x * Math.sin(angle) + vec.y * Math.cos(angle))
        );
    }
}
