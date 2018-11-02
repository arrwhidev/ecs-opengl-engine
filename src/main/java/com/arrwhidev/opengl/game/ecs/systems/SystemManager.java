package com.arrwhidev.opengl.game.ecs.systems;

import com.arrwhidev.opengl.engine.ecs.System;
import com.arrwhidev.opengl.engine.Camera;
import com.arrwhidev.opengl.engine.Window;

import java.util.ArrayList;
import java.util.List;

public class SystemManager {

    private final List<System> systems = new ArrayList<>();

    public SystemManager(Window window, Camera camera) {
        systems.add(new PlayerControlSystem());
        systems.add(new MovementSystem());
        systems.add(new CollisionSystem(window));
    }

    public void update(double dt) {
        for (System system : systems) {
            system.update(dt);
        }
    }
}
