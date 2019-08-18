package com.mechanist.gravityphysicssimulator.Render;

import org.lwjgl.glfw.*;

public class Input {
    private boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
    private boolean[] mouse = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
    private double mouseX, mouseY;
    private double scrollX, scrollY;

    private GLFWKeyCallback keyboard;
    private GLFWCursorPosCallback mouseMove;
    private GLFWMouseButtonCallback mouseButtons;
    private GLFWScrollCallback scroll;

    public Input() {
        keyboard = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                keys[key] = (action != GLFW.GLFW_RELEASE);

            }
        };

        mouseMove = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                mouseX = xpos;
                mouseY = ypos;
            }
        };

        mouseButtons = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                mouse[button] = (action != GLFW.GLFW_RELEASE);
            }
        };

        scroll = new GLFWScrollCallback() {
            @Override
            public void invoke(long window, double xoffset, double yoffset) {
                scrollX += xoffset;
                scrollY += yoffset;
            }
        };
    }

    public void freeCallbacks() {
        keyboard.free();
        mouseMove.free();
        mouseButtons.free();
        scroll.free();
    }

    public boolean isKeyDown(int key) {
        return keys[key];
    }

    public boolean isButtonDown(int key) {
        return mouse[key];
    }

    public double getMouseX() {
        return mouseX;
    }

    public double getMouseY() {
        return mouseY;
    }

    public double getScrollX() {
        return scrollX;
    }

    public double getScrollY() {
        return scrollY;
    }

    public GLFWKeyCallback getKeyboardCallback() {
        return keyboard;
    }

    public GLFWCursorPosCallback getMouseMoveCallback() {
        return mouseMove;
    }

    public GLFWMouseButtonCallback getMouseButtonsCallback() {
        return mouseButtons;
    }

    public GLFWScrollCallback getScroll() {
        return scroll;
    }
}
