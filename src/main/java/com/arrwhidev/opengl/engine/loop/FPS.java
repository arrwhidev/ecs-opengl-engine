package com.arrwhidev.opengl.engine.loop;

import java.util.Arrays;

public class FPS {

    private static final int MAX_SAMPLES = 20;
    private int tickindex = 0;
    private int ticksum = 0;
    private int[] ticklist = null;

    public int calculate(int newtick) {
        if (ticklist == null) {
            ticklist = new int[MAX_SAMPLES];
            Arrays.fill(ticklist, 0);
        }

        ticksum -= ticklist[tickindex];
        ticksum += newtick;
        ticklist[tickindex] = newtick;
        if (++tickindex == MAX_SAMPLES) {
            tickindex = 0;
        }

        return ticksum / MAX_SAMPLES;
    }
}
