package com.salas.miniengine;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.util.Log;

import android.content.Context;

public class GERenderer implements GLSurfaceView.Renderer {
	private static final String tag = "ME";
	private final Context context;
	private final GEModel model;
	private GL10 gl;
	
	private long startTime;
	private long pulseStartTime;
	private long fpsStartTime;
	private long numframes;
	private boolean autoPan = false;
	private float eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ;
	private int pulseInterval;
	private boolean pulseActive;
	private float modelTime;
	private float lastModelTime;
	private float frameElapsedTime;


		
	public GERenderer(GEModel model, Context context) {
		this.context = context;
		this.model = model;
	}
	
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		Log.d(tag, "onSurfaceCreated");
		this.gl = gl;
		defaultProperties(gl);		
		defaultLighting(gl);
		startMotion();
	}

	private void defaultProperties(GL10 gl) {
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		// Optional: disable dither to boost performance
		gl.glDisable(GL10.GL_DITHER);
	}

	private void startMotion() {
		// Motion
		startTime = System.currentTimeMillis();
		fpsStartTime = startTime;
		numframes = 0;
		pulseStartTime = startTime;
		modelTime = 0;
	}
	
	private void defaultLighting(GL10 gl) {
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
	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
		Log.v("GE", "On SurfaceChanged called");
		// Define the view frustrum
		gl.glViewport(0, 0,	width, height);

		// Change the projection matrix. Use perspective.
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		float ratio = (float) width / height;
		GLU.gluPerspective(gl, 45.0f, ratio, 0.1f, 100.0f);

		// Go back to the Modelview matrix
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
		
	public void onDrawFrame(GL10 gl) {
		clearView(gl);

		positionCamera(gl);

		automaticPan(gl);
		
		handlePulse();
		
		updateModelTime();
		
		renderModel(gl);
		
		logInfo();
	}

	private void updateModelTime() {
		modelTime = (System.currentTimeMillis() - startTime)/1000.0f;
		frameElapsedTime = modelTime - lastModelTime;
		lastModelTime = modelTime;
		model.updateTime(modelTime, frameElapsedTime);
	}

	private void clearView(GL10 gl) {
		// Clear the screen to black
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
	}

	private void renderModel(GL10 gl) {
		for (GEModelSprite asprite : model.sprites) {
		    	asprite.render(gl);
		    }
		}

	private void automaticPan(GL10 gl) {
		if (autoPan) {
			//... Set rotation angle based on the time
			long elapsed = System.currentTimeMillis() - startTime;
			gl.glRotatef(elapsed * (30f / 1000f), 0, 1, 0);
			gl.glRotatef(elapsed * (15f / 1000f), 1, 0, 0);
		}
	}

	private void positionCamera(GL10 gl) {
		// Position model so I can see it:
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		GLU.gluLookAt(gl, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, 0, 1, 0);
	}

	private void logInfo() {
		numframes++;
		long fpsElaped = System.currentTimeMillis() - fpsStartTime;
		if (fpsElaped > 5 * 1000) { // Every 5 seconds
			float fps = (numframes * 1000.0f) / fpsElaped;
			Log.d("GE", "Frames per second: " + fps + "(" + numframes + " frames in " + fpsElaped + " ms)");
			Log.d("GE", "Time Curr: " + modelTime + ", Elapsed: " + frameElapsedTime);
			fpsStartTime = System.currentTimeMillis();
			numframes = 0;
		}
	}
	
	private void handlePulse() {
		if (pulseInterval != 0 && pulseActive) {
			long pulseElapsed = System.currentTimeMillis() - pulseStartTime;
			if (pulseElapsed > pulseInterval) {
				model.pulse();
				pulseStartTime = System.currentTimeMillis();
			}
		}
	}
	
	public void setLookFrom(float eyeX, float eyeY, float eyeZ) {
		this.eyeX = eyeX;
		this.eyeY = eyeY;
		this.eyeZ = eyeZ;
	}
	
	public void setLookAt(float centerX, float centerY, float centerZ) {
		this.centerX = centerX;
		this.centerY = centerY;
		this.centerZ = centerZ;
	}
	
	public void setPulseIntervalMS(int pInterval) {
		pulseInterval = pInterval;
	}

	public void setAutoPan(boolean setting) {
		autoPan = setting;
	}

	public void pausePulse() {
		pulseActive = false;
	}
	
	public void resumePulse() {
		pulseActive = true;
	}
}
