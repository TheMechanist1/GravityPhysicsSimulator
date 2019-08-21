package com.mechanist.gravityphysicssimulator.Graphics;

import com.mechanist.gravityphysicssimulator.Math.Matrix4f;
import com.mechanist.gravityphysicssimulator.Render.WindowController;
import com.mechanist.gravityphysicssimulator.WindowElements.BaseElement;
import com.mechanist.gravityphysicssimulator.WindowElements.CameraElement;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

public class Renderer {
    private Shader shader;
    private WindowController window;
    private CameraElement camera;

    public Renderer(WindowController window, Shader shader, CameraElement camera) {
        this.shader = shader;
        this.window = window;
        this.camera = camera;
    }

    public void renderMesh(BaseElement element) {
        GL30.glBindVertexArray(element.getMesh().getVAO());
        GL30.glEnableVertexAttribArray(0);
        GL30.glEnableVertexAttribArray(1);
        GL30.glEnableVertexAttribArray(2);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, element.getMesh().getIndexBufferObject());
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL13.glBindTexture(GL11.GL_TEXTURE_2D, element.getMesh().getMat().getTextureID());
        shader.bind();
        shader.setUniform("model", Matrix4f.transform(element.getPosition(), element.getRotation(), element.getScale()));
        shader.setUniform("projection", window.getProjection());
        shader.setUniform("view", Matrix4f.view(camera.getPosition(), camera.getRotation()));
        GL11.glDrawElements(GL11.GL_TRIANGLES, element.getMesh().getIndices().length, GL11.GL_UNSIGNED_INT, 0);

        shader.unbind();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        GL30.glDisableVertexAttribArray(2);
        GL30.glDisableVertexAttribArray(1);
        GL30.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
    }
}
