package com.mechanist.gravityphysicssimulator.Render;

import com.mechanist.gravityphysicssimulator.Math.Matrix4f;
import com.mechanist.gravityphysicssimulator.Math.Vector2f;
import com.mechanist.gravityphysicssimulator.Math.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;


public class WindowController {
    public static long time;
    public int frames;
    public Input input = new Input();
    private int width, height;
    private String title;
    private long window;
    private Vector3f backgroundColor = new Vector3f(1f, 0f, 0f);
    private GLFWWindowSizeCallback sizeCallback;
    private boolean isResized;
    private boolean isFullscreen;
    private int[] windowPosX = new int[1];
    private int[] windowPosY = new int[1];
    private Matrix4f projection;
    public static Vector2f SIZE;

    public WindowController(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        projection = Matrix4f.projection(70f, (float) this.width / (float) this.height, 0.1f, 10000.0f);
        SIZE = new Vector2f(width, height);
    }

    public void create() {

        if (!GLFW.glfwInit()) {
            System.out.println("Glfw failed to init");
            return;
        }

        window = GLFW.glfwCreateWindow(width, height, title, isFullscreen ? GLFW.glfwGetPrimaryMonitor() : 0, 0);

        if (window == 0) {
            System.out.println("Failed to create window");
            return;
        }

        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        windowPosX[0] = (videoMode.width() - width) / 2;
        windowPosY[0] = (videoMode.height() - height) / 2;
        GLFW.glfwSetWindowPos(window, windowPosX[0], windowPosY[0]);
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();
        GL11.glClearColor(backgroundColor.getX(), backgroundColor.getY(), backgroundColor.getZ(), 1.0f);


        createCallbacks();

        GLFW.glfwShowWindow(window);

        GLFW.glfwSwapInterval(1);
        time = System.nanoTime();
    }

    private void createCallbacks() {
        sizeCallback = new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int w, int h) {
                width = w;
                height = h;
                isResized = true;
            }
        };

        GLFW.glfwSetKeyCallback(window, input.getKeyboardCallback());
        GLFW.glfwSetCursorPosCallback(window, input.getMouseMoveCallback());
        GLFW.glfwSetMouseButtonCallback(window, input.getMouseButtonsCallback());
        GLFW.glfwSetScrollCallback(window, input.getScroll());
        GLFW.glfwSetWindowSizeCallback(window, sizeCallback);
    }

    public void update() {
        if (isResized) {
            projection = Matrix4f.projection(70f, (float) this.width / (float) this.height, 0.1f, 10000f);
            GL11.glViewport(0, 0, width, height);
            isResized = false;
            SIZE = new Vector2f(width, height);
        }


        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);



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
        GL11.glDrawPixels(10, 10, 0, 0, window);
    }

    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(window);
    }

    public void destroy() {
        input.freeCallbacks();
        sizeCallback.free();
        GLFW.glfwWindowShouldClose(window);
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }

    public void setBackgroundColor(float red, float green, float blue) {
        backgroundColor.set(red, green, blue);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getTitle() {
        return title;
    }

    public long getWindow() {
        return window;
    }

    public boolean isFullscreen() {
        return isFullscreen;
    }

    public void setFullscreen(boolean fullscreen) {
        isFullscreen = fullscreen;
        isResized = true;
        if (isFullscreen) {
            GLFW.glfwGetWindowPos(window, windowPosX, windowPosY);
            GLFW.glfwSetWindowMonitor(window, GLFW.glfwGetPrimaryMonitor(), 0, 0, width, height, 0);
        } else {
            GLFW.glfwSetWindowMonitor(window, 0, windowPosX[0], windowPosY[0], width, height, 0);
        }
    }

    public Matrix4f getProjection() {
        return projection;
    }
}
