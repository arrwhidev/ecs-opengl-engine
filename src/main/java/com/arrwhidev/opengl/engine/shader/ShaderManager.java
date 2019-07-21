package com.arrwhidev.opengl.engine.shader;

import com.arrwhidev.opengl.engine.Utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;

public class ShaderManager {

    private static final HashSet<ShaderProgram> shaders = new HashSet<>();

    private static ShaderProgram reflectCreate(Class<? extends ShaderProgram> clazz) {
        Constructor<?> constructor = clazz.getConstructors()[0];
        try {
            return (ShaderProgram) constructor.newInstance(new Object[0]);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    private ShaderManager() {}

    public static ShaderProgram load(Class<? extends ShaderProgram> clazz) {
        ShaderProgram program = reflectCreate(clazz);
        System.out.println("Loading shader: " + program.getName());
        program.createVertexShader(Utils.loadResource(program.getVertexShaderFilename()));
        program.createFragmentShader(Utils.loadResource(program.getFragmentShaderFilename()));
        program.link();
        program.createUniforms();

        shaders.add(program);
        return program;
    }
}
