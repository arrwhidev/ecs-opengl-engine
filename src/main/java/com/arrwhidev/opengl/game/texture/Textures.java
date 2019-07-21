package com.arrwhidev.opengl.game.texture;

import com.arrwhidev.opengl.engine.texture.TextureType;

import java.util.ArrayList;
import java.util.List;

public enum Textures {

    PLAYER("character.png"),
    TILE_GRASS("grassdirt.png"),
    TILE_DIRT("dirt.png");

    private static final String PREFIX = "/textures/";
    private TextureType tt;

    Textures(String filename) {
        this.tt = new TextureType(PREFIX + filename);
    }

    public static List<TextureType> all() {
        List<TextureType> all = new ArrayList<>();
        for (Textures value : Textures.values()) {
            all.add(value.tt);
        }
        return all;
    }
}
