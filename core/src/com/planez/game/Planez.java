package com.planez.game;

import com.planez.screens.HomeScreen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Planez extends Game {
	

	@Override
	public void create() {
		this.setScreen(new HomeScreen(this));
		
	}
}
