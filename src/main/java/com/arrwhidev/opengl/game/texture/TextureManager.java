package com.arrwhidev.opengl.game.texture;

import com.arrwhidev.opengl.engine.texture.PNGTexture;
import com.arrwhidev.opengl.engine.texture.Texture;

import java.util.HashMap;
import java.util.Map;

public class TextureManager {

    private static final String PREFIX = "/textures/";
    private static final Map<TextureType, Texture> textures = new HashMap<>();

    static {
        // Load all known textures as there are not many yet.
        // Implement a lazy loader later when required.
        for(TextureType tt : TextureType.values()) {
            load(tt);
        }
    }

    private TextureManager() {}

    private static void load(TextureType tt) {
        System.out.println("Loading texture: " + tt.getFilename());
        textures.put(tt, new PNGTexture(PREFIX + tt.getFilename()));
    }

    public static Texture get(TextureType tt) {
        return textures.get(tt);
    }
}
