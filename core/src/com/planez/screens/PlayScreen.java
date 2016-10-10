package com.planez.screens;

import com.planez.extra.Extras;
import com.planez.gameObjects.EnemyPlane;
import com.planez.gameObjects.Explosion;
import com.planez.gameObjects.FireBalls;
import com.planez.gameObjects.FireButton;
import com.planez.gameObjects.InfoBar;
import com.planez.gameObjects.PauseButton;
import com.planez.gameObjects.Plane;
import com.planez.gameObjects.ScrollingSprite;
import com.planez.gameObjects.VirtualJoystick;

import java.io.File;

import javax.xml.stream.events.EndElement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.planez.game.Planez;
import com.planez.gameLogic.DangerZone;

public class PlayScreen implements Screen {

	private Planez game;
	private SpriteBatch batch;
	private VirtualJoystick joystick;
	private Sprite bgSand0, bg;
	private ScrollingSprite bgSand1, bgSand2, bgSand3;
	private Plane plane;
	private EnemyPlane enemyPlane;
	private float time = 0;
	private Explosion explosion;
	private PauseButton pauseButton;
	private FireButton fireButton;
	private FireBalls fireBalls;
	private InfoBar lifeBar;
	boolean explosionIsAnimated = false;
	boolean pointerJoystick = false;
	boolean pointerFireBall = false;
	private DangerZone dangerZone;

	private TextureRegion currentFrame;

	public PlayScreen(Planez game) {
		this.game = game;
	}

	@Override
	public void show() {

		batch = new SpriteBatch();
		Sprite bgJoystickSprite = new Sprite(new Texture(Gdx.files.internal("joystick_background.png")));
		Sprite thumbJoystickSprite = new Sprite(new Texture(Gdx.files.internal("joystick_thumb.png")));
		bgJoystickSprite.setBounds(Extras.xUnite(bgJoystickSprite.getBoundingRectangle().getX()),
				Extras.yUnite(bgJoystickSprite.getBoundingRectangle().getY()),
				Extras.xUnite(bgJoystickSprite.getBoundingRectangle().getWidth()),
				Extras.yUnite(bgJoystickSprite.getBoundingRectangle().getHeight()));
		thumbJoystickSprite.setBounds(Extras.xUnite(thumbJoystickSprite.getBoundingRectangle().getX()),
				Extras.yUnite(thumbJoystickSprite.getBoundingRectangle().getY()),
				Extras.xUnite(thumbJoystickSprite.getBoundingRectangle().getWidth()),
				Extras.yUnite(thumbJoystickSprite.getBoundingRectangle().getHeight()));

		joystick = new VirtualJoystick(bgJoystickSprite, thumbJoystickSprite);
		joystick.setTranslation(new Vector2(Extras.xUnite(50), Extras.yUnite(50)));

		plane = new Plane(Plane.RED, joystick);
		enemyPlane = new EnemyPlane(EnemyPlane.BLUE, joystick);

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

		lifeBar = new InfoBar(10, 10);

		// Explosion
		explosion = new Explosion(new Texture(Gdx.files.internal("explosion01_128.png")), 11, 10);
		
		// Button PAUSE
		Sprite pauseButtonSprite = new Sprite(new Texture(Gdx.files.internal("pause.png")));
		pauseButtonSprite.setPosition(Extras.xUnite(710), Extras.yUnite(480 - 80));
		pauseButtonSprite.setSize(Extras.xUnite(70), Extras.yUnite(70));
		Sprite pauseButtonSpriteTouched = new Sprite(pauseButtonSprite);
		pauseButtonSprite.setAlpha(0.2f);
		pauseButton = new PauseButton(pauseButtonSprite, pauseButtonSpriteTouched);

		// Button fireBall
		Sprite fireBallButtonSprite = new Sprite(new Texture(Gdx.files.internal("fireBall1.png")));
		fireBallButtonSprite.setPosition(Extras.xUnite(640), Extras.yUnite(80));
		fireBallButtonSprite.setSize(Extras.xUnite(70), Extras.yUnite(70));
		fireBallButtonSprite.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		fireBallButtonSprite.setAlpha(0.8f);
		Sprite fireBallSpriteTouched = new Sprite(fireBallButtonSprite);
		fireBallButtonSprite.setAlpha(0.4f);
		fireButton = new FireButton(fireBallButtonSprite, fireBallSpriteTouched);
		fireBalls = new FireBalls(fireButton);
		
		dangerZone=new DangerZone();
		dangerZone.addDangerZone("playerPlane1",20,20,20,20);
		dangerZone.addDangerZone("enemey1",enemyPlane.getPlaneRect().x,enemyPlane.getPlaneRect().y,enemyPlane.getPlaneRect().width,enemyPlane.getPlaneRect().height);
	}

