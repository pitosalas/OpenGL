package com.salas.mygame;

import android.content.Context;
import android.util.AttributeSet;

import com.salas.miniengine.GEModel;
import com.salas.miniengine.GEView;

public class MyGameView extends GEView {

	MyGameView(Context context) {
		super(context);
	}
	
	public MyGameView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public void setup(GEModel model, Context context) {
		super.setup(model, context);
		setLookFrom(10, 10, 40);
		setLookAt(10, 0, 0);
		setLens();
		setPulseIntervalMS(1000);
	}

	public void resumePulse() {
		super.resumePulse();
	}

	public void pausePulse() {
		super.pausePulse();
		
	}
}
