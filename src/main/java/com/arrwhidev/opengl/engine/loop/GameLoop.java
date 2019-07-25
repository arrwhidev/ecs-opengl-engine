package com.arrwhidev.opengl.engine.loop;

import com.arrwhidev.opengl.engine.GameEngine;
import com.arrwhidev.opengl.engine.Window;

public class GameLoop {

    private static final int UPDATES_PER_SECOND = 30;
    private static final double NS_PER_SECOND = 1000000000.0;
    private static final float NS_BETWEEN_TICKS = (float) NS_PER_SECOND / UPDATES_PER_SECOND;
    private static final float DT = 1.0f / UPDATES_PER_SECOND;

    private boolean isRunning = true;
    private GameEngine engine;

    public GameLoop(GameEngine engine) {
        this.engine = engine;
    }

    private long now() {
        return System.nanoTime();
    }

    public void run(Window window) {
        long now = now();

        // For FPS measurements.
        long timer = now;
        int frames = 0;
        int updates = 0;

        double nextUpdate = now;
        float interpolation;
        while (isRunning && !window.windowShouldClose()) {
            long t = now();

            while(t >= nextUpdate) {
                engine.update(DT);
                nextUpdate += NS_BETWEEN_TICKS;
                updates++;
            }

            interpolation = (float) (t + NS_BETWEEN_TICKS - nextUpdate) / NS_BETWEEN_TICKS;
            engine.render(interpolation);
            frames++;

            if (t - timer >= NS_PER_SECOND) {
                timer += NS_PER_SECOND;
                System.out.println("ups="+updates+", fps="+frames);
                updates = 0;
                frames = 0;
            }
        }
    }
}
