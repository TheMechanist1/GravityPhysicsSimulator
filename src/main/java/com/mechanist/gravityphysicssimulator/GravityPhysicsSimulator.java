package com.mechanist.gravityphysicssimulator;

import com.mechanist.gravityphysicssimulator.Graphics.Mesh;
import com.mechanist.gravityphysicssimulator.Graphics.Renderer;
import com.mechanist.gravityphysicssimulator.Graphics.Shader;
import com.mechanist.gravityphysicssimulator.Graphics.Vertex;
import com.mechanist.gravityphysicssimulator.Math.Vector3f;
import com.mechanist.gravityphysicssimulator.Render.WindowController;

import static org.lwjgl.glfw.GLFW.*;

public class GravityPhysicsSimulator implements Runnable {
    public final int WIDTH = 980, HEIGHT = 980;
    public Thread game;
    public WindowController window;
    public Renderer renderer;
    public Shader shader;
    public Mesh mesh = new Mesh(new Vertex[]{
            new Vertex(new Vector3f(-0.5f, 0.5f, 0f), new Vector3f(1f, 0f, 0f)),
            new Vertex(new Vector3f(0.5f, 0.5f, 0f), new Vector3f(1f, 1f, 0f)),
            new Vertex(new Vector3f(0.5f, -0.5f, 0f), new Vector3f(0f, 1f, 0f)),
            new Vertex(new Vector3f(-0.5f, -0.5f, 0f), new Vector3f(0f, 0f, 1f)),

    }, new int[]{
            0, 1, 2, 0, 3, 2
    });
    private boolean shouldClose;

    public static void main(String[] args) {
        new GravityPhysicsSimulator().start();
    }

    public void start() {
        game = new Thread(this, "game");
        game.run();
    }

    public void init() {
        window = new WindowController(WIDTH, HEIGHT, "Gravity");
        shader = new Shader("/Shaders/mainVertex.glsl", "/Shaders/mainFragment.glsl");
        renderer = new Renderer(shader);
//        window.setBackgroundColor(1.0f,1.0f,1.0f);
        window.create();
        mesh.create();
        shader.create();
        shouldClose = false;

    }

    public void run() {
        init();

        while (!window.shouldClose() && !window.input.isKeyDown(GLFW_KEY_ESCAPE)) {
            update();
            render();
            if (window.input.isKeyDown(GLFW_KEY_F11)) window.setFullscreen(true);
        }
        close();
    }

    private void close() {
        window.destroy();
        mesh.destroy();
        shader.destroy();
    }

    private void update() {
        window.update();
        if (window.input.isButtonDown(GLFW_MOUSE_BUTTON_LEFT)) {
//            System.out.println(window.input.getMouseX() + " " + window.input.getMouseY() + " " + window.input.getScrollX() + " " + window.input.getScrollY());
        }

    }

    private void render() {
        renderer.renderMesh(mesh);
        window.swapBuffers();

    }


}
