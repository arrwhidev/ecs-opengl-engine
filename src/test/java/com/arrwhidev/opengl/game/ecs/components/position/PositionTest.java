package com.arrwhidev.opengl.game.ecs.components.position;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PositionTest {

    @Test
    void shouldApplyScaleWhereNecessary() {
        Position p = new Position(100, 150, 20, 30, 4);
        assertThat(p.left(), is(100f));
        assertThat(p.right(), is(100f + (20 * 4)));
        assertThat(p.top(), is(150f));
        assertThat(p.bottom(), is(150f + (30 * 4)));
        assertThat(p.getWidth(), is(20f * 4));
        assertThat(p.getHeight(), is(30f * 4));
        assertThat(p.center().x, is(140f));
        assertThat(p.center().y, is(210f));
    }
}