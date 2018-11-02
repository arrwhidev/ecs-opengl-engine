package com.arrwhidev.opengl.game.ecs.entities.player;

import com.arrwhidev.opengl.engine.ecs.Entity;
import com.arrwhidev.opengl.game.ecs.components.mesh.HasMesh;
import com.arrwhidev.opengl.game.ecs.components.movement.HasMovement;
import com.arrwhidev.opengl.game.ecs.components.position.HasPosition;

public class Player implements Entity, HasPosition, HasMovement, HasMesh {

    private int id, positionId, movementId, meshId;

    public Player(int positionId, int movementId, int meshId) {
        this.positionId = positionId;
        this.movementId = movementId;
        this.meshId = meshId;
    }

    @Override
    public int getPosition() {
        return positionId;
    }

    @Override
    public void setPosition(int id) {
        this.positionId = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getMesh() {
        return meshId;
    }

    @Override
    public void setMesh(int id) {
        this.meshId = id;
    }

    @Override
    public int getMovement() {
        return movementId;
    }

    @Override
    public void setMovement(int id) {
        movementId = id;
    }
}


