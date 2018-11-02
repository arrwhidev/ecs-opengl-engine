package com.arrwhidev.opengl.game.map;

public enum LevelType {
    LEVEL_1("level1");

    private String name;

    LevelType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
