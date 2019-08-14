package main;

import main.Physics.Gravity;
import main.WindowElements.BaseElement;
import main.jframe.WindowController;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class GravityPhysicsSimulator {
    boolean gameRunning = true;
    WindowController control = new WindowController("GravityPhysicsSimulator", new Dimension(1000, 1000));
    public List<BaseElement> elements = new ArrayList<>();
    Gravity grav = new Gravity();

    public GravityPhysicsSimulator() {
        gameLoop();
    }

    public static void main(String args[]) {

        GravityPhysicsSimulator gps = new GravityPhysicsSimulator();
    }

    public void gameLoop() {
        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
        int fps = 0;
        int fpsTime = 0;
        control.setup(this);
        int newFps = 0;
        while (gameRunning) {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength / ((double) OPTIMAL_TIME);
            fpsTime += updateLength;
            fps++;

            if (fpsTime >= 1000000000) {
                newFps = fps;
                fpsTime = 0;
                fps = 0;
            }
            control.render(this, String.valueOf(newFps));

            for (int i = 0; i < elements.size(); i++) {
                BaseElement element = elements.get(i);
                Point2D p = grav.gravity(element.x, element.y, 9.8);
                element.y = p.getY();
            }
        }


        try {
            Thread.sleep((lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
