package com.salas.mygame;

import javax.microedition.khronos.opengles.GL10;

import com.salas.miniengine.GEModel;
import com.salas.miniengine.GEShapeCube;

public class MyGameModel extends GEModel {
	private final GEShapeCube cube = new GEShapeCube();
	private int pulseCounter;

	MyGameModel() {
		super();
		pulseCounter = 0;
	}
	
	public void render(GL10 gl) {
		int cubeN = 0;
		float[] mat;
		float ypos;
		for (int z = 0; z < 3; z++) {
			for (int x = 0; x < 10; x++) {
				if ( z == 0) {
					mat = new float[] {x/3.0f, 0, 0, 1};					
				} else if (z == 1) {
					mat = new float[] {0, x/3.0f, 0, 1};										
				} else {
					mat = new float[] {0, 0, x/3.0f, 1};					
				}
				// int yPos = cubeN == pulseCounter ? 0 : 2;
				if (x < 5) {
					ypos = x / 10.0f;
				}
				else { 
					ypos = 0.5f - ((x-5) / 10.0f);
				}
				ypos = cubeN == pulseCounter ? ypos+3 : ypos;
				cube.draw(gl, x*2, ypos * 2, z*2, mat);
				cubeN++;
			}
		}
	}
	
	public void pulse() {
		pulseCounter++;
		if (pulseCounter == 30) pulseCounter = 0;
	}
}