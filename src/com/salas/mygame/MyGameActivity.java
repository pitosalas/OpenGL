package com.salas.mygame;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MyGameActivity extends Activity {
	private MyGameView view;
	private String tag="ME";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	    view = (MyGameView) findViewById(R.id.game_view);
		view.setup(new MyGameModel(), this);

		// Catch and handle the toggle button
		final ToggleButton togglebutton = (ToggleButton) findViewById(R.id.run_pause);
		togglebutton.setOnClickListener(new OnClickListener() {
		    public void onClick(View v) {
		        // Perform action on clicks
		        if (togglebutton.isChecked()) {
		            Toast.makeText(MyGameActivity.this, "Running", Toast.LENGTH_SHORT).show();
		            view.resumePulse();
		        } else {
		            Toast.makeText(MyGameActivity.this, "Paused", Toast.LENGTH_SHORT).show();
		            view.pausePulse();
		        }
		    }

		});		
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