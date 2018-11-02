package com.arrwhidev.opengl.engine.input.mouse.event;

public abstract class MouseEvent {

    public enum MouseEventType {
        MOVE, CLICK, WINDOW
    }

    private MouseEventType type;

    MouseEvent(MouseEventType type) {
        this.type = type;
    }

    public MouseEventType getType() {
        return type;
    }
}