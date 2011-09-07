package com.salas.miniengine;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

public class GEShapeCube extends GEShape {
	private final IntBuffer mVertexBuffer;
	private final IntBuffer mTextureBuffer;
	public GEShapeCube() {
		int one = 65536;
		int p = one / 2;
		int m = -p;
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

		int textCoords[] = {
				//Front
				0, p, p, p, 0, 0, p, 0,
				//Back
				p, p, p, 0, 0, 0, 0,
				//Left
				p, p, p, 0, 0, p, 0, 0,
				//Right
				p, p, p, 0, 0, p, 0, 0,
				//Top
				p, 0, 0, 0, p, p, 0, p,
				//Bottom
				0, 0, 0, p, p, 0, p, p };
		ByteBuffer tbb = ByteBuffer.allocateDirect(textCoords.length * 4);
		tbb.order(ByteOrder.nativeOrder());
		mTextureBuffer = tbb.asIntBuffer();
		mTextureBuffer.put(textCoords);
		mTextureBuffer.position(0);
	}
	
	static void loadTextture(GL10 gl, Context ctx, int resource) {
		Bitmap bmp = BitmapFactory.decodeResource(ctx.getResources(), resource);
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bmp, 0);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
		bmp.recycle();
	}

	protected void draw_verteces(GL10 gl) {
		// front
		gl.glVertexPointer(3, GL10.GL_FIXED, 0, mVertexBuffer);		
		gl.glColor4f(0, 0, 1, 0.1f);
		gl.glNormal3f(0, 0, 1);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
		
		// Back
		gl.glNormal3f(0, 0, -1);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 4, 4);
		
		// Left
		gl.glColor4f(1, 0, 0, 0.1f);
		gl.glNormal3f(-1, 0, 0);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 8, 4);
		
		// Right
		gl.glNormal3f(1, 0, 0);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 12, 4);

		// Top
		gl.glColor4f(0, 1, 0, 0.1f);
		gl.glNormal3f(0, 1, 0);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 16, 4);
		
		// Bottom
		gl.glNormal3f(0, -1, 0);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 20, 4);
	}


}
