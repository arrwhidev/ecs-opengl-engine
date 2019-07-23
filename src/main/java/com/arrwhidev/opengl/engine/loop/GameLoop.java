package com.arrwhidev.opengl.engine.loop;

import com.arrwhidev.opengl.engine.GameEngine;
import com.arrwhidev.opengl.engine.Window;

public class GameLoop {

    private static final int UPDATES_PER_SECOND = 30;
    private static final int MILLISECONDS_BETWEEN_TICKS = 1000 / UPDATES_PER_SECOND;
    private static final float DT = 1.0f / UPDATES_PER_SECOND;

    private boolean isRunning = true;
    private FPS fps;
    private GameEngine engine;

    public GameLoop(GameEngine engine) {
        this.engine = engine;
        this.fps = new FPS();
    }

    public void run(Window window) {
        startThreadToPrintFps(window);

        long nextUpdate = System.currentTimeMillis();
        float interpolation;
        while (isRunning && !window.windowShouldClose()) {

            while(System.currentTimeMillis() > nextUpdate) {
                engine.update(DT);
                nextUpdate += MILLISECONDS_BETWEEN_TICKS;
            }

            interpolation = (float) (System.currentTimeMillis() + MILLISECONDS_BETWEEN_TICKS - nextUpdate) / (float) MILLISECONDS_BETWEEN_TICKS;
            engine.render(interpolation);
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
