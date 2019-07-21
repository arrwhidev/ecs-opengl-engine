package com.arrwhidev.opengl.engine.ecs.component.mesh;

import com.arrwhidev.opengl.engine.shader.ShaderProgram;
import com.arrwhidev.opengl.engine.texture.Texture;

import java.util.ArrayList;
import java.util.List;

public class MeshComponentManager {

    private static final List<Mesh> components = new ArrayList<>(1024);

    public static int create(int width, int height, Texture texture, ShaderProgram shader, float[] colours) {
        components.add(new Mesh(width, height, texture, colours, shader));
        return components.size() - 1;
    }

    public static int create(int width, int height, Texture texture, ShaderProgram shader) {
        return create(width, height, texture, shader,null);
    }

    public static Mesh get(int id) {
        return components.get(id);
    }

    public static Mesh get(HasMesh e) {
        return get(e.getMesh());
    }
}
