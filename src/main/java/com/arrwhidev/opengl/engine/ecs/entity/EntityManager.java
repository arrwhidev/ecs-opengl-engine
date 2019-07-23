package com.arrwhidev.opengl.engine.ecs.entity;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {

    private static final Object ID_LOCK = new Object();
    private final static List<Entity> entities = new ArrayList<>(1024);

    public static List<Entity> getEntities() {
        return entities;
    }

    public static void add(Entity e) {
        synchronized (ID_LOCK) {
            entities.add(e);
            e.setId(entities.size() - 1);
        }
    }
}
