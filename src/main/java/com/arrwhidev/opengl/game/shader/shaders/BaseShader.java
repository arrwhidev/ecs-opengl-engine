package com.arrwhidev.opengl.game.shader.shaders;

import com.arrwhidev.opengl.engine.shader.ShaderProgram;

public abstract class BaseShader extends ShaderProgram {

    private static final String PREFIX = "/shaders/";

    protected String withBasePath(String path) {
        return PREFIX + path;
    }
}
