package main;

import main.jframe.WindowController;

import java.awt.*;

public class GravityPhysicsSimulator {
    boolean gameRunning = true;
    WindowController control = new WindowController("GravityPhysicsSimulator", new Dimension(1000, 1000));

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
        control.setup();

        while (gameRunning) {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength / ((double) OPTIMAL_TIME);
            fpsTime += updateLength;
            fps++;

            if (fpsTime >= 1000000000) {
                System.out.println(fps);
                fpsTime = 0;
                fps = 0;
            }
            control.render();
        }


        try {
            Thread.sleep((lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
