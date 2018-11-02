package com.arrwhidev.opengl.game;

import com.arrwhidev.opengl.engine.GameEngine;

public class Main {

    private static final int HEIGHT = 768;
    private static final int WIDTH = HEIGHT * 16/9;
    private static final boolean VSYNC = true;
 
    public static void main(String[] args) {
        try {
            GameEngine engine = new GameEngine("Untitled Game", WIDTH, HEIGHT, VSYNC, new UntitledGame());
            engine.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}