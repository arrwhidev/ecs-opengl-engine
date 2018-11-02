package com.arrwhidev.opengl.engine.input.keyboard.event;

public class KeyboardEvent {

    public enum Action {
        RELEASE, PRESS, REPEAT;

        static Action from(int b) {
            if (b == 0) return RELEASE;
            if (b == 1) return PRESS;
            if (b == 2) return REPEAT;
            throw new RuntimeException("Unknown keyboard action " + b);
        }
    }

    private Action action;
    private int key;

    public KeyboardEvent(int action, int key) {
        this.action = Action.from(action);
        this.key = key;
    }

    public Action getAction() {
        return action;
    }

    public int getKey() {
        return key;
    }

    @Override
    public String toString() {
        return "KeyboardEvent{" +
                "action=" + action +
                ", key=" + key +
                '}';
    }
}
