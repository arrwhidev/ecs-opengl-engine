package com.arrwhidev.opengl.game.shader;

import com.arrwhidev.opengl.engine.Utils;
import com.arrwhidev.opengl.engine.shader.ShaderProgram;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ShaderManager {

    private static final String PREFIX = "/shaders/";
    private static final Map<ShaderType, ShaderProgram> shaders = new HashMap<>();

    static {
        // Load all known shaders.
        for(ShaderType st : ShaderType.values()) {
            load(st);
        }
    }

    private ShaderManager() {}

    private static void load(ShaderType st) {
        ShaderProgram program = reflectCreate(st.getClazz());
        System.out.println("Loading shader: " + program.getName());
        program.createVertexShader(Utils.loadResource(PREFIX + program.getVertexShaderFilename()));
        program.createFragmentShader(Utils.loadResource(PREFIX + program.getFragmentShaderFilename()));
        program.link();
        program.createUniforms();

        shaders.put(st, program);
    }

    public static ShaderProgram get(ShaderType st) {
        return shaders.get(st);
    }

    private static ShaderProgram reflectCreate(Class<? extends ShaderProgram> clazz) {
        Constructor<?> constructor = clazz.getConstructors()[0];
        try {
            return (ShaderProgram) constructor.newInstance(new Object[0]);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}
