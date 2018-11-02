package com.arrwhidev.opengl.game.texture;

public enum TextureType {

    PLAYER("character.png"),
    TILE_GRASS("grassdirt.png"),
    TILE_DIRT("dirt.png");

    private String filename;

    TextureType(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }
}