	@Override
	public void render(float delta) {
		time += delta;

		Gdx.gl.glClearColor(255, 255, 255, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		batch.begin();

		if (enemyPlane == null)
		enemyPlane = new EnemyPlane(EnemyPlane.BLUE, joystick);
		
			//Backgrounds render
			bg.draw(batch);
			bgSand3.animate(batch, 0.2f);
			bgSand2.animate(batch, 0.22f);
			bgSand1.animate(batch, 0.3f);
			bgSand0.draw(batch);

			//objects render
			lifeBar.draw(batch);
			pauseButton.draw(game, batch);
			fireButton.draw(batch);
			joystick.draw(batch);
			
			fireBallDraw(batch, time);
			planeDraw(batch, time);
			
			pointerIdSetter();

			Rectangle rectPlane = new Rectangle(plane.getPlaneRect().x,plane.getPlaneRect().y,plane.getPlaneRect().width,plane.getPlaneRect().height);
			
			Rectangle rectEnemey1;
			if (enemyPlane != null){
				rectEnemey1 = new Rectangle(enemyPlane.getPlaneRect().x,enemyPlane.getPlaneRect().y,enemyPlane.getPlaneRect().width,enemyPlane.getPlaneRect().height);

			//plane on plane destruction
			if (Intersector.overlaps(rectPlane, rectEnemey1)){
				
				explosion.setAnimate(true);
				enemyPlane=null;	 
				// plane is destroyed, substruct kerozene quickly
				while (lifeBar.getHealthPerc() > 0)
					lifeBar.setHealthPerc(lifeBar.getHealthPerc() - 2);
				
			}
			if (enemyPlane != null)
			if (dangerZone.hasGotFireBallShot(enemyPlane.getPlaneRect())){
				explosion.render(batch, delta, enemyPlane.getPlaneRect().x, enemyPlane.getPlaneRect().y, enemyPlane.getPlaneRect().width, enemyPlane.getPlaneRect().height);

				explosion.setAnimate(true);
				enemyPlane=null;
			}
				
			dangerZone.updateDangerZone("playerPlane1",rectPlane);
			dangerZone.updateDangerZone("enemey1",rectEnemey1);

			}

			if (enemyPlane != null)
				if (enemyPlane.getPlaneRect().getX()>0)
					enemyPlane.render(batch, delta);
				else
					enemyPlane = null;
		batch.end();
		

		
		dangerZone.draw(batch, delta);
	}

	// permit multitouch
	private void pointerIdSetter() {

		if (joystick.isPointerActive()) {
			joystick.setPointerId(0);
			fireButton.setPointerId(1);
		} else {
			fireButton.setPointerId(0);
		}
	}

	// draw the plane with the kerozene bar discharge and the explosion effect
	private void planeDraw(SpriteBatch batch, float delta) {
		if (plane.getPlaneRect().y > Extras.yUnite(20) && lifeBar.getHealthPerc() > 0)
			plane.render(batch, time);
		else if (plane.getPlaneRect().y > Extras.yUnite(40) && lifeBar.getHealthPerc() == 0) {
			explosion.setAnimate(true);
			explosion.render(batch, delta,
					plane.getPlaneRect().x - plane.getPlaneAnimation().getWidth() / 2 - Extras.xUnite(64) / 2,
					plane.getPlaneRect().y - plane.getPlaneAnimation().getHeight() / 2,
					Extras.xUnite(200) - Extras.yUnite(64) / 2, Extras.yUnite(200));

			if (lifeBar.getHealthPerc() > 0)
				lifeBar.setHealthPerc(lifeBar.getHealthPerc() - 2);
		} else {
			explosion.setAnimate(true);
			explosion.render(batch, delta,
					plane.getPlaneRect().x - plane.getPlaneRect().getWidth() / 2 - Extras.xUnite(64) / 2,
					plane.getPlaneRect().y - plane.getPlaneRect().getHeight() / 2 - Extras.yUnite(64) / 2,
					Extras.xUnite(200), Extras.yUnite(200));

			// plane is destroyed, substruct kerozene quickly
			if (lifeBar.getHealthPerc() > 0)
				lifeBar.setHealthPerc(lifeBar.getHealthPerc() - 2);
		}

		// while the plane is flighing, do kerozene substraction
		if (lifeBar.getHealthPerc() > 0)
			lifeBar.setHealthPerc(lifeBar.getHealthPerc() - 0.05f);

	}

	// ************** FIRE BALL
	private void fireBallDraw(SpriteBatch batch, float delta) {
		if (fireButton.isTouched()) {
			pointerFireBall = true;
			if (Extras.POINTER_NUMBER == 2) {
				joystick.setPointerId(0);
				fireButton.setPointerId(1);
			}

			if (fireButton.getTouchPeriod() >= 0 && fireButton.getTouchPeriod() <= 3){

				fireBalls.addBall(plane.getPlaneRect().x + plane.getPlaneRect().height / 2,
						plane.getPlaneRect().y + plane.getPlaneRect().width / 2,
						plane.getPlaneAnimation().getRotation(), 5,dangerZone);
			}

		} else {
			pointerFireBall = false;
		}
		
		fireBalls.draw(batch, time,dangerZone);
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
