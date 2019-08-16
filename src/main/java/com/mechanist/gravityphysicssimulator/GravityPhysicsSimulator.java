package com.mechanist.gravityphysicssimulator;

import com.mechanist.gravityphysicssimulator.Render.WindowController;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_E;

public class GravityPhysicsSimulator implements Runnable {
    public final int WIDTH = 1280, HEIGHT = 960;
    public Thread game;
    public WindowController window;

    public static void main(String[] args) {
        new GravityPhysicsSimulator().start();
    }

    public void start() {
        game = new Thread(this, "game");
        game.run();
    }

    public void init() {
        window = new WindowController(WIDTH, HEIGHT, "Gravity");
        window.create();

    }

    public void run() {
        init();

        while (!window.shouldClose()) {
            update();
            render();
            if (window.input.isKeyDown(GLFW_KEY_E)) {
                System.out.println("E");
            }
        }
        window.destroy();
    }

    private void update() {
        window.update();

    }

    private void render() {
        window.swapBuffers();
    }


}
