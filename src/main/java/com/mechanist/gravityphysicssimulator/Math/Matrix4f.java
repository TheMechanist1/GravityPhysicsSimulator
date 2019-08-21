package com.mechanist.gravityphysicssimulator.Math;

import java.util.Arrays;

public class Matrix4f {
    public static final int SIZE = 4;
    private float[] elements = new float[SIZE * SIZE];

    public static Matrix4f identity() {
        Matrix4f result = new Matrix4f();

        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                result.set(x, y, 0);
            }
        }
        result.set(0, 0, 1);
        result.set(1, 1, 1);
        result.set(2, 2, 1);
        result.set(3, 3, 1);


        return result;
    }

    public static Matrix4f translate(Vector3f translation) {
        Matrix4f result = Matrix4f.identity();

        result.set(3, 0, translation.getX());
        result.set(3, 1, translation.getY());
        result.set(3, 2, translation.getZ());

        return result;

    }

    public static Matrix4f rotate(float angle, Vector3f axis) {
        Matrix4f result = Matrix4f.identity();

        float cos = (float) Math.cos(Math.toRadians(angle));
        float sin = (float) Math.sin(Math.toRadians(angle));
        float inverseCos = 1 - cos;
        float x = axis.getX();
        float y = axis.getY();
        float z = axis.getZ();

        result.set(0, 0, cos + x * x * inverseCos);
        result.set(0, 1, x * y * inverseCos - z * sin);
        result.set(0, 2, x * z * inverseCos + y * sin);

        result.set(1, 0, y * x * inverseCos + z * sin);
        result.set(1, 1, cos + y * y * inverseCos);
        result.set(1, 2, y * z * inverseCos - x * sin);

        result.set(2, 0, z * x * inverseCos - y * sin);
        result.set(2, 1, z * y * inverseCos + x * sin);
        result.set(2, 2, cos + z * z * inverseCos);

        return result;

    }

    public static Matrix4f scale(Vector3f scalar) {
        Matrix4f result = Matrix4f.identity();

        result.set(0, 0, scalar.getX());
        result.set(1, 1, scalar.getY());
        result.set(2, 2, scalar.getZ());

        return result;

    }

    public static Matrix4f multiply(Matrix4f matrix1, Matrix4f matrix2) {
        Matrix4f result = Matrix4f.identity();

        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                result.set(x, y, matrix1.get(x, 0) * matrix2.get(0, y) +
                        matrix1.get(x, 1) * matrix2.get(1, y) +
                        matrix1.get(x, 2) * matrix2.get(2, y) +
                        matrix1.get(x, 3) * matrix2.get(3, y));
            }
        }

        return result;
    }

    public static Matrix4f transform(Vector3f position, Vector3f rotation, Vector3f scale) {
        Matrix4f result = Matrix4f.identity();

        Matrix4f translationMatrix = Matrix4f.translate(position);
        Matrix4f rotationMatrixX = Matrix4f.rotate(rotation.getX(), new Vector3f(1, 0, 0));
        Matrix4f rotationMatrixY = Matrix4f.rotate(rotation.getY(), new Vector3f(0, 1, 0));
        Matrix4f rotationMatrixZ = Matrix4f.rotate(rotation.getZ(), new Vector3f(0, 0, 1));
        Matrix4f scaleMatrix = Matrix4f.scale(scale);

        Matrix4f rotationMatrix = Matrix4f.multiply(rotationMatrixX, Matrix4f.multiply(rotationMatrixY, rotationMatrixZ));

        result = Matrix4f.multiply(translationMatrix, Matrix4f.multiply(rotationMatrix, scaleMatrix));
        return result;
    }

    public static Matrix4f projection(float fov, float aspect, float near, float far) {
        Matrix4f result = Matrix4f.identity();

        float tanFov = (float) Math.tan(Math.toRadians(fov / 2));
        float range = far - near;

        result.set(0, 0, 1f / (aspect * tanFov));
        result.set(1, 1, 1f / tanFov);
        result.set(2, 2, -((far + near) / range));
        result.set(2, 3, -1f);
        result.set(3, 2, -((2 * far * near) / range));
        result.set(3, 3, 0f);

        return result;
    }

    public static Matrix4f view(Vector3f position, Vector3f rotation) {
        Matrix4f result;

        Vector3f negativePos = new Vector3f(-position.getX(), -position.getY(), -position.getZ());
        Matrix4f translationMatrix = Matrix4f.translate(negativePos);
        Matrix4f rotationMatrixX = Matrix4f.rotate(rotation.getX(), new Vector3f(1, 0, 0));
        Matrix4f rotationMatrixY = Matrix4f.rotate(rotation.getY(), new Vector3f(0, 1, 0));
        Matrix4f rotationMatrixZ = Matrix4f.rotate(rotation.getZ(), new Vector3f(0, 0, 1));


        Matrix4f rotationMatrix = Matrix4f.multiply(rotationMatrixZ, Matrix4f.multiply(rotationMatrixX, rotationMatrixY));

        result = Matrix4f.multiply(translationMatrix, rotationMatrix);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix4f matrix4f = (Matrix4f) o;
        return Arrays.equals(elements, matrix4f.elements);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(elements);
    }

    public float get(int x, int y) {
        return elements[y * SIZE + x];
    }

    public void set(int x, int y, float value) {
        elements[y * SIZE + x] = value;
    }

    public float[] getAll() {
        return elements;
    }
}
