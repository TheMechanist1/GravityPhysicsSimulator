package com.mechanist.gravityphysicssimulator.Graphics;

import com.mechanist.gravityphysicssimulator.Math.Vector2f;
import com.mechanist.gravityphysicssimulator.Math.Vector3f;

public class Vertex {
    private Vector3f position, color;
    private Vector2f textureCoord;

    public Vertex(Vector3f position, Vector3f color, Vector2f textureCoord) {
        this.position = position;
        this.color = color;
        this.textureCoord = textureCoord;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getColor() {
        return color;
    }

    public Vector2f getTextureCoord() {
        return textureCoord;
    }
}
