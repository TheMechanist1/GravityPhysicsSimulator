package main;

import main.jframe.WindowController;

import java.awt.*;

public class GravityPhysicsSimulator {

    public static void main(String args[]) {
        Dimension frameDimension = new Dimension(1000,1000);
        new WindowController("GravityPhysicsSimulator", frameDimension);
    }

}
