package com.arrwhidev.opengl.engine.loop;

import com.arrwhidev.opengl.engine.GameEngine;
import com.arrwhidev.opengl.engine.Window;
import org.lwjgl.glfw.GLFW;

public class GameLoop {

    private static final int UPDATES_PER_SECOND = 30;
    private static final float DT = 1.0f / UPDATES_PER_SECOND;

    private boolean isRunning = true;
    private GameEngine engine;

    public GameLoop(GameEngine engine) {
        this.engine = engine;
    }

    public void run(Window window) {
        double currentTime = GLFW.glfwGetTime();

        // For FPS measurements.
        double timer = currentTime;
        int frames = 0;
        int updates = 0;

        float accumulator = 0.0f;

        while (isRunning && !window.windowShouldClose()) {
            double newTime = GLFW.glfwGetTime();
            double frameTime = newTime - currentTime;

            // Avoid spiral of death
            if (frameTime > 0.25f) frameTime = 0.25f;

            currentTime = newTime;
            accumulator += frameTime;

            // Logic update
            while (accumulator >= DT) {
                accumulator -= DT;
                engine.update(DT);
                updates++;
            }

            float interp = accumulator / DT;
            engine.render(interp);
            frames++;

            if (newTime - timer >= 1) {
                timer += 1;
                window.setTitle("ups="+updates+", fps="+frames);
                updates = 0;
                frames = 0;
            }
        }
    }
}
