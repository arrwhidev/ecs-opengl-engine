package com.arrwhidev.opengl.game.map;

public class Map {

    private final String name;
    private final int rows, cols, playerStartRow, playerStartCol;
    private final int[][] map;

    public Map(String name, int rows, int cols, int playerStartRow, int playerStartCol, int[][] map) {
        this.name = name;
        this.rows = rows;
        this.cols = cols;
        this.playerStartRow = playerStartRow;
        this.playerStartCol = playerStartCol;
        this.map = map;
    }

    public int get(int row, int col) {
        return map[row][col];
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public int getPlayerStartRow() {
        return playerStartRow;
    }

    public int getPlayerStartCol() {
        return playerStartCol;
    }
}
