package com.arrwhidev.opengl.engine.input.mouse.event;

public class MouseWindowEvent extends MouseEvent {
    private boolean inWindow;

    public MouseWindowEvent(boolean inWindow) {
        super(MouseEventType.WINDOW);
        this.inWindow = inWindow;
    }

    public boolean isInWindow() {
        return inWindow;
    }
}

