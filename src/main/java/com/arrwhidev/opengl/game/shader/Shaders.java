package com.arrwhidev.opengl.game.shader;

import com.arrwhidev.opengl.engine.shader.ShaderManager;
import com.arrwhidev.opengl.engine.shader.ShaderProgram;
import com.arrwhidev.opengl.game.shader.shaders.AABBShader;
import com.arrwhidev.opengl.game.shader.shaders.GameObjectShader;

public enum Shaders {

    GAME_OBJECT(GameObjectShader.class),
    AABB(AABBShader.class);

    private final ShaderProgram st;

    Shaders(Class clazz) {
        this.st = ShaderManager.load(clazz);
    }

    public ShaderProgram getShader() {
        return st;
    }
}
