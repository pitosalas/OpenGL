package com.salas.opengl;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.util.Log;

import android.content.Context;

public class GLRenderer implements GLSurfaceView.Renderer {
	private static final String tag = "GLRenderer";
	private final Context context;
	private final GLCube cube = new GLCube();
	
	public GLRenderer(Context context) {
		this.context = context;
	}
	
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		
		// Optional: disable dither to boost performance
		gl.glDisable(GL10.GL_DITHER);
		Log.d(tag, "onSurfaceCreated");
		
	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// Define the view frustrum
		gl.glViewport(0, 0,	width, height);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		
		float ratio = (float) width / height;
		GLU.gluPerspective(gl, 45.0f, ratio, 1, 100f);
	}
	
	public void onDrawFrame(GL10 gl) {
		// Clear the screen to black
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		// Position model so I can see it:
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glTranslatef(0,  0, -3.0f);
		
		// Draw the model
		cube.draw(gl);
		
		
	}
}
