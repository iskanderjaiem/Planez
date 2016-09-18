package com.planez.extra;

import com.badlogic.gdx.Gdx;

public class Extras {

	//Core et desktop//
	public static final int SCALE = 2;
	public static final int WIDTH = 400 * SCALE;
	public static final int HEIGHT =WIDTH * 3/5;
	public static int POINTER_NUMBER=0;
	
	 public static float xUnite(float x)
	    {
	          return x*Gdx.graphics.getWidth()/WIDTH;
	    }
	   
	 public static float yUnite(float y)
	    {
	          return y*Gdx.graphics.getHeight()/HEIGHT;
	    }
	   static public double Distance(double x1, double y1, double x2, double y2) {
	        return Math.sqrt((y2 - y1)*(y2 - y1) + (x2 - x1)*(x2 - x1));
	    }
}
