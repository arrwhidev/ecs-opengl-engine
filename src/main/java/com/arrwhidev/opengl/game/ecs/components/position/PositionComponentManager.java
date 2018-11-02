package com.arrwhidev.opengl.game.ecs.components.position;

import java.util.ArrayList;
import java.util.List;

public class PositionComponentManager {

    private static final List<Position> components = new ArrayList<>(1024);

    public static int create(float x, float y, int width, int height, float scale) {
        components.add(new Position(x, y, width, height, scale));
        return components.size() - 1;
    }

    public static Position get(int id) {
        return components.get(id);
    }

    public static Position get(HasPosition e) {
        return get(e.getPosition());
    }
}
