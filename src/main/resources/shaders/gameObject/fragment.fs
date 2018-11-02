#version 330

in vec2 textureCoords;
in vec3 colour;
out vec4 result;

uniform sampler2D texture_sampler;

void main()
{
    result = texture(texture_sampler, textureCoords) * vec4(colour, 1);
}


