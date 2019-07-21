package com.arrwhidev.opengl.engine.loop;

import com.arrwhidev.opengl.engine.GameEngine;
import com.arrwhidev.opengl.engine.Window;

public class GameLoop {

    private static final int FPS = 60;
    private static final float DT = 1.0f / FPS;

    private boolean isRunning = true;
    private FPS fps;
    private GameEngine engine;

    public GameLoop(GameEngine engine) {
        this.engine = engine;
        this.fps = new FPS();
    }

    public void run(Window window) {
        startThreadToPrintFps(window);

        float accumulator = 0.0f;
        long currentTime = System.currentTimeMillis();

        while (isRunning && !window.windowShouldClose()) {
            long newTime = System.currentTimeMillis();
            float frameTime = (newTime - currentTime) / 1000.0f;

            // Avoid spiral of death
            if (frameTime > 0.25f) frameTime = 0.25f;

            currentTime = newTime;
            accumulator += frameTime;

            // Logic update
            while (accumulator >= DT)  {
                accumulator -= DT;
                engine.update(DT);
            }

            fps.calculate((int) (1.0f / DT));
            engine.render();
        }
    }

    private void startThreadToPrintFps(Window window) {
        new Thread(() -> {
            while(isRunning && !window.windowShouldClose()) {
                System.out.println(fps);
                try {
                    Thread.sleep(1000 * 2);
                } catch (InterruptedException e) {}
            }
        }).start();
    }
}
