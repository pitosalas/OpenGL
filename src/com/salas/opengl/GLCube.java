package com.salas.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

public class GLCube {
	private final IntBuffer mVertexBuffer;
	public GLCube() {
		int one = 65536;
		int m = one / 2;
		int p = -m;
		int vertices[] = {
				//Front
				m, m, p, p, m, p,
				m, p, p, p, p, p,
				
				// Back
				m, m, m, m, p, m,
				p, m, m, p, p, m,
				
				// Left
				m, m, p, m, p, p,
				m, m, m, m, p, m,
				
				// Right
				p, m, m, p, p, m,
				p, m, p, p, p, p,
				
				// Top
				m, p, p, p, p, p,
				m, p, m, p, p, m,
				
				// Bottom
				m, m, p, m, m, m,
				p, m, p, p, m, m };
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		mVertexBuffer = vbb.asIntBuffer();
		mVertexBuffer.put(vertices);
		mVertexBuffer.position(0);
	}
	
	public void draw(GL10 gl) {
		gl.glColor4f(1, 1, 1, 1);
		gl.glNormal3f(0, 0, 1);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
		gl.glNormal3f(0, 0, -1);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 4, 4);
		
		gl.glColor4f(1, 1, 1, 1);
		gl.glNormal3f(-1, 0, 0);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 8, 4);
		gl.glNormal3f(1, 0, 0);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 12, 4);

		gl.glColor4f(1, 1, 1, 1);
		gl.glNormal3f(0, 1, 0);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 16, 4);
		gl.glNormal3f(0, -1, 0);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 20, 4);

	}

}
