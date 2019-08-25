package com.mechanist.gravityphysicssimulator.WindowElements;

import com.mechanist.gravityphysicssimulator.Graphics.Mesh;
import com.mechanist.gravityphysicssimulator.Math.Vector3f;

public class BaseElement {
    private Vector3f position, rotation, scale;
    private Mesh mesh;
    private float temp;

    public BaseElement(Vector3f position, Vector3f rotation, Vector3f scale, Mesh mesh) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.mesh = mesh;
    }


    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public Vector3f getScale() {
        return scale;
    }

    public Mesh getMesh() {
        return mesh;
    }
}
