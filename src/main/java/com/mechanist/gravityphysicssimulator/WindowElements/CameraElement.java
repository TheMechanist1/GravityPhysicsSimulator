package com.mechanist.gravityphysicssimulator.WindowElements;

import com.mechanist.gravityphysicssimulator.Math.Vector2f;
import com.mechanist.gravityphysicssimulator.Math.Vector3f;
import com.mechanist.gravityphysicssimulator.Render.Input;
import com.mechanist.gravityphysicssimulator.Render.WindowController;
import org.lwjgl.glfw.GLFW;

public class CameraElement {
    private Vector3f position, rotation;
    private float moveSpeed = 0.1f;
    private double oldMouseX, oldMouseY;
    private Vector3f mousePos;

    public CameraElement(Vector3f position, Vector3f rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public void update() {
        Vector2f size = WindowController.SIZE;

        if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_MIDDLE)) {
            mousePos = new Vector3f((float) Input.getMouseX(), (float) Input.getMouseY(), 0);
            Vector3f vector = new Vector3f((float) (mousePos.getX() - oldMouseX), (float) (mousePos.getY() - oldMouseY), 0f);

            oldMouseX = Input.getMouseX();
            oldMouseY = Input.getMouseY();

            position = Vector3f.add(position, new Vector3f(-(vector.getX() / size.getX()), vector.getY() / size.getY(), 0));
        } else {
            oldMouseX = Input.getMouseX();
            oldMouseY = Input.getMouseY();
        }


        if (Input.isKeyDown(GLFW.GLFW_KEY_W)) position = Vector3f.add(position, new Vector3f(0, 0, -moveSpeed));
        if (Input.isKeyDown(GLFW.GLFW_KEY_A)) position = Vector3f.add(position, new Vector3f(-moveSpeed, 0, 0));
        if (Input.isKeyDown(GLFW.GLFW_KEY_S)) position = Vector3f.add(position, new Vector3f(0, 0, moveSpeed));
        if (Input.isKeyDown(GLFW.GLFW_KEY_D)) position = Vector3f.add(position, new Vector3f(moveSpeed, 0, 0));
        if (Input.isKeyDown(GLFW.GLFW_KEY_SPACE)) position = Vector3f.add(position, new Vector3f(0, moveSpeed, 0));
        if (Input.isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT))
            position = Vector3f.add(position, new Vector3f(0, -moveSpeed, 0));
        if (Input.isKeyDown(GLFW.GLFW_KEY_Q)) rotation = Vector3f.add(rotation, new Vector3f(0, moveSpeed, 0));

    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getRotation() {
        return rotation;
    }
}
