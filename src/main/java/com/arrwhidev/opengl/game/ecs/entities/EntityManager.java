package com.arrwhidev.opengl.game.ecs.entities;

import com.arrwhidev.opengl.engine.ecs.Entity;
import com.arrwhidev.opengl.game.ecs.entities.player.Player;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {

    private static final Object ID_LOCK = new Object();
    private final static List<Entity> entities = new ArrayList<>(1024);
    private static int playerIndex = -1;

    public static List<Entity> getEntities() {
        return entities;
    }

    // Convenience method
    public static Player getPlayer() {
        if (playerIndex == -1) throw new RuntimeException("No player created.");
        return (Player) entities.get(playerIndex);
    }

    public static void add(Entity e) {
        synchronized (ID_LOCK) {
            entities.add(e);
            e.setId(entities.size() - 1);

            if (e instanceof Player) {
                playerIndex = e.getId();
            }
        }
    }
}
