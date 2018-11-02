package com.arrwhidev.opengl.engine.loop;

import com.arrwhidev.opengl.engine.GameEngine;
import com.arrwhidev.opengl.engine.Window;

public class GameLoop {

    private static final double TIMESTEP = 0.0166;
    private static final double MAX_DELTA = 0.05;

    private boolean isRunning = true;
    private FPS fps;
    private GameEngine engine;

    public GameLoop(GameEngine engine) {
        this.engine = engine;
        this.fps = new FPS();
    }

    public void run(Window window) {
        long previousTime = System.nanoTime();
        double accumulatedTime = 0;

        while (isRunning && !window.windowShouldClose()) {
            long currentTime = System.nanoTime();
            double deltaTime = (currentTime - previousTime) / 1_000_000_000.0;

            if (deltaTime > MAX_DELTA) {
                deltaTime = MAX_DELTA;
            }

            accumulatedTime += deltaTime;

            while (accumulatedTime >= TIMESTEP) {
                engine.update(TIMESTEP);
                accumulatedTime -= TIMESTEP;
            }

            // TODO: Render when engine supports text - fps.calculate((int) (1.0f / deltaTime));
            engine.render();

            previousTime = currentTime;
        }
    }
}
