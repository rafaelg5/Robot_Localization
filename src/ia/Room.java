/*
 * The MIT License
 *
 * Copyright 2017 Rafael.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package ia;

import java.util.Random;

/**
 *
 * @author Rafael
 */
public class Room {

    private int rows;
    private int cols;
    private Cell matrix[][];

    /*
     * Private class representing a unit in a room.
     */
    private class Cell {
        
        public boolean isObstacle;
        public int x;
        public int y;
        
        // Length of one side of the cell (square)
        public final int LENGTH = Main.cellSize;

        /**
         * Initializes the cell with a given point (x, y)
         * @param xCoordinate the x point representing the cell
         * @param yCoordinate the y point representing the cell
         */
        public Cell(int xCoordinate, int yCoordinate) {
            this.x = xCoordinate;
            this.y = yCoordinate;
        }
    }

    /**
     * Constructs a room which is a 2d matrix, each position representing
     * a tile in a room
     * @param rows the number of rows 
     * @param cols the number of columns
     * @param hasRandomObstacles indicates if it will have obstacles in 
     * random positions of the room
     */
    public Room(int rows, int cols, boolean hasRandomObstacles) {

        this.rows = rows;
        this.cols = cols;
        matrix = new Cell[rows][cols];
        if (hasRandomObstacles) {

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = new Cell(i, j);
                    int rn = new Random().nextInt(5);
                    if (rn == 0) {
                        matrix[i][j].isObstacle = true;
                    }
                }
            }
        } else {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = new Cell(i, j);
                }
            }
        }

    }

    /**
     * @return the rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * @param rows the rows to set
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * @return the cols
     */
    public int getCols() {
        return cols;
    }

    /**
     * @param cols the cols to set
     */
    public void setCols(int cols) {
        this.cols = cols;
    }

    /**
     * Determines if the Cell with coordinates (i, j) is an obstacle or not.
     * @param i the x coordinate of the Cell
     * @param j the y coordinate of the Cell
     * @return <b>True</b> if the Cell is an obstacle. <b>False</b> in other case.
     */
    public boolean isObstacle(int i, int j) {
        return matrix[i][j].isObstacle;
    }
}
