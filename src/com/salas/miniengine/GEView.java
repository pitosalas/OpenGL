package com.salas.miniengine;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class GEView extends GLSurfaceView {
	private GERenderer renderer;
	private GEModel model;
	
	protected GEView(Context context) {
		super(context);
	}
	
	public GEView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public void setup(GEModel model, Context context) {
		this.model = model;
		renderer = new GERenderer(model, context);
		setRenderer(renderer);
//		setDebugFlags(DEBUG_CHECK_GL_ERROR | DEBUG_LOG_GL_CALLS);
	}
	
	protected void setPulseIntervalMS(int i) {
		renderer.setPulseIntervalMS(i);
	}

	protected void setLookAt(int i, int j, int k) {
		renderer.setLookAt(i, j, k);
	}

	protected void setLookFrom(int i, int j, int k) {
		renderer.setLookFrom(i, j, k);
	}
	
	protected void setLens() {
		
	}

	public void pausePulse() {
		renderer.pausePulse();
		
	}

	public void resumePulse() {
		renderer.resumePulse();
	}

}
