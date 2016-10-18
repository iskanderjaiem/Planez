package com.planez.screens;

import java.awt.Color;
import java.net.URISyntaxException;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import com.planez.extra.Extras;
import com.planez.game.Planez;
import com.planez.gameObjects.EnemyPlane;
import com.planez.gameObjects.ExplosionFire;
import com.planez.gameObjects.PauseButton;
import com.planez.gameObjects.Plane;
import com.planez.gameObjects.ScrollingSprite;

public class MultiplayerScreen implements Screen {

	private Planez game;
	private SpriteBatch batch;
	private ExplosionFire explosion;
	private Sprite bgSand0, bg;
	private ScrollingSprite bgSand1, bgSand2, bgSand3;
	private float time = 0;
	private PauseButton pauseButton;
	private Plane plane;
	private HashMap<String, Plane> enemyPlanes;
	private Socket socket;

	public MultiplayerScreen(Planez game) {
		this.game = game;
		connectSocket();
		configSocketEvents();
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		backgroundsInit();

		plane = new Plane(Plane.RED,20,70);
		enemyPlanes = new HashMap<String, Plane>();
		// EXPLOSION
		explosion = new ExplosionFire(new Texture(Gdx.files.internal("explosion01_128.png")), 11, 10);
		
		// Button PAUSE
		Sprite pauseButtonSprite = new Sprite(new Texture(Gdx.files.internal("pause.png")));
		Sprite pauseButtonSpriteTouched = new Sprite(pauseButtonSprite);
		pauseButtonSprite.setAlpha(0.2f);
		Rectangle rectBtn= new Rectangle(Extras.xUnite(710), Extras.yUnite(480 - 80),Extras.xUnite(70), Extras.yUnite(70));
		pauseButton = new PauseButton(pauseButtonSprite, pauseButtonSprite, rectBtn);

		}
	
	@Override
	public void render(float delta) {
		time+=delta;
		Gdx.gl.glClearColor(255, 255, 255, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		batch.begin();
			event();
			explosion.render(batch);
			//Backgrounds render
			//backgroundsRender();
			//objects render
			pauseButton.draw(game, batch);

			if (plane != null)
				plane.draw(batch, time);
			for (HashMap.Entry<String,Plane>entry:enemyPlanes.entrySet()){
				entry.getValue().draw(batch, time);
			}
		batch.end();
	}

	public void event(){
		if (Gdx.input.isTouched()){
			explosion.animate(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
		}
		
	}
	public void connectSocket() {
		try {
			socket = IO.socket ("http://localhost:5000");
			socket.connect();
		} catch (URISyntaxException e) {
			System.out.println(e);
		}
	}
	public void configSocketEvents(){
		socket.on(Socket.EVENT_CONNECT,new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				Gdx.app.log("SocketIO","Connected");
			}
		}).on("socketID", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONObject data = (JSONObject) args[0];
				String id;
				try {
					id = data.getString("id");
					Gdx.app.log("SocketIO", "My ID:"+id);	
					Gdx.graphics.getBackBufferHeight();
					enemyPlanes.put("dd", new Plane(Plane.RED,70,70));	
				} catch (JSONException e) {
					Gdx.app.log("ERROR", "error getting ID");
				}
			}
		}).on("newPlayer", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONObject data = (JSONObject) args[0];
				String id;
				try {
					id = data.getString("id");
					Gdx.app.log("SocketIO", "New player Connected "+id);
				} catch (JSONException e) {
					Gdx.app.log("ERROR", "error getting new player ID");
				}
			}
		});
	}
	
	public void backgroundsRender(){
		bg.draw(batch);
		bgSand3.animate(batch, 0.2f);
		bgSand2.animate(batch, 0.22f);
		bgSand1.animate(batch, 0.3f);
		bgSand0.draw(batch);
	}
	
	public void backgroundsInit(){
		bg = new Sprite(new Texture(Gdx.files.internal("bg1.png")));
		bg.setSize(Extras.xUnite(1024), Extras.yUnite(512));

		bgSand0 = new Sprite(new Texture(Gdx.files.internal("bgSand0.png")));
		bgSand0.setSize(Extras.xUnite(bgSand0.getWidth()), Extras.yUnite(bgSand0.getHeight()));
		bgSand0.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);

		bgSand1 = new ScrollingSprite(new Texture(Gdx.files.internal("bgSand1.png")));
		bgSand1.setSize(Extras.xUnite(bgSand1.getWidth() / 2), Extras.yUnite(bgSand1.getHeight() / 2));
		bgSand1.setPosition(0, Extras.yUnite(70));
		bgSand1.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);

		bgSand2 = new ScrollingSprite(new Texture(Gdx.files.internal("bgSand2.png")));
		bgSand2.setSize(Extras.xUnite(bgSand2.getWidth() / 2), Extras.yUnite(bgSand2.getHeight() / 2));
		bgSand2.setPosition(0, Extras.yUnite(70));
		bgSand2.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);

		bgSand3 = new ScrollingSprite(new Texture(Gdx.files.internal("bgSand3.png")));
		bgSand3.setSize(Extras.xUnite(bgSand3.getWidth() / 2), Extras.yUnite(bgSand3.getHeight() / 2));
		bgSand3.setPosition(0, Extras.yUnite(70));
		bgSand3.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
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
