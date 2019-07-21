package com.arrwhidev.opengl.engine.ecs.system;

import com.arrwhidev.opengl.engine.Window;

import java.util.ArrayList;
import java.util.List;

public class SystemManager {

    private final List<System> systems;

    public SystemManager(Window window, List<System> systems) {
        this.systems = new ArrayList<>(systems.size());
        this.systems.addAll(systems);
    }

    public void update(double dt) {
        for (System system : systems) {
            system.update(dt);
        }
    }
}
