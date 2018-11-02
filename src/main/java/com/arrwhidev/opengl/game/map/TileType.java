package com.arrwhidev.opengl.game.map;

import com.arrwhidev.opengl.game.texture.TextureType;

public enum TileType {

    EMPTY(0, null),
    DIRT(1, TextureType.TILE_DIRT),
    GRASS(2, TextureType.TILE_GRASS);

    public int id;
    public TextureType textureType;

    TileType(int id, TextureType textureType) {
        this.id = id;
        this.textureType = textureType;
    }

    public static TileType byId(int id) {
        for (TileType item : TileType.values()) {
            if (item.id == id) return item;
        }

        throw new RuntimeException("No TileType for id: " + id);
    }
}
