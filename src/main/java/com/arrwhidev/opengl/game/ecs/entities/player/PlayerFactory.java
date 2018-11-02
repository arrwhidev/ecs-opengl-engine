package com.arrwhidev.opengl.game.ecs.entities.player;

import com.arrwhidev.opengl.engine.texture.Texture;
import com.arrwhidev.opengl.game.ecs.components.mesh.MeshComponentManager;
import com.arrwhidev.opengl.game.ecs.components.movement.MovementComponentManager;
import com.arrwhidev.opengl.game.ecs.components.position.PositionComponentManager;
import com.arrwhidev.opengl.game.ecs.entities.EntityManager;
import com.arrwhidev.opengl.game.texture.TextureManager;
import com.arrwhidev.opengl.game.texture.TextureType;
import org.joml.Vector2f;

public class PlayerFactory {

    private static final float SCALE = 4;
    private static final int WIDTH = 8;
    private static final int HEIGHT = 16;
    private static final Texture TEXTURE = TextureManager.get(TextureType.PLAYER);

    private PlayerFactory() {}

    public static Player create(float x, float y) {
        Vector2f velocity = new Vector2f(0,0);
        Vector2f acceleration = new Vector2f(0 ,0);

        int position = PositionComponentManager.create(x, y, WIDTH, HEIGHT, SCALE);
        int movement = MovementComponentManager.create(velocity, acceleration);
        int mesh = MeshComponentManager.create(WIDTH, HEIGHT, TEXTURE);
        Player player = new Player(position, movement, mesh);

        EntityManager.add(player);
        return player;
    }
}
