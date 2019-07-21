package com.arrwhidev.opengl.game.shader.shaders;

import com.arrwhidev.opengl.engine.Camera;
import com.arrwhidev.opengl.engine.ecs.component.mesh.Mesh;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class GameObjectShader extends BaseShader {

    private static final String NAME = "GameObjectShader";
    private static final String VERTEX_FILENAME = "gameObject/vertex.vs";
    private static final String FRAGMENT_FILENAME = "gameObject/fragment.fs";

    private static final String UNIFORM_TEXTURE = "texture_sampler";
    private static final String UNIFORM_PROJECTION_MATRIX = "projectionMatrix";

    public static final int INPUT_LOCATION_POSITION = 0;
    public static final int INPUT_LOCATION_TEXTURE_COORDS = 1;
    public static final int INPUT_LOCATION_COLOUR = 2;

    @Override
    public void render(Camera camera, Mesh mesh, Matrix4f modelViewMatrix) {
        setUniforms(modelViewMatrix);

        // Activate texture slot.
        glActiveTexture(GL_TEXTURE0);

        // Bind the texture.
        glBindTexture(GL_TEXTURE_2D, mesh.getTextureId());

        // Bind mesh.
        glBindVertexArray(mesh.getVaoId());

        // Enable vertex arrays.
        glEnableVertexAttribArray(INPUT_LOCATION_POSITION);
        glEnableVertexAttribArray(INPUT_LOCATION_TEXTURE_COORDS);
        glEnableVertexAttribArray(INPUT_LOCATION_COLOUR);

        // Triangles!
        glDrawElements(GL_TRIANGLES, mesh.getVertexCount(), GL_UNSIGNED_INT, 0);

        // Disable vertex arrays.
        glDisableVertexAttribArray(INPUT_LOCATION_POSITION);
        glDisableVertexAttribArray(INPUT_LOCATION_TEXTURE_COORDS);
        glDisableVertexAttribArray(INPUT_LOCATION_COLOUR);

        // Unbind mesh.
        glBindVertexArray(0);
    }

    private void setUniforms(Matrix4f modelViewMatrix) {
        setUniform(UNIFORM_TEXTURE, 0);
        setUniform(UNIFORM_PROJECTION_MATRIX, modelViewMatrix);
    }

    @Override
    public void createUniforms() {
        createUniform(UNIFORM_PROJECTION_MATRIX);
        createUniform(UNIFORM_TEXTURE);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getVertexShaderFilename() {
        return withBasePath(VERTEX_FILENAME);
    }

    @Override
    public String getFragmentShaderFilename() {
        return withBasePath(FRAGMENT_FILENAME);
    }

    @Override
    public int getInputLocationPos() {
        return INPUT_LOCATION_POSITION;
    }

    @Override
    public int getInputLocationTextureCoords() {
        return INPUT_LOCATION_TEXTURE_COORDS;
    }

    @Override
    public int getInputLocationColour() {
        return INPUT_LOCATION_COLOUR;
    }
}
