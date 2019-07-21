package com.arrwhidev.opengl.engine.ecs.component.mesh;

import com.arrwhidev.opengl.engine.ecs.component.Component;
import com.arrwhidev.opengl.engine.texture.Texture;
import com.arrwhidev.opengl.game.shader.ShaderType;
import com.arrwhidev.opengl.game.shader.shaders.GameObjectShader;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Mesh implements Component {

    private float width, height;
    private float[] positions;
    private float[] textCoords;
    private float[] colours;
    private int[] indices;
    private ShaderType shaderType;

    private final int vaoId;
    private final List<Integer> vboIdList;
    private final int vertexCount;
    private final Texture texture;

    public Mesh(float width, float height, Texture texture, float[] coloursarr) {
        this.width = width;
        this.height = height;

        if (coloursarr == null) {
            this.colours = new float[] {
                    1,1,1,
                    1,1,1,
                    1,1,1,
                    1,1,1
            };
        } else {
            this.colours = coloursarr;
        }

        this.positions = new float[] {
            0, 0, // top left
            0, height, // bottom left
            width, height, // bottom right
            width, 0 // top right
        };
        this.indices = new int[] { 0, 1, 3, 3, 1, 2 };
        this.textCoords = new float[] {
            0.0f, 0.0f,
            0.0f, 1.0f,
            1.0f, 1.0f,
            1.0f, 0.0f
        };
        this.shaderType = ShaderType.GAME_OBJECT;
        this.texture = texture;

        FloatBuffer posBuffer = null;
        FloatBuffer textCoordsBuffer = null;
        FloatBuffer colourBuffer = null;
        IntBuffer indicesBuffer = null;

        try {
            vertexCount = indices.length;
            vboIdList = new ArrayList<>();

            vaoId = glGenVertexArrays();
            glBindVertexArray(vaoId);

            // Position VBO
            int vboId = glGenBuffers();
            vboIdList.add(vboId);
            posBuffer = MemoryUtil.memAllocFloat(positions.length);
            posBuffer.put(positions).flip();
            glBindBuffer(GL_ARRAY_BUFFER, vboId);
            glBufferData(GL_ARRAY_BUFFER, posBuffer, GL_STATIC_DRAW);
            GL20.glVertexAttribPointer(GameObjectShader.INPUT_LOCATION_POSITION, 2, GL_FLOAT, false, 0, 0);

            // Texture coordinates VBO
            vboId = glGenBuffers();
            vboIdList.add(vboId);
            textCoordsBuffer = MemoryUtil.memAllocFloat(textCoords.length);
            textCoordsBuffer.put(textCoords).flip();
            glBindBuffer(GL_ARRAY_BUFFER, vboId);
            glBufferData(GL_ARRAY_BUFFER, textCoordsBuffer, GL_STATIC_DRAW);
            GL20.glVertexAttribPointer(GameObjectShader.INPUT_LOCATION_TEXTURE_COORDS, 2, GL_FLOAT, false, 0, 0);

            // Colour VBO
            vboId = glGenBuffers();
            vboIdList.add(vboId);
            textCoordsBuffer = MemoryUtil.memAllocFloat(colours.length);
            textCoordsBuffer.put(colours).flip();
            glBindBuffer(GL_ARRAY_BUFFER, vboId);
            glBufferData(GL_ARRAY_BUFFER, colours, GL_STATIC_DRAW);
            GL20.glVertexAttribPointer(GameObjectShader.INPUT_LOCATION_COLOUR, 3, GL_FLOAT, false, 0, 0);

            // Index VBO
            vboId = glGenBuffers();
            vboIdList.add(vboId);
            indicesBuffer = MemoryUtil.memAllocInt(indices.length);
            indicesBuffer.put(indices).flip();
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboId);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);

            glBindBuffer(GL_ARRAY_BUFFER, 0);
            glBindVertexArray(0);
        } finally {
            if (posBuffer != null) {
                MemoryUtil.memFree(posBuffer);
            }
            if (textCoordsBuffer != null) {
                MemoryUtil.memFree(textCoordsBuffer);
            }
            if (indicesBuffer != null) {
                MemoryUtil.memFree(indicesBuffer);
            }
            if (colourBuffer != null) {
                MemoryUtil.memFree(colourBuffer);
            }
        }
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float[] getPositions() {
        return positions;
    }

    public void setPositions(float[] positions) {
        this.positions = positions;
    }

    public int[] getIndices() {
        return indices;
    }

    public void setIndices(int[] indices) {
        this.indices = indices;
    }

    public ShaderType getShaderType() {
        return shaderType;
    }

    public void setShaderType(ShaderType shaderType) {
        this.shaderType = shaderType;
    }

    public int getVaoId() {
        return vaoId;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public int getTextureId() {
        return texture.getId();
    }
}
