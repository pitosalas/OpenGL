package com.salas.mygame;

import android.content.Context;
import com.salas.miniengine.GERenderer;

public class MyGameRenderer extends GERenderer {

	public MyGameRenderer(MyGameModel model, Context context) {
		super(model, context);
		setLookFrom(10, 10, 40);
		setLookAt(10, 0, 0);
		setPulseIntervalMS(1000);
	}
}
