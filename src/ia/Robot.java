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
         *
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
         *
         * @param degrees the actual degrees rotated.
         * @param sd the standard deviation proportional to the degrees
         */
        public OdometrySensor(double degrees, double sd) {
            super(degrees, sd * 2);
        }
    }

    /**
     * Class representing a rotation sensor
     */
    private class RotationSensor extends Sensor {

        /**
         * Initializes a rotation sensor
         *
         * @param degrees the actual degrees rotated.
         * @param sd the standard deviation proportional to the degrees
         */
        public RotationSensor(double degrees, double sd) {
            super(degrees, sd * 2);
        }
    }

    /*
     * Private class representing the robot's vision of a unit in a room.
     */
    private class RobotCell {

        public int x;
        public int y;
        public double theta;
        public boolean isObstacle;
        public double belief;

        // Length of one side of the cell (square)
        public final int LENGTH = Main.cellSize;

        public double distance;

        /**
         * Initializes the cell with a given point (x, y)
         *
         * @param xCoordinate the x point representing the cell
         * @param yCoordinate the y point representing the cell
         * @param deg the degrees of the angle theta
         * @param isObstacle determines if the cell is an obstacle or not
         */
        public RobotCell(int xCoordinate, int yCoordinate, double deg,
                boolean isObstacle) {
            this.x = xCoordinate;
            this.y = yCoordinate;
            theta = deg;
            this.isObstacle = isObstacle;
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

    public RobotCell innerWorld[][][];

    public Robot(Room room) {

        int rows = room.getRows();
        int columns = room.getCols();

        int notObstacles = rows * columns;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (room.isObstacle(i, j)) {
                    notObstacles--;
                }
            }
        }

        notObstacles *= 8;

        innerWorld = new RobotCell[rows][columns][8];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                for (int k = 0; k < 8; k++) {
                    if (room.isObstacle(i, j)) {
                        RobotCell rc = new RobotCell(i, j, k * 45, true);
                        rc.belief = (double) 1 / notObstacles;
                        innerWorld[i][j][k] = rc;
                    } else {
                        RobotCell rc = new RobotCell(i, j, k * 45, false);
                        rc.belief = 0.0;
                        innerWorld[i][j][k] = rc;

                    }
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                for (int k = 0; k < 8; k++) {
                    setDistance(i, j, k);
                }
            }
        }

    }

    private void setDistance(int i, int j, int k) {

        double distance = 0.0;
        RobotCell rc = innerWorld[i][j][k];
        double hip = Math.sqrt(2 * Math.pow(rc.LENGTH, 2));
        if (innerWorld[i][j][k].isObstacle) {
            innerWorld[i][j][k].distance = 0;
            return;
        }
        switch (k) {
            case 0:
                if (rc.x == 0) {
                    innerWorld[i][j][k].distance = 0;
                    return;
                }

                rc = innerWorld[i - 1][j][k];
                distance += rc.LENGTH;

                while (!rc.isObstacle && rc.x != 0) {
                    distance += rc.LENGTH;
                    rc = innerWorld[rc.x - 1][j][k];
                }
                innerWorld[i][j][k].distance = distance;
                break;

            case 1:

                if (rc.x == 0 || rc.y == innerWorld[0].length - 1) {
                    innerWorld[i][j][k].distance = 0;
                    return;
                }

                rc = innerWorld[i - 1][j + 1][k];

                while (!rc.isObstacle
                        && rc.x != 0 && rc.y != innerWorld[0].length - 1) {
                    distance += hip;
                    rc = innerWorld[rc.x - 1][rc.y + 1][k];
                }
                innerWorld[i][j][k].distance = distance;
                break;

            case 2:

                if (rc.y == innerWorld[0].length - 1) {
                    innerWorld[i][j][k].distance = 0;
                    return;
                }

                rc = innerWorld[i][j + 1][k];

                while (!rc.isObstacle && rc.y != innerWorld[0].length - 1) {
                    distance += rc.LENGTH;
                    rc = innerWorld[i][rc.y + 1][k];
                }
                innerWorld[i][j][k].distance = distance;
                break;

            case 3:

                if (rc.x == innerWorld.length - 1
                        || rc.y == innerWorld[0].length - 1) {
                    innerWorld[i][j][k].distance = 0;
                    return;
                }

                rc = innerWorld[i + 1][j + 1][k];

                while (!rc.isObstacle && rc.x != innerWorld.length - 1
                        && rc.y != innerWorld[0].length - 1) {
                    distance += hip;
                    rc = innerWorld[rc.x + 1][rc.y + 1][k];
                }
                innerWorld[i][j][k].distance = distance;
                break;

            case 4:
                if (rc.x == innerWorld.length - 1) {
                    innerWorld[i][j][k].distance = 0;
                    return;
                }

                rc = innerWorld[i + 1][j][k];
                distance += rc.LENGTH;

                while (!rc.isObstacle && rc.x != innerWorld.length - 1) {
                    distance += rc.LENGTH;
                    rc = innerWorld[rc.x + 1][j][k];
                }
                innerWorld[i][j][k].distance = distance;
                break;

            case 5:

                if (rc.x == innerWorld.length - 1 || rc.y == 0) {
                    innerWorld[i][j][k].distance = 0;
                    return;
                }

                rc = innerWorld[i + 1][j - 1][k];

                while (!rc.isObstacle
                        && rc.x != innerWorld.length - 1 && rc.y != 0) {
                    distance += hip;
                    rc = innerWorld[rc.x + 1][rc.y - 1][k];
                }
                innerWorld[i][j][k].distance = distance;
                break;

            case 6:

                if (rc.y == 0) {
                    innerWorld[i][j][k].distance = 0;
                    return;
                }

                rc = innerWorld[i][j - 1][k];

                while (!rc.isObstacle && rc.y != 0) {
                    distance += rc.LENGTH;
                    rc = innerWorld[i][rc.y - 1][k];
                }
                innerWorld[i][j][k].distance = distance;
                break;

            case 7:

                if (rc.x == 0 || rc.y == 0) {
                    innerWorld[i][j][k].distance = 0;
                    return;
                }

                rc = innerWorld[i - 1][j - 1][k];

                while (!rc.isObstacle && rc.x != 0 && rc.y != 0) {
                    distance += hip;
                    rc = innerWorld[rc.x - 1][rc.y - 1][k];
                }
                innerWorld[i][j][k].distance = distance;
                break;
        }
    }

    public double getBelief(int i, int j, int deg) {

        int k = deg / 45;

        return innerWorld[i][j][k].belief;
    }

    public void setBelief(int i, int j, int deg, double belief) {

        int k = deg / 45;
        innerWorld[i][j][k].belief = belief;
    }

    /**
     * Moves the robot in the direction it is facing.
     */
    public void moveFront() {

    }

    /**
     * Turns the robot
     *
     * @param rad the number of degrees (in radians) of the turn
     */
    public void turn(double rad) {

    }
}
