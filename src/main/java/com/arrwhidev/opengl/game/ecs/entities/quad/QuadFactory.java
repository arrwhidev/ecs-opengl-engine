package com.arrwhidev.opengl.game.ecs.entities.quad;

import com.arrwhidev.opengl.engine.Window;
import com.arrwhidev.opengl.engine.texture.EmptyTexture;
import com.arrwhidev.opengl.engine.texture.Texture;
import com.arrwhidev.opengl.engine.ecs.component.mesh.MeshComponentManager;
import com.arrwhidev.opengl.game.ecs.components.movement.MovementComponentManager;
import com.arrwhidev.opengl.game.ecs.components.position.PositionComponentManager;
import com.arrwhidev.opengl.engine.ecs.entity.EntityManager;
import com.arrwhidev.opengl.game.shader.Shaders;
import com.arrwhidev.opengl.game.util.RandomUtils;
import org.joml.Vector2f;

public class QuadFactory {

    private static final Texture EMPTY_TEXTURE = EmptyTexture.instance();
    private static final int
        MIN_WIDTH = 5,
        MAX_WIDTH = 50,
        MIN_HEIGHT = 5,
        MAX_HEIGHT = 80;

    private QuadFactory() {}

    public static Quad createRandom(Window window) {
        int width = RandomUtils.randomIntBetween(MIN_WIDTH, MAX_WIDTH);
        int height = RandomUtils.randomIntBetween(MIN_HEIGHT, MAX_HEIGHT);
        int x = RandomUtils.randomIntBetween(0, window.getWidth() - MAX_WIDTH);
        int y = RandomUtils.randomIntBetween(0, window.getHeight() - MAX_HEIGHT);
        float[] colour = RandomUtils.randomColour();
        Vector2f velocity = RandomUtils.randomVec2();
        Vector2f acceleration = new Vector2f(0 ,0);

        int position = PositionComponentManager.create(x, y, width, height, 1);
        int movement = MovementComponentManager.create(velocity, acceleration);
        int mesh = MeshComponentManager.create(width, height, EMPTY_TEXTURE, Shaders.GAME_OBJECT.getShader(), colour);
        Quad quad = new Quad(position, movement, mesh);

        EntityManager.add(quad);
        return quad;
    }

    public static Quad createRandom(Vector2f pos) {
        return createRandom(pos, RandomUtils.randomVec2(), RandomUtils.randomIntBetween(MIN_WIDTH, MAX_WIDTH));
    }

    public static Quad createRandom(Vector2f pos, Vector2f velocity, int size) {
        float[] colour = RandomUtils.randomColour();
        Vector2f acceleration = new Vector2f(0 ,0);

        int position = PositionComponentManager.create(pos.x, pos.y, size, size, 1);
        int movement = MovementComponentManager.create(velocity, acceleration);
        int mesh = MeshComponentManager.create(size, size, EMPTY_TEXTURE, Shaders.GAME_OBJECT.getShader(), colour);
        Quad quad = new Quad(position, movement, mesh);

        EntityManager.add(quad);
        return quad;
    }
}
