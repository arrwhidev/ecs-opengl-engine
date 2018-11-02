package com.arrwhidev.opengl.engine.texture;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;

import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

public class PNGTexture implements Texture {

    private final int id;

    public PNGTexture(String fileName) {
        this(loadTexture(fileName));
    }
    private PNGTexture(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private static int loadTexture(String fileName) {
        // Load PNGTexture file
        PNGDecoder decoder = null;
        try {
            decoder = new PNGDecoder(PNGTexture.class.getResourceAsStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load texture contents into a byte buffer
        ByteBuffer buf = ByteBuffer.allocateDirect(
                4 * decoder.getWidth() * decoder.getHeight());
        try {
            decoder.decode(buf, decoder.getWidth() * 4, Format.RGBA);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buf);

        // Generate Mip Map
        glGenerateMipmap(GL_TEXTURE_2D);
        return textureId;
    }

    public void cleanup() {
        // TODO
        glDeleteTextures(id);
    }
}
