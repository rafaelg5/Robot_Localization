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
public class Robot {

    /**
     * Class representing a laser sensor
     */
    private class LaserSensor extends Sensor {

        /**
         * Initializes a laser sensor
         * @param distance the real distance measured from the robot to the 
         * first obstacle in a straight line in the sensor's direction.
         * @param sd the standard deviation proportional to the cell size 
         */
        public LaserSensor(double distance, double sd) {
            super(distance, sd * 2);
        }
    }

    /**
     * Class representing an odometry sensor
     */
    private class OdometrySensor extends Sensor {

        /**
         * Initializes an odometry sensor
         * @param degrees the actual degrees rotated.
         * @param sd the standard deviation proportional to the degrees 
         */
        public OdometrySensor(double degrees, double sd){
            super(degrees, sd * 2);
        }
    }

    /**
     * Class representing a rotation sensor
     */
    private class RotationSensor extends Sensor {

        /**
         * Initializes a rotation sensor
         * @param degrees the actual degrees rotated.
         * @param sd the standard deviation proportional to the degrees 
         */
        public RotationSensor(double degrees, double sd) {
            super(degrees, sd * 2);
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

    /**
     * Moves the robot in the direction it is facing.
     */
    public void moveFront() {

    }

    /**
     * Turns the robot 
     * @param rad the number of degrees (in radians) of the turn
     */
    public void turn(double rad) {

    }
}
