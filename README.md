# OpenGL ECS Game Engine & Game

## Introduction

First attempt at game programming using the modern OpenGL pipeline. I've used LWJGL simply as an OpenGL wrapper, I've ignored everything else it provides.

## Game Engine

I've kept the 'game' and the 'engine' separate in a top level package. Eventually, I plan to fully separate the engine and the game into separate projects.

I read about the ECS (Entity Component System) game engine pattern and decided to implement it. All other games I've worked on previously followed a more conventional OO architecture and the code often ended up messy and difficult to reason about. So far I like the ECS pattern!

## Thoughts

- I'm not entirely happy with how the Renderer/Shader/Mesh relate, it's a bit messy. There's no clear distinction between the 'game' and the 'engine'. It works but it needs fixing.
- Is the granularity of Components/Systems ok?
- Not made any serious attempt to optimise performance yet. Plan: write the code simple and understandable, then make it fast.

## How to run

- Only tested on OSX. Everything should work on other platforms by specifying different platform bindings in `pom.xml`.
- When running on OSX you need to run with the following VM property `-XstartOnFirstThread`.
- Run `com.arrwhidev.opengl.game.Main.java`.
- Left/Right arrows move the character.
- Left mouse click will create a random quad at the cursor location. 

## Resources

Resources I've found very useful so far;
 - https://lwjglgamedev.gitbooks.io/3d-game-development-with-lwjgl/content
 - https://medium.com/@savas
 - https://gafferongames.com/post/fix_your_timestep