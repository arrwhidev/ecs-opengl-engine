package com.arrwhidev.opengl.engine;

public interface GameLogic {
    void init(Window window) throws Exception;
    void update(double interval);
    void render(Window window);
    void cleanup();
}