package com.salas.mygame;

import com.salas.miniengine.GEView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MyGameActivity extends Activity {
	GEView view;
	private String tag="OpenGlActivity";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	    GEView view = (GEView) findViewById(R.id.game_view);
		view.setRenderer(new MyGameRenderer(new MyGameModel(), this));
    }
    
    @Override
    public void onPause() {
    	super.onPause();
    	//view.onPause();
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	//view.onResume();
    }
}