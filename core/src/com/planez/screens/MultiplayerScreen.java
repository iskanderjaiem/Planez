package com.planez.screens;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.planez.game.Planez;
import com.planez.gameObjects.ExplosionFire;

public class MultiplayerScreen implements Screen {

	private Planez game;
	private SpriteBatch batch;
	private ExplosionFire explosion;


	public MultiplayerScreen(Planez game) {
		this.game = game;
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		// EXPLOSION
		explosion = new ExplosionFire(new Texture(Gdx.files.internal("explosion01_128.png")), 11, 10);
		}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(255, 255, 255, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		batch.begin();
			event();
			explosion.render(batch);
		batch.end();
	}

	
	public void event(){
		if (Gdx.input.isTouched()){
			explosion.animate(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
		}
		
	}
	
	@Override
	public void resize(int width, int height) {
		show();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
