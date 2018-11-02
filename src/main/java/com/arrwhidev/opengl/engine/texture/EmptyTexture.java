package com.arrwhidev.opengl.engine.texture;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glDeleteTextures;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

/**
 * This is a singleton because only one ever needs to exist.
 */
public class EmptyTexture implements Texture {

    private static EmptyTexture INSTANCE;
    private final int id;

    private EmptyTexture() {
        this.id = createEmptyTexture();
    }

    public static EmptyTexture instance() {
        if (INSTANCE == null) {
            INSTANCE = new EmptyTexture();
        }
        return INSTANCE;
    }

    @Override
    public int getId() {
        return id;
    }

    private static int createEmptyTexture() {
        ByteBuffer buf = ByteBuffer.allocateDirect(4);

        byte[] data = new byte[] {
            (byte)255, (byte)255, (byte)255, (byte)255
        };
        buf.put(data);
        buf.flip();

        // Create a new OpenGL texture
        int textureId = glGenTextures();

        // Bind the texture
        glBindTexture(GL_TEXTURE_2D, textureId);

        // Tell OpenGL how to unpack the RGBA bytes. Each component is 1 byte size
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

        // Enable transparent textures when overlapping.
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        // Fix blurry textures.
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        // Upload the texture data
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, 1,1, 0, GL_RGBA, GL_UNSIGNED_BYTE, buf);

        // Generate Mip Map
        glGenerateMipmap(GL_TEXTURE_2D);
        return textureId;
    }

    public void cleanup() {
        // TODO
        glDeleteTextures(id);
    }
}
