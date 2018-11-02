#version 330

layout (location=0) in vec3 in_position;
layout (location=1) in vec2 in_tex_coord;
layout (location=2) in vec3 in_colour;

out vec2 textureCoords;
out vec3 colour;
uniform mat4 projectionMatrix;

void main()
{
    gl_Position = projectionMatrix * vec4(in_position, 1.0);
    textureCoords = in_tex_coord;
    colour = in_colour;
}