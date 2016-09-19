package com.planez.gameObjects;

import java.util.ArrayList;

import com.planez.screens.HomeScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.planez.game.Planez;
import com.planez.gameLogic.DangerZone;
import com.planez.extra.Extras;

public class FireBalls {
	float i = 1;
	private Button fireButton;
	private float width, height;
	private int idBall;

	public static float timer;
	public static float time;
	public static ArrayList<Ball> fireBallArray = new ArrayList<Ball>();
	public static int NB_FIRE_BALLS = 0;

	public FireBalls(Button fireButton) {
		this.fireButton = fireButton;
	}

	public void addBall(float initX, float initY, float rotation, float speed, DangerZone dangerZone) {
		// fill the FireBall options
		TextureRegion fireBallTexture = new TextureRegion(new Texture(Gdx.files.internal("spriteSheet.png")), 104, 0,
				11, 9);
		width = 11;
		height = 9;
		Sprite fireBallSprite = new Sprite(fireBallTexture);
		fireBallSprite.setSize(Extras.xUnite(width), Extras.yUnite(height));

		// Affect options to fireBall and add it to the fireball arrayList
		Ball fireBall = new Ball(fireBallSprite, rotation, initX, initY, speed);

		fireBallArray.add(fireBall);
		dangerZone.addDangerZone("fireBall" + fireBall.getIdBall(), initX, initY, width, height);

		NB_FIRE_BALLS++;
	}

	public void draw(SpriteBatch batch, float delta, DangerZone dangerZone) {
		// Simulation of the balls

		for (int i = 0; i < fireBallArray.size(); i++) {
			Ball currentBall = fireBallArray.get(i);
			Sprite currentBallSprite = fireBallArray.get(i).getSprite();

			if (currentBallSprite.getX() >= 0 && currentBallSprite.getX() < Gdx.graphics.getWidth()
					&& currentBallSprite.getY() > Extras.yUnite(70)
					&& currentBallSprite.getY() < Gdx.graphics.getHeight()) {

				currentBallSprite.setX(currentBallSprite.getX() + Extras
						.xUnite((float) Math.cos(Math.toRadians(currentBallSprite.getRotation())) * currentBall.speed));
				currentBallSprite.setY(currentBallSprite.getY() + Extras
						.yUnite((float) Math.sin(Math.toRadians(currentBallSprite.getRotation())) * currentBall.speed));

				// currentBallSprite.setX(currentBallSprite.getX()+5);
				currentBall.getSprite().setPosition((float) (currentBallSprite.getX()), currentBallSprite.getY());
				currentBall.setRotation(fireBallArray.get(i).getRotation());
				currentBallSprite.draw(batch);
				dangerZone.updateDangerZone("fireBall" + fireBallArray.get(i).getIdBall(),
						new Rectangle(currentBallSprite.getX(), currentBallSprite.getY(), width, height));

			} else {
				dangerZone.removeDangerZone("fireBall" + fireBallArray.get(i).getIdBall(),
						new Rectangle(currentBallSprite.getX(), currentBallSprite.getY(), width, height));
				fireBallArray.remove(i);
			}

			System.out.println(i);
		}
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	class Ball {
		private Sprite sprite;
		private float rotation;
		private float speed;
		private Vector2 initPos;
		private int idBall;

		public Ball(Sprite sprite, float rotation, float initPosX, float initPosY, float speed) {
			idBall = FireBalls.NB_FIRE_BALLS;
			this.sprite = sprite;
			this.speed = speed;
			this.sprite.setPosition(initPosX, initPosY);
			this.sprite.setRotation(rotation);
			initPos = new Vector2(initPosX, initPosY);
			this.rotation = rotation;
		}

		public Sprite getSprite() {
			return sprite;
		}

		public void setSprite(Sprite sprite) {
			this.sprite = sprite;
		}

		public float getRotation() {
			return rotation;
		}

		public void setRotation(float rotation) {
			this.rotation = rotation;
		}

		public Vector2 getInitPos() {
			return initPos;
		}

		public void setInitPos(Vector2 initPos) {
			this.initPos = initPos;
		}

		public int getIdBall() {
			return idBall;
		}

		public void setIdBall(int idBall) {
			this.idBall = idBall;
		}

	}

}
