package com.arrwhidev.opengl.engine.loop;

import com.arrwhidev.opengl.engine.GameEngine;
import com.arrwhidev.opengl.engine.Window;

public class GameLoop {

    private static final int UPDATES_PER_SECOND = 30;
    private static final double NS_BETWEEN_TICKS = 1000000000.0 / UPDATES_PER_SECOND;
    private static final float DT = 1.0f / UPDATES_PER_SECOND;

    private boolean isRunning = true;
    private FPS fps;
    private GameEngine engine;

    public GameLoop(GameEngine engine) {
        this.engine = engine;
        this.fps = new FPS();
    }

    private long time() {
        return System.nanoTime();
    }

    public void run(Window window) {
        startThreadToPrintFps(window);

        double nextUpdate = time();
        float interpolation;
        while (isRunning && !window.windowShouldClose()) {

            while(time() > nextUpdate) {
                engine.update(DT);
                nextUpdate += NS_BETWEEN_TICKS;
            }

            interpolation = (float) (time() + NS_BETWEEN_TICKS - nextUpdate) / (float) NS_BETWEEN_TICKS;
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

    public static void main(String[] args) {
        System.out.println(System.nanoTime());
        System.out.println(System.currentTimeMillis());
    }
}
