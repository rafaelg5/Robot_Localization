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

/**
 *
 * @author Rafael
 */
import processing.core.PApplet;
import java.util.Random;

public class Main extends PApplet {

    public static int cellSize = 60;
    private final int columns = 10;
    private final int rows = 8;
    private Room room;
    private Random random;
    private Robot robot;
    private float robotSize;
    private int robotCenterX;
    private int robotCenterY;
    private double ang[];
    private double dir;

    @Override
    public void settings() {
        size(columns * cellSize, rows * cellSize + 70);
    }

    @Override
    public void setup() {

        room = new Room(rows, columns, true);
        robot = new Robot(room);
        random = new Random();
        robotSize = cellSize / 2f;

        int r1;
        int r2;
        do {
            r1 = random.nextInt(rows);
            r2 = random.nextInt(columns);

        } while (room.isObstacle(r1, r2));

        robotCenterX = r2 * cellSize + (cellSize / 2);
        robotCenterY = r1 * cellSize + (cellSize / 2);

        ang = new double[8];
        double val = 0;
        for (int i = 0; i < ang.length; i++, val += Math.PI / 4) {
            ang[i] = val;
        }

        dir = ang[random.nextInt(8)];
    }

    @Override
    public void keyReleased() {

        if (key == 'd') {
            if (dir == ang[7]) {
                dir = 0;
                return;
            }
            dir += Math.PI / 4;
            redraw();
        }

        if (key == 'a') {
            if (dir == ang[0]) {
                dir = ang[7];
                return;
            }
            dir -= Math.PI / 4;
            redraw();
        }

        int centerX = (robotCenterX - (cellSize / 2)) / cellSize;
        int centerY = (robotCenterY - (cellSize / 2)) / cellSize;

        if (key == 'w') {
            if (dir == ang[0]) {
                if (centerY == 0) {
                    return;
                }
                if (room.isObstacle(centerY - 1, centerX)) {
                    return;
                }
                robotCenterY -= cellSize;
                redraw();
            }

            if (dir == ang[1]) {
                if (centerY == 0 || centerX == columns - 1) {
                    return;
                }
                if (room.isObstacle(centerY - 1, centerX + 1)) {
                    return;
                }
                robotCenterY -= cellSize;
                robotCenterX += cellSize;
                redraw();
            }

            if (dir == ang[2]) {
                if (centerX == columns - 1) {
                    return;
                }
                if (room.isObstacle(centerY, centerX + 1)) {
                    return;
                }
                robotCenterX += cellSize;
                redraw();
            }

            if (dir == ang[3]) {
                if (centerY == rows - 1 || centerX == columns - 1) {
                    return;
                }
                if (room.isObstacle(centerY + 1, centerX + 1)) {
                    return;
                }
                robotCenterY += cellSize;
                robotCenterX += cellSize;
                redraw();
            }

            if (dir == ang[4]) {
                if (centerY == rows - 1) {
                    return;
                }
                if (room.isObstacle(centerY + 1, centerX)) {
                    return;
                }
                robotCenterY += cellSize;
                redraw();
            }

            if (dir == ang[5]) {
                if (centerY == rows - 1 || centerX == 0) {
                    return;
                }
                if (room.isObstacle(centerY + 1, centerX - 1)) {
                    return;
                }
                robotCenterY += cellSize;
                robotCenterX -= cellSize;
                redraw();
            }

            if (dir == ang[6]) {
                if (centerX == 0) {
                    return;
                }
                if (room.isObstacle(centerY, centerX - 1)) {
                    return;
                }
                robotCenterX -= cellSize;
                redraw();
            }

            if (dir == ang[7]) {
                if (centerY == 0 || centerX == 0) {
                    return;
                }
                if (room.isObstacle(centerY - 1, centerX - 1)) {
                    return;
                }
                robotCenterY -= cellSize;
                robotCenterX -= cellSize;
                redraw();
            }
        }
    }

    @Override
    public void draw() {

        background(200);
        fill(51, 204, 0);
        ellipse(robotCenterX, robotCenterY, robotSize, robotSize);

        line(robotCenterX, robotCenterY,
                (robotCenterX + (robotSize / 2 + 1) * (float) Math.sin(dir)),
                (robotCenterY - (robotSize / 2 + 1) * (float) Math.cos(dir)));

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (room.isObstacle(i, j)) {
                    stroke(0);
                    fill(102, 51, 0);
                    rect(j * cellSize, i * cellSize, cellSize, cellSize);
                }
            }
        }

        fill(100);
        rect(0, rows * cellSize, columns * cellSize, 70);

        fill(255);
        text("A: Turn robot 45° left", 40, rows * cellSize + 30);
        text("D: Turn robot 45° right", 40, rows * cellSize + 50);
        text("W: Move robot in the facing direction",
                3 * cellSize, rows * cellSize + 30);

    }

    static public void main(String args[]) {
        
        PApplet.main(new String[]{"ia.Main"});
    }

}
