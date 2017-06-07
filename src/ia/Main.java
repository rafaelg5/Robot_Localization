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
import processing.core.PFont;
import java.util.Random;

public class Main extends PApplet {

    PFont font;
    int cellSize = 60;
    int columns = 10, rows = 8;

    Room room;

    @Override
    public void settings() {
        size(columns * cellSize, rows * cellSize);
    }

    @Override
    public void setup() {

        background(200);
        font = createFont("Arial", 12, true);
        textFont(font, 12);
        room = new Room(rows, columns, true);

    }

    @Override
    public void draw() {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (room.isObstacle(i, j)) {
                    stroke(0);
                    fill(102, 51, 0);
                    rect(j * cellSize, i * cellSize, cellSize, cellSize);
                } 
            }
        }
    }

    static public void main(String args[]) {
        PApplet.main(new String[]{"ia.Main"});
    }

}
