package com.salas.miniengine;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class GEView extends GLSurfaceView {
	private GERenderer renderer;
	
	GEView(Context context) {
		super(context);
		initGLView(context);
	}
	
	public GEView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initGLView(context);
	}
	
	void initGLView(Context context) {
//		setDebugFlags(DEBUG_CHECK_GL_ERROR | DEBUG_LOG_GL_CALLS);
	}

}
