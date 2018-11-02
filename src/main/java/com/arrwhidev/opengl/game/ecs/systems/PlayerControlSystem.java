package com.arrwhidev.opengl.game.ecs.systems;

import com.arrwhidev.opengl.engine.ecs.System;
import com.arrwhidev.opengl.engine.input.keyboard.KeyboardInput;
import com.arrwhidev.opengl.game.ecs.components.movement.Movement;
import com.arrwhidev.opengl.game.ecs.components.movement.MovementComponentManager;
import com.arrwhidev.opengl.game.ecs.components.position.Position;
import com.arrwhidev.opengl.game.ecs.components.position.PositionComponentManager;
import com.arrwhidev.opengl.game.ecs.entities.EntityManager;
import com.arrwhidev.opengl.game.ecs.entities.player.Player;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;

public class PlayerControlSystem implements System {

    private static final float MAX_PLAYER_SPEED = 120;
    private static final float HORIZONTAL_ACCELERATION = 15;
    private static final float HORIZONTAL_DECELERATION_FACTOR = 0.55f;

    @Override
    public void update(double dt) {
        try {
            Player player = EntityManager.getPlayer();
            Movement movement = MovementComponentManager.get(player);
            Position position = PositionComponentManager.get(player);

            if (KeyboardInput.instance().isDown(GLFW_KEY_LEFT)) {
                movement.faceLeft();
                if (belowMaxSpeed(movement)) {
                    movement.getAcceleration().add(HORIZONTAL_ACCELERATION, 0);
                }
            } else if (KeyboardInput.instance().isDown(GLFW_KEY_RIGHT)) {
                movement.faceRight();
                if (belowMaxSpeed(movement)) {
                    movement.getAcceleration().add(-HORIZONTAL_ACCELERATION, 0);
                }
            } else {
                if (movement.getAcceleration().x > 0) {
                    movement.getAcceleration().mul(HORIZONTAL_DECELERATION_FACTOR, 1);
                }
            }
        } catch (Exception e) {
            // Do nothing because Player probably doesn't exist.
        }
    }

    private boolean belowMaxSpeed(Movement movement) {
        return movement.getVelocity().x < MAX_PLAYER_SPEED;
    }
}
