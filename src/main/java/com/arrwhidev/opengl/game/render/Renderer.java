package com.arrwhidev.opengl.game.render;

import com.arrwhidev.opengl.engine.Camera;
import com.arrwhidev.opengl.engine.ecs.Entity;
import com.arrwhidev.opengl.engine.input.keyboard.KeyboardInput;
import com.arrwhidev.opengl.engine.shader.ShaderProgram;
import com.arrwhidev.opengl.game.ecs.components.mesh.HasMesh;
import com.arrwhidev.opengl.game.ecs.components.mesh.Mesh;
import com.arrwhidev.opengl.game.ecs.components.mesh.MeshComponentManager;
import com.arrwhidev.opengl.game.ecs.components.movement.HasMovement;
import com.arrwhidev.opengl.game.ecs.components.movement.Movement;
import com.arrwhidev.opengl.game.ecs.components.movement.MovementComponentManager;
import com.arrwhidev.opengl.game.ecs.components.position.HasPosition;
import com.arrwhidev.opengl.game.ecs.components.position.Position;
import com.arrwhidev.opengl.game.ecs.components.position.PositionComponentManager;
import com.arrwhidev.opengl.game.ecs.entities.EntityManager;
import com.arrwhidev.opengl.game.map.TileMap;
import com.arrwhidev.opengl.game.shader.ShaderManager;
import com.arrwhidev.opengl.game.shader.ShaderType;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.opengl.GL11.*;

public class Renderer {

    private Camera camera;
    private TileMap tileMap;

    public Renderer(Camera camera, TileMap tileMap) {
        this.camera = camera;
        this.tileMap = tileMap;
    }

    public void render() {
        clear();
        windowResized();
        renderTileMap();
        renderEntities();
    }

    private void renderTileMap() {
        // TODO.
    }

    private void renderEntities() {
        for (Entity e : EntityManager.getEntities()) {
            if (e instanceof HasMesh && e instanceof HasPosition && e instanceof HasMovement) {
                Mesh mesh = MeshComponentManager.get((HasMesh) e);
                Position position = PositionComponentManager.get((HasPosition) e);
                Movement movement = MovementComponentManager.get((HasMovement) e);

                render(mesh, position, movement, ShaderManager.get(mesh.getShaderType()));

                if (KeyboardInput.instance().isDown(GLFW_KEY_DOWN)) {
                    render(mesh, position, movement, ShaderManager.get(ShaderType.AABB));
                }
            }
        }
    }

    private void render(Mesh mesh, Position position, Movement movement, ShaderProgram shaderProgram) {
        shaderProgram.bind();
        shaderProgram.render(camera, position, mesh, movement);
        shaderProgram.unbind();
    }

    private void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    private void windowResized() {
        // TODO
        // if (window.isResized()) {
        //     glViewport(0, 0, window.getWidth(), window.getHeight());
        //     window.setResized(false);
        // }
    }
}
