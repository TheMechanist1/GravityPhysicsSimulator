package com.mechanist.gravityphysicssimulator;

import com.mechanist.gravityphysicssimulator.Graphics.Mesh;
import com.mechanist.gravityphysicssimulator.Graphics.Renderer;
import com.mechanist.gravityphysicssimulator.Graphics.Shader;
import com.mechanist.gravityphysicssimulator.Math.Vector3f;
import com.mechanist.gravityphysicssimulator.Render.Input;
import com.mechanist.gravityphysicssimulator.Render.WindowController;
import com.mechanist.gravityphysicssimulator.WindowElements.BaseElement;
import com.mechanist.gravityphysicssimulator.WindowElements.CameraElement;
import com.mechanist.gravityphysicssimulator.utils.MeshLoader;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_F11;

public class GravityPhysicsSimulator implements Runnable {
    public final int WIDTH = 980, HEIGHT = 980;
    public Thread game;
    public WindowController window;
    public Renderer renderer;
    public Shader shader;
    public List<BaseElement> list = new ArrayList<>();

    Mesh rectangleMesh = MeshLoader.fileToMesh("src/main/resources/Models/rectangleMesh");
    Mesh planeMesh = MeshLoader.fileToMesh("src/main/resources/Models/planeMesh");

    CameraElement camera = new CameraElement(new Vector3f(0f, 0f, 1f), new Vector3f(0f, 0f, 0f));
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
        renderer = new Renderer(window, shader, camera);
//        window.setBackgroundColor(1.0f,1.0f,1.0f);
        window.create();
        rectangleMesh.create();
        planeMesh.create();
        shader.create();


        list.add(new BaseElement(new Vector3f(0f, 0f, 0f), new Vector3f(0f, 0f, 0f), new Vector3f(1f, 1f, 1f), rectangleMesh));
        list.add(new BaseElement(new Vector3f(0f, 0f, 0f), new Vector3f(0f, 0f, 0f), new Vector3f(1f, 1f, 1f), planeMesh));


        shouldClose = false;

    }

    public void run() {
        init();

        while (!window.shouldClose() && !Input.isKeyDown(GLFW_KEY_ESCAPE)) {
            update();

            render();
            if (Input.isKeyDown(GLFW_KEY_F11)) window.setFullscreen(true);
        }
        close();
    }

    private void close() {
        window.destroy();
        rectangleMesh.destroy();
        planeMesh.destroy();
        shader.destroy();
    }

    private void update() {
        window.update();
        camera.update();

        for (int i = 0; i < list.size(); i++) {
//            Gravity.gravity(list.get(i), -0.001f);
        }

    }

    private void render() {
        for (int i = 0; i < list.size(); i++) {
            renderer.renderMesh(list.get(i));
        }
        window.swapBuffers();

    }


}
