package com.arrwhidev.opengl.engine.input.mouse.event;

public class MouseClickEvent extends MouseEvent {

    public enum Button {
        LEFT, RIGHT, MIDDLE;

        static Button from(int b) {
            if (b == 0) return LEFT;
            if (b == 1) return RIGHT;
            if (b == 2) return MIDDLE;
            throw new RuntimeException("Unknown button event " + b);
        }
    }

    public enum Action {
        RELEASE, PRESS, REPEAT;

        static Action from(int b) {
            if (b == 0) return RELEASE;
            if (b == 1) return PRESS;
            if (b == 2) return REPEAT;
            throw new RuntimeException("Unknown button action " + b);
        }
    }

    private Button button;
    private Action action;

    public MouseClickEvent(int button, int action) {
        super(MouseEventType.CLICK);
        this.button = Button.from(button);
        this.action = Action.from(action);
    }

    public Action getAction() {
        return action;
    }

    public Button getButton() {
        return button;
    }

    @Override
    public String toString() {
        return "MouseClickEvent{" +
                "button=" + button +
                ", action=" + action +
                '}';
    }
}