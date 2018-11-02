package com.arrwhidev.opengl.engine.input;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class BaseInput<E> {

    private final List<Consumer<E>> subscribers = new ArrayList<>();

    protected void notifyEveryone(E event) {
        subscribers.forEach(c -> c.accept(event));
    }

    public void subscribe(Consumer<E> c) {
        subscribers.add(c);
    }
}
