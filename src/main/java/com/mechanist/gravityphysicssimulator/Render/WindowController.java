package com.mechanist.gravityphysicssimulator.Render;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;


public class WindowController {
    public static long time;
    public int frames;
    public Input input = new Input();
    private int width, height;
    private String title;
    private long window;

    public WindowController(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    public void create() {

        if (!GLFW.glfwInit()) {
            System.out.println("Glfw failed to init");
            return;
        }

        window = GLFW.glfwCreateWindow(width, height, title, 0, 0);

        if (window == 0) {
            System.out.println("Failed to create window");
            return;
        }

        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());

        GLFW.glfwSetWindowPos(window, (videoMode.width() - width) / 2, (videoMode.height() - height) / 2);

        GLFW.glfwMakeContextCurrent(window);

        GLFW.glfwSetKeyCallback(window, input.getKeyboardCallback());
        GLFW.glfwSetCursorPosCallback(window, input.getMouseMoveCallback());
        GLFW.glfwSetMouseButtonCallback(window, input.getMouseButtonsCallback());

        GLFW.glfwShowWindow(window);

        GLFW.glfwSwapInterval(1);
        time = System.nanoTime();
    }

    public void update() {
        GLFW.glfwPollEvents();
        frames++;
        if (System.nanoTime() > time + 1000000000) {
            GLFW.glfwSetWindowTitle(window, title + " | " + frames);
            time = System.nanoTime();
            frames = 0;
        }
    }

    public void swapBuffers() {
        GLFW.glfwSwapBuffers(window);
    }

    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(window);
    }

    public void destroy() {
        input.freeCallbacks();
        GLFW.glfwWindowShouldClose(window);
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }

}
