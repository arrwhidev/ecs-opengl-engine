package com.arrwhidev.opengl.engine.texture;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextureManager {

    private static final Map<TextureType, Texture> textures = new HashMap<>();

    private TextureManager() {}

    public static void load(List<TextureType> texturesToLoad) {
        for (TextureType tt : texturesToLoad) {
            System.out.println("Loading texture: " + tt.getFilename());
            textures.put(tt, new PNGTexture(tt.getFilename()));
        }
    }

    public static Texture get(TextureType tt) {
        return textures.get(tt);
    }

}
