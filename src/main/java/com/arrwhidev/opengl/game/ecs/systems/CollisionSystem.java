package com.arrwhidev.opengl.game.ecs.systems;

import com.arrwhidev.opengl.engine.Window;
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

import static com.arrwhidev.opengl.game.util.MathUtils.rotate;

public class CollisionSystem implements System {

    private Window window;

    public CollisionSystem(Window window) {
        this.window = window;
    }

    @Override
    public void update(double dt) {
        for (int i = 0; i < EntityManager.getEntities().size(); i++) {
            Entity e1 = EntityManager.getEntities().get(i);
            if (e1 instanceof HasPosition) {
                Position position = PositionComponentManager.get((HasPosition) e1);
                Movement movement = MovementComponentManager.get((HasMovement) e1);
                checkScreenBounds(position, movement);

//                for (int j = i + 1; j < EntityManager.getEntities().size(); j++) {
//                    Entity e2 = EntityManager.getEntities().get(j);
//                    if (e2 instanceof HasPosition) {
//                        Position position2 = PositionComponentManager.get((HasPosition) e2);
//                        Movement movement2 = MovementComponentManager.get((HasMovement) e2);
//                        checkEntityCollision(position, position2, movement, movement2);
//                    }
//                }
            }
        }
    }

    private void checkEntityCollision(Position position1, Position position2, Movement movement1, Movement movement2) {
        if (position1.left() < position2.right() &&
            position1.right() > position2.left() &&
            position1.top() < position2.bottom() &&
            position1.bottom() > position2.top()
        ) {
            fixPositions(position1, position2);
            elasticCollision(position1, position2, movement1, movement2);
        }
    }

    private void fixPositions(Position position1, Position position2) {
        float w = (float) 0.5 * (position1.getWidth() + position2.getWidth());
        float h = (float) 0.5 * (position1.getHeight() + position2.getHeight());
        float dx = position1.center().x - position2.center().x;
        float dy = position1.center().y - position2.center().y;

        if (Math.abs(dx) <= w && Math.abs(dy) <= h) {
            float wy = w * dy;
            float hx = h * dx;

            if (wy > hx) {
                if (wy > -hx) {
                    position1.setTop(position2.bottom());
                } else {
                    position1.setRight(position2.left());
                }
            } else {
                if (wy > -hx) {
                    position1.setLeft(position2.right());
                } else {
                    position1.setBottom(position2.top());
                }
            }
        }
    }

    // https://en.wikipedia.org/wiki/Elastic_collision
    private void elasticCollision(Position position1, Position position2, Movement movement1, Movement movement2) {
        double angle = -Math.atan2(
            position2.center().y() - position1.center().y(),
            position2.center().x() - position1.center().x()
        );

        // Mass, everything has a mass of 1 for now.
        float m1 = 1;
        float m2 = 1;

        // Rotate vectors into a 1d plane.
        Vector2f u1 = rotate(movement1.getVelocity(), angle);
        Vector2f u2 = rotate(movement2.getVelocity(), angle);

        // Compute elastic collision.
        Vector2f v1 = new Vector2f(u1.x * (m1 - m2) / (m1 + m2) + u2.x * 2 * m2 / (m1 + m2), u1.y);
        Vector2f v2 = new Vector2f(u2.x * (m2 - m1) / (m1 + m2) + u1.x * 2 * m2 / (m1 + m2), u2.y);

        // Rotate vectors back & update.
        movement1.setVelocity(rotate(v1, -angle));
        movement2.setVelocity(rotate(v2, -angle));
    }

    private void checkScreenBounds(Position position, Movement movement) {
        boolean offLeft = position.left() < 0;
        boolean offRight = position.right() > window.getWidth();
        boolean offTop = position.top() < 0;
        boolean offBottom = position.bottom() > window.getHeight();

        if (offLeft) {
            movement.getVelocity().x = -movement.getVelocity().x;
            position.setLeft(0);
        } else if (offRight) {
            movement.getVelocity().x = -movement.getVelocity().x;
            position.setRight(window.getWidth());
        }

        if (offTop) {
            movement.getVelocity().y = -movement.getVelocity().y;
            position.setTop(0);
        } else if (offBottom) {
            movement.getVelocity().y = -movement.getVelocity().y;
            position.setBottom(window.getHeight());
        }
    }
}