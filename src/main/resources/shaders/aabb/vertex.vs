#version 330

layout (location=0) in vec3 in_position;

out vec3 colour;
uniform mat4 projectionMatrix;

void main()
{
    gl_Position = projectionMatrix * vec4(in_position, 1.0);
    colour = vec3(1.0, 0.0, 1.0);
}