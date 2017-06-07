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
public class Robot {

    private class LaserSensor extends Sensor {

        public LaserSensor(double distance, double cellSize) {
            super(distance, cellSize * 2);
        }
    }

    private class OdometrySensor extends Sensor {

        public OdometrySensor(double distance, double cellSize) {
            super(distance, cellSize * 2);
        }
    }

    private class RotationSensor extends Sensor {

        public RotationSensor(double distance, double cellSize) {
            super(distance, cellSize * 2);
        }        
    }

    public LaserSensor lS0;
    public LaserSensor lS45;
    public LaserSensor lS90;
    public LaserSensor lS135;
    public LaserSensor lS180;
    public LaserSensor lS225;
    public LaserSensor lS270;
    public LaserSensor lS315;
    public OdometrySensor oS;
    public RotationSensor rS;

    public void moveFront() {

    }

    public void turn(double rad) {

    }
}
