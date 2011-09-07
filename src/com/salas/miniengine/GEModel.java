package com.salas.miniengine;

import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;

public class GEModel {
	float lastTime = 0;
	
	public ArrayList<GEModelSprite> sprites = new ArrayList<GEModelSprite>();
	protected static float[] matWhite = new float[] {1, 1, 1, 1};

	public void pulse() {
		
	}

	public void updateTime(float modelTime, float frameElapsedTime) {
		for (GEModelSprite asprite : sprites) {
	    	asprite.updateTime(modelTime, frameElapsedTime);
	    }
	}
}
