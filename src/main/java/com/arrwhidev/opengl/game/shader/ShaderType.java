package com.arrwhidev.opengl.game.shader;

import com.arrwhidev.opengl.engine.shader.ShaderProgram;
import com.arrwhidev.opengl.game.shader.shaders.AABBShader;
import com.arrwhidev.opengl.game.shader.shaders.GameObjectShader;

public enum ShaderType {
    GAME_OBJECT(GameObjectShader.class),
    AABB(AABBShader.class);

    private final Class<? extends ShaderProgram> clazz;
    ShaderType(Class<? extends ShaderProgram> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends ShaderProgram> getClazz() {
        return clazz;
    }
}
