package com.arrwhidev.opengl.game;

import com.arrwhidev.opengl.engine.Window;
import com.arrwhidev.opengl.engine.input.mouse.MouseInput;
import com.arrwhidev.opengl.engine.input.mouse.event.MouseClickEvent;
import com.arrwhidev.opengl.game.ecs.entities.quad.QuadFactory;
import org.joml.Vector2f;

import static com.arrwhidev.opengl.engine.input.mouse.event.MouseClickEvent.Action.PRESS;

public class TestEntityFactory {

    public void createTestEntitiesFromMouseEvents() {
        MouseInput.instance().subscribe(event -> {
            switch (event.getType()) {
                case CLICK:
                    MouseClickEvent mce = (MouseClickEvent) event;
                    if (mce.getAction().equals(PRESS) && MouseInput.instance().isInWindow()) {
                        QuadFactory.createRandom(MouseInput.instance().getPosition());
                    }
                    break;
                default:
                    break;
            }
        });
    }

    public void createTestEntities(Window window) {
        QuadFactory.createRandom(
            new Vector2f(400, 400),
            new Vector2f(200, 0),
            30
        );
    }
}
