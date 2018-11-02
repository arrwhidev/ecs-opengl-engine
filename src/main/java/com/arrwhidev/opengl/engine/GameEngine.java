package com.arrwhidev.opengl.engine;

import com.arrwhidev.opengl.engine.input.keyboard.KeyboardInput;
import com.arrwhidev.opengl.engine.input.mouse.MouseInput;
import com.arrwhidev.opengl.engine.loop.GameLoop;

public class GameEngine implements Runnable {

    private final Window window;
    private final Thread gameLoopThread;
    private final GameLogic gameLogic;
    private final GameLoop gameLoop;

    public GameEngine(String windowTitle, int width, int height, boolean vSync, GameLogic gameLogic) {
        System.out.println("Resolution: " + width + " x " + height);
        this.gameLogic = gameLogic;

        gameLoop = new GameLoop(this);
        gameLoopThread = new Thread(this, "GAME_LOOP_THREAD");
        window = new Window(windowTitle, width, height, vSync);
    }

    public void start() {
        String osName = System.getProperty("os.name");
        if (osName.contains("Mac")) {
            gameLoopThread.run();
        } else {
            gameLoopThread.start();
        }
    }

    @Override
    public void run() {
        try {
            init();
            gameLoop.run(window);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cleanup();
        }
    }

    private void init() throws Exception {
        window.init();
        gameLogic.init(window);

        MouseInput.instance().init(window);
        KeyboardInput.instance().init(window);
    }

    public void update(double dt) {
        gameLogic.update(dt);
    }

    public void render() {
        gameLogic.render(window);
        window.update();
    }

    private void cleanup() {
        gameLogic.cleanup();
    }
}
