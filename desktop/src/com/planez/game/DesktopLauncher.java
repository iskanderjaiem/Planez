package com.planez.game;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.planez.game.Planez;

public class DesktopLauncher {
	
	//Core et desktop//
		public static final int SCALE = 2;
		public static final int WIDTH = 200 * SCALE;
		public static final int HEIGHT =WIDTH * 3/5;
		
		
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = WIDTH;
		config.height = HEIGHT;
		config.title="Planez";
		new LwjglApplication(new Planez(), config);
	}
}
