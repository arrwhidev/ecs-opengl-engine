package com.arrwhidev.opengl.game.ecs.systems;

import com.arrwhidev.opengl.engine.ecs.entity.Entity;
import com.arrwhidev.opengl.engine.ecs.system.System;
import com.arrwhidev.opengl.game.ecs.components.movement.HasMovement;
import com.arrwhidev.opengl.game.ecs.components.movement.Movement;
import com.arrwhidev.opengl.game.ecs.components.movement.MovementComponentManager;
import com.arrwhidev.opengl.game.ecs.components.position.HasPosition;
import com.arrwhidev.opengl.game.ecs.components.position.Position;
import com.arrwhidev.opengl.game.ecs.components.position.PositionComponentManager;
import com.arrwhidev.opengl.engine.ecs.entity.EntityManager;
import org.joml.Vector2f;

public class MovementSystem implements System {

    @Override
    public void update(double dt) {
        for (Entity e : EntityManager.getEntities()) {
            if (e instanceof HasMovement && e instanceof HasPosition) {
                Movement movement = MovementComponentManager.get((HasMovement) e);
                Position position = PositionComponentManager.get((HasPosition) e);

                // Create new Vectors by multiplying movement attributes by delta time.
                Vector2f accDT = new Vector2f(movement.getAcceleration()).mul((float) dt);
                Vector2f velDT = new Vector2f(movement.getVelocity()).mul((float) dt);

                // Add acceleration to velocity.
                movement.getVelocity().add(accDT);

                // Add velocity to position.
                position.add(velDT);

                // Basic drag, reduce velocity over time.
                boolean applyDrag = false;
                if (applyDrag) {
                    movement.getVelocity().mul(0.9f);
                }

                // Move left or right depending on facing direction.
//                if (position.isFacingLeft()) {
//                    position.sub(velDT);
//                } else {
//                    position.add(velDT);
//                }
            }
        }
    }
}
