package com.arrwhidev.opengl.game.map;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {

    public static Map load(LevelType levelType) {
        int rows = -1, cols = -1, playerStartRow = -1, playerStartCol = -1;
        int[][] map = null;

        InputStream resourceAsStream = Map.class.getResourceAsStream("/" + levelType.getName() + ".txt");
        try(Scanner sc = new Scanner(resourceAsStream)) {
            while(sc.hasNextLine()) {
                String line = sc.nextLine().trim();

                if (line.startsWith("#")) {
                    continue;
                } else if (line.startsWith("rows=")) {
                    rows = Integer.parseInt(line.split("rows=")[1]);
                } else if (line.startsWith("cols=")) {
                    cols = Integer.parseInt(line.split("cols=")[1]);
                } else if (line.startsWith("playerStartRow=")) {
                    playerStartRow = Integer.parseInt(line.split("playerStartRow=")[1]);
                } else if (line.startsWith("playerStartCol=")) {
                    playerStartCol = Integer.parseInt(line.split("playerStartCol=")[1]);
                } else {
                    map = new int[rows][cols];
                    for (int y = 0; y < rows; y++) {
                        String[] row = sc.nextLine().trim().split(",");
                        for (int x = 0; x < row.length; x++) {
                            map[y][x] = Integer.parseInt(row[x]);
                        }
                    }
                }
            }

            return new Map(levelType.getName(), rows, cols, playerStartRow, playerStartCol, map);
        }
    }
}
