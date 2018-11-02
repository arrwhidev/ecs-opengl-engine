package com.arrwhidev.opengl.game;

import com.arrwhidev.opengl.engine.Camera;
import com.arrwhidev.opengl.engine.GameLogic;
import com.arrwhidev.opengl.engine.Window;
import com.arrwhidev.opengl.engine.input.keyboard.KeyboardInput;
import com.arrwhidev.opengl.engine.input.keyboard.event.KeyboardEvent;
import com.arrwhidev.opengl.game.ecs.systems.SystemManager;
import com.arrwhidev.opengl.game.map.MapLoader;
import com.arrwhidev.opengl.game.map.LevelType;
import com.arrwhidev.opengl.game.map.TileMap;
import com.arrwhidev.opengl.game.render.Renderer;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;

public class UntitledGame implements GameLogic {

    private Camera camera;
    private TileMap tileMap;
    private SystemManager systemManager;
    private Renderer renderer;


    @Override
    public void init(Window window) {
        camera = new Camera(window);
        tileMap = new TileMap(window, MapLoader.load(LevelType.LEVEL_1));
        systemManager = new SystemManager(window, camera);
        renderer = new Renderer(camera, tileMap);

        setupKeyboardQuitListener(window);

        // Test objects!
        TestEntityFactory tef = new TestEntityFactory();
        tef.createTestEntities(window);
        tef.createTestEntitiesFromMouseEvents();
    }

    private void setupKeyboardQuitListener(Window window) {
        KeyboardInput.instance().subscribe(event -> {
            if (event.getKey() == GLFW_KEY_ESCAPE && event.getAction().equals(KeyboardEvent.Action.RELEASE)) {
                glfwSetWindowShouldClose(window.getWindowHandle(), true);
            }
        });
    }

    @Override
    public void update(double interval) {
        systemManager.update(interval);
    }

    @Override
    public void render(Window window) {
        renderer.render();
    }

    @Override
    public void cleanup() {}
}


