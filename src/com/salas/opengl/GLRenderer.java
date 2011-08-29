package com.salas.opengl;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.util.Log;

import android.content.Context;

public class GLRenderer implements GLSurfaceView.Renderer {
	private static final String tag = "GLRenderer";
	private Context context;
	
	private long startTime;
	private long fpsStartTime;
	private long numframes;
	
	private final GLCube cube = new GLCube();
	
	public GLRenderer(Context context) {
		this.context = context;
	}
	
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		Log.d(tag, "onSurfaceCreated");

		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

		
		// Optional: disable dither to boost performance
		gl.glDisable(GL10.GL_DITHER);
		
		
		// Lighting
//		float lightAmbient[] = new float[] { 2.1f, 0.0f, 0.0f, 1};
		float lightDiffuse[] = new float[] { 1, 1, 1, 1 };
		float lightPos[] = new float[] { 10, 10, 10, 0.0f};
		
		gl.glEnable(GL10.GL_LIGHTING);
		gl.glEnable(GL10.GL_LIGHT0);
//		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, lightAmbient, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, lightDiffuse, 0);
//		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, lightDiffuse, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, lightPos, 0);
		
		// Motion
		startTime = System.currentTimeMillis();
		fpsStartTime = startTime;
		numframes = 0;
		
//		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
//		gl.glEnable(GL10.GL_TEXTURE_2D);
//		GLCube.loadTextture(gl, context, R.drawable.brandeis);
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
		gl.glTranslatef(0,  0, -20.0f);
		
		//... Set rotation angle based on the time
		long elapsed = System.currentTimeMillis() - startTime;
		gl.glRotatef(elapsed * (30f / 1000f), 0, 1, 0);
		gl.glRotatef(elapsed * (15f / 1000f), 1, 0, 0);
		
		// Draw the model
		
		float matOne[] = new float [] {0, 0, 1, 1};
		float matTwo[] = new float [] {1, 0, 1, 1};
		
		cube.draw(gl, -3, matOne);
		cube.draw(gl, 0, matTwo);
		
		numframes++;
		long fpsElaped = System.currentTimeMillis() - fpsStartTime;
		if (fpsElaped > 5 * 1000) { // Every 5 seconds
			float fps = (numframes * 1000.0f) / fpsElaped;
			Log.d("GL", "Frames per second: " + fps + "(" + numframes + " frames in " + fpsElaped + " ms)");
			fpsStartTime = System.currentTimeMillis();
			numframes = 0;	
		}
	}
}
