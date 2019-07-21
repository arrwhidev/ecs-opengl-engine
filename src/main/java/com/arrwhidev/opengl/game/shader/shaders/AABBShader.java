package com.arrwhidev.opengl.game.shader.shaders;

import com.arrwhidev.opengl.engine.Camera;
import com.arrwhidev.opengl.engine.shader.ShaderProgram;
import com.arrwhidev.opengl.engine.ecs.component.mesh.Mesh;
import com.arrwhidev.opengl.game.ecs.components.movement.Movement;
import com.arrwhidev.opengl.game.ecs.components.position.Position;
import com.arrwhidev.opengl.game.ecs.misc.Transformation;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class AABBShader extends ShaderProgram {

    private static final String NAME = "AABBShader";
    private static final String VERTEX_FILENAME = "aabb/vertex.vs";
    private static final String FRAGMENT_FILENAME = "aabb/fragment.fs";
    private static final String UNIFORM_PROJECTION_MATRIX = "projectionMatrix";

    public static final int INPUT_LOCATION_POSITION = 0;

    @Override
    public void render(Camera camera, Position position, Mesh mesh, Movement movement) {
        setUniforms(camera, position, movement);

        // Bind mesh.
        glBindVertexArray(mesh.getVaoId());

        // Enable vertex arrays.
        glEnableVertexAttribArray(INPUT_LOCATION_POSITION);

        // Turn off FILL.
        glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);

        // Triangles!
        glDrawElements(GL_TRIANGLES, mesh.getVertexCount(), GL_UNSIGNED_INT, 0);

        // Turn FILL back on.
        glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);

        // Disable vertex arrays.
        glDisableVertexAttribArray(INPUT_LOCATION_POSITION);

        // Unbind mesh.
        glBindVertexArray(0);
    }

    private void setUniforms(Camera camera, Position position, Movement movement) {
        Matrix4f modelViewMatrix = Transformation.getModelMatrix(camera, position, movement);
        setUniform(UNIFORM_PROJECTION_MATRIX, modelViewMatrix);
    }

    @Override
    public void createUniforms() {
        createUniform(UNIFORM_PROJECTION_MATRIX);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getVertexShaderFilename() {
        return VERTEX_FILENAME;
    }

    @Override
    public String getFragmentShaderFilename() {
        return FRAGMENT_FILENAME;
    }
}
