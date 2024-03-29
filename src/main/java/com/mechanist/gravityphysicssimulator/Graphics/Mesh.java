package com.mechanist.gravityphysicssimulator.Graphics;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Mesh {
    private Vertex[] vertices;
    private int[] indices;
    private Material mat;
    private int vao, positionBufferObject, indexBufferObject, colorBufferObject, textureBufferObject;

    public Mesh(Vertex[] vertices, int[] indices, Material mat) {
        this.vertices = vertices;
        this.indices = indices;
        this.mat = mat;
    }

    public void create() {
        mat.create();

        vao = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vao);

        FloatBuffer positionBuffer = MemoryUtil.memAllocFloat(vertices.length * 3);
        float[] positionData = new float[vertices.length * 3];
        for (int i = 0; i < vertices.length; i++) {
            positionData[i * 3] = vertices[i].getPosition().getX();
            positionData[i * 3 + 1] = vertices[i].getPosition().getY();
            positionData[i * 3 + 2] = vertices[i].getPosition().getZ();
        }
        positionBuffer.put(positionData).flip();

        positionBufferObject = storeData(positionBuffer, 0, 3);

        FloatBuffer colorBuffer = MemoryUtil.memAllocFloat(vertices.length * 3);
        float[] colorData = new float[vertices.length * 3];
        for (int i = 0; i < vertices.length; i++) {
            colorData[i * 3] = vertices[i].getColor().getX();
            colorData[i * 3 + 1] = vertices[i].getColor().getY();
            colorData[i * 3 + 2] = vertices[i].getColor().getZ();
        }
        colorBuffer.put(colorData).flip();

        colorBufferObject = storeData(colorBuffer, 1, 3);

        FloatBuffer textureBuffer = MemoryUtil.memAllocFloat(vertices.length * 2);
        float[] textureData = new float[vertices.length * 2];
        for (int i = 0; i < vertices.length; i++) {
            textureData[i * 2] = vertices[i].getTextureCoord().getX();
            textureData[i * 2 + 1] = vertices[i].getTextureCoord().getY();

        }
        textureBuffer.put(textureData).flip();

        textureBufferObject = storeData(textureBuffer, 2, 2);

        IntBuffer indicesBuffer = MemoryUtil.memAllocInt(indices.length);
        indicesBuffer.put(indices).flip();
        indexBufferObject = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indexBufferObject);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    private int storeData(FloatBuffer buffer, int index, int size) {
        int bufferID = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, bufferID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(index, size, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        return bufferID;
    }

    public void destroy() {
        GL15.glDeleteBuffers(positionBufferObject);
        GL15.glDeleteBuffers(colorBufferObject);
        GL15.glDeleteBuffers(indexBufferObject);
        GL15.glDeleteBuffers(textureBufferObject);

        GL30.glDeleteVertexArrays(vao);

        mat.destroy();
    }

    public Vertex[] getVertices() {
        return vertices;
    }

    public int[] getIndices() {
        return indices;
    }

    public int getVAO() {
        return vao;
    }


    public int getPositionBufferObject() {
        return positionBufferObject;
    }

    public int getColorBufferObject() {
        return colorBufferObject;
    }

    public int getIndexBufferObject() {
        return indexBufferObject;
    }

    public int getTextureBufferObject() {
        return textureBufferObject;
    }

    public Material getMat() {
        return mat;
    }
}
