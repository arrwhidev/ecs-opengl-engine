package com.arrwhidev.opengl.game.ecs.components.movement;

import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class MovementComponentManager {

    private static final List<Movement> components = new ArrayList<>(1024);

    public static int create(Vector2f velocity, Vector2f acceleration) {
        components.add(new Movement(velocity, acceleration));
        return components.size() - 1;
    }

    public static Movement get(int id) {
        return components.get(id);
    }

    public static Movement get(HasMovement e) {
        return get(e.getMovement());
    }
}
