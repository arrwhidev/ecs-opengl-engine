package com.arrwhidev.opengl.engine.input.mouse;

import com.arrwhidev.opengl.engine.Window;
import com.arrwhidev.opengl.engine.input.BaseInput;
import com.arrwhidev.opengl.engine.input.mouse.event.MouseClickEvent;
import com.arrwhidev.opengl.engine.input.mouse.event.MouseEvent;
import com.arrwhidev.opengl.engine.input.mouse.event.MouseMoveEvent;
import com.arrwhidev.opengl.engine.input.mouse.event.MouseWindowEvent;
import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.*;

public class MouseInput extends BaseInput<MouseEvent> {

    private static MouseInput INSTANCE;

    private boolean leftButtonPressed = false;
    private boolean rightButtonPressed = false;
    private boolean inWindow = true;
    private Vector2f position = new Vector2f();

    private MouseInput() {}
    public static MouseInput instance() {
        if (INSTANCE == null) {
            INSTANCE = new MouseInput();
        }
        return INSTANCE;
    }

    public void init(Window window) {
        glfwSetMouseButtonCallback(window.getWindowHandle(), (windowHandle, button, action, mode) -> {
            notifyEveryone(new MouseClickEvent(button, action));
            leftButtonPressed = button == GLFW_MOUSE_BUTTON_1 && action == GLFW_PRESS;
            rightButtonPressed = button == GLFW_MOUSE_BUTTON_2 && action == GLFW_PRESS;
        });

        glfwSetCursorPosCallback(window.getWindowHandle(), (windowHandle, xpos, ypos) -> {
            position.x = (float) xpos;
            position.y = (float) ypos;
            notifyEveryone(new MouseMoveEvent(position));
        });

        glfwSetCursorEnterCallback(window.getWindowHandle(), (windowHandle, entered) -> {
            inWindow = entered;
            notifyEveryone(new MouseWindowEvent(inWindow));
        });
    }

    public boolean isLeftButtonPressed() {
        return leftButtonPressed;
    }

    public boolean isRightButtonPressed() {
        return rightButtonPressed;
    }

    public boolean isInWindow() {
        return inWindow;
    }

    public Vector2f getPosition() {
        return position;
    }
}
