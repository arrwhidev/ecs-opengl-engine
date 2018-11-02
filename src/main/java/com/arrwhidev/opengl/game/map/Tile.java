package com.arrwhidev.opengl.game.map;

import com.arrwhidev.opengl.engine.texture.Texture;
import com.arrwhidev.opengl.game.ecs.components.mesh.Mesh;
import com.arrwhidev.opengl.game.texture.TextureManager;
import org.joml.Vector2f;

public class Tile {

    private Mesh mesh;
    public static float SIZE = 8;
    private TileType type;
    private float
            width = 8,
            height = 8;
    private Vector2f pos = new Vector2f(0,0);

    public boolean isTypeOf(TileType type) {
        return this.type.equals(type);
    }

    public TileType getType() {
        return type;
    }

    public Tile(TileType type) {
        super();
        this.type = type;
//        float[] positions = new float[]{
//                0, 0, // top left
//                0, height, // bottom left
//                width, height, // bottom right
//                width, 0 // top right
//        };
//        int[] indices = new int[]{
//                0, 1, 3, 3, 1, 2
//        };

        Texture texture = (type.equals(TileType.EMPTY)) ? null : TextureManager.get(type.textureType);
        mesh = new Mesh(width, height, texture, null);
    }

    public void setPosition(float x, float y) {
        pos.x = x;
        pos.y = y;
    }


}
