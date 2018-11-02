package com.arrwhidev.opengl.game.util;

import org.joml.Vector2f;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    private static ThreadLocalRandom rand() {
        return ThreadLocalRandom.current();
    }

    public static float[] randomColour() {
        float r = rand().nextFloat(),
              g = rand().nextFloat(),
              b = rand().nextFloat();

        return new float[] {
            r, g, b,
            r, g, b,
            r, g, b,
            r, g, b,
        };
    }

    public static int randomIntBetween(int min, int max) {
        return rand().nextInt(min, max);
    }

    public static Vector2f randomVec2() {
        return new Vector2f(
            rand().nextFloat() * randomIntBetween(-200, 200),
            rand().nextFloat() * randomIntBetween(-200, 200)
        );
    }
}
