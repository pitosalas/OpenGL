package com.salas.opengl;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class OpenGLActivity extends Activity {
	GLView view;
	private String tag="OpenGlActivity";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new GLView(this);
        setContentView(view);
        Log.d(tag, "onCreate");
    }
    
    @Override
    public void onPause() {
    	super.onPause();
    	view.onPause();
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	view.onResume();
    }
}