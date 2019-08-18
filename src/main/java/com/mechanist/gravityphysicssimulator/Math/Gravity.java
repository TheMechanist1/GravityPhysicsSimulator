package com.mechanist.gravityphysicssimulator.Math;

import java.awt.geom.Point2D;

public class Gravity {

    public Point2D gravity(double x, double y, double gravityStrength) {
        Point2D point = new Point2D.Float();
        if (gravityStrength == 0) {
            point.setLocation(x, y);
            return point;
        } else {
            double deltaY = Math.pow((1f / 2f) * gravityStrength, 2);
            point.setLocation(x, y + deltaY);
            return point;
        }
    }
}
