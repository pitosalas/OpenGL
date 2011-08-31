package com.salas.miniengine;

import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;

public class GEModel {
	
	protected ArrayList<GEModelSprite> sprites = new ArrayList<GEModelSprite>();
	protected static float[] matWhite = new float[] {1, 1, 1, 1};


	public void render(GL10 gl) {
	    for (GEModelSprite asprite : sprites) {
	    	asprite.render(gl);
	    }
	}
	
	public void pulse() {
		
	}

}
