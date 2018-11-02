package com.arrwhidev.opengl.engine.input.keyboard;

import com.arrwhidev.opengl.engine.Window;
import com.arrwhidev.opengl.engine.input.BaseInput;
import com.arrwhidev.opengl.engine.input.keyboard.event.KeyboardEvent;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.*;

public class KeyboardInput extends BaseInput<KeyboardEvent> {

    private static KeyboardInput INSTANCE;
    private final Map<Integer, Boolean> KEYS_DOWN = new HashMap<>();

    private KeyboardInput() {}
    public static KeyboardInput instance() {
        if (INSTANCE == null) {
            INSTANCE = new KeyboardInput();
        }
        return INSTANCE;
    }

    public void init(Window window) {
        glfwSetKeyCallback(window.getWindowHandle(), (win, key, scancode, action, mods) -> {
            notifyEveryone(new KeyboardEvent(action, key));
            if (action == GLFW_RELEASE) {
                release(key);
            } else if (action == GLFW_PRESS) {
                press(key);
            }
        });
    }
    private void press(int key) {
        check(key);
        KEYS_DOWN.put(key, true);
    }

    private void release(int key) {
        check(key);
        KEYS_DOWN.put(key, false);
    }

    private void check(int key) {
        if (!KEYS_DOWN.containsKey(key)) KEYS_DOWN.put(key, false);
    }

    public boolean isDown(int key) {
        return KEYS_DOWN.containsKey(key) && KEYS_DOWN.get(key);
    }
}
