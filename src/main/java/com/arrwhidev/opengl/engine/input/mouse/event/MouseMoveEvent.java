package com.arrwhidev.opengl.engine.input.mouse.event;

import org.joml.Vector2f;

public class MouseMoveEvent extends MouseEvent {
    public Vector2f position;
    public MouseMoveEvent(Vector2f position) {
        super(MouseEventType.MOVE);
        this.position = position;
    }
}
