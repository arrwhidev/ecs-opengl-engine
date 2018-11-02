package com.arrwhidev.opengl.game.map;

import com.arrwhidev.opengl.engine.Window;

import java.util.ArrayList;
import java.util.List;

public class TileMap {

    private Window window;
    private Tile[][] tiles;
    private int rows, cols;

    public TileMap(Window window, Map map) {
        this.window = window;
        rows = map.getRows();
        cols = map.getCols();
        tiles = new Tile[rows][cols];

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                Tile tile =  createTile(map.get(y, x));
                tile.setPosition(x * Tile.SIZE, y * Tile.SIZE);
                tiles[y][x] = tile;
            }
        }
    }

    private Tile createTile(int id) {
        return new Tile(TileType.byId(id));
    }

    public Tile get(int row, int col) {
        return tiles[row][col];
    }

    public List<Tile> getTiles() {
        List<Tile> tiles = new ArrayList<>();

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                tiles.add(this.tiles[x][y]);
            }
        }

        return tiles;
    }
}
