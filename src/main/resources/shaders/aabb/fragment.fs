#version 330

in vec3 colour;
out vec4 result;

void main()
{
    result = vec4(colour, 1);
}


