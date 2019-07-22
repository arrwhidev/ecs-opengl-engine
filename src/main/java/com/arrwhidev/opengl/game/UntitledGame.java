package com.arrwhidev.opengl.game;

import com.arrwhidev.opengl.engine.Camera;
import com.arrwhidev.opengl.engine.GameLogic;
import com.arrwhidev.opengl.engine.Window;
import com.arrwhidev.opengl.engine.input.keyboard.KeyboardInput;
import com.arrwhidev.opengl.engine.input.keyboard.event.KeyboardEvent;
import com.arrwhidev.opengl.engine.ecs.system.SystemManager;
import com.arrwhidev.opengl.engine.Renderer;
import com.arrwhidev.opengl.engine.texture.TextureManager;
import com.arrwhidev.opengl.game.ecs.systems.CollisionSystem;
import com.arrwhidev.opengl.game.ecs.systems.MovementSystem;
import com.arrwhidev.opengl.game.texture.Textures;

import static java.util.Arrays.asList;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;

public class UntitledGame implements GameLogic {

    private Camera camera;
    private Renderer renderer;
    private SystemManager systemManager;

    @Override
    public void init(Window window) {
        // Load all known textures, implement lazy loading when it's required.
        TextureManager.load(Textures.all());

        camera = new Camera(window);
        renderer = new GameRenderer(camera);
        systemManager = new SystemManager(window, asList(
            new MovementSystem(),
            new CollisionSystem(window))
        );

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


