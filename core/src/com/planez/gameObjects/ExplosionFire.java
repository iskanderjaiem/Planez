package com.planez.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
public class ExplosionFire {

	private Animation explosionAnimation;
	private TextureRegion currentFrame;
	private float timeElapsedExplosion;
	private boolean animate;
	public static float BLINK_TIME = 0.005f;

	public ExplosionFire(Texture explosionSheet, int frameRows, int frameCols) {
		TextureRegion[][] tmp = TextureRegion.split(explosionSheet, explosionSheet.getWidth() / frameCols,explosionSheet.getHeight() / frameRows);
		TextureRegion[] explosionFrames = new TextureRegion[frameCols * frameRows];
		int index = 0;
		for (int i = 0; i < frameRows; i++) {
			for (int j = 0; j < frameCols; j++) {
				explosionFrames[index++] = tmp[i][j];
			}
		}
		explosionAnimation = new Animation(BLINK_TIME, explosionFrames);
		explosionAnimation.setPlayMode(PlayMode.NORMAL);
		animate = false;
		timeElapsedExplosion=0;
	}

	//render the explosion once
	public void render(SpriteBatch batch, float delta, float timeElapsedBatch, float x, float y) {
		
	}

	public boolean isAnimate() {
		return animate;
	}

	public void setAnimate(boolean animate) {
		this.animate = animate;
	}

	public TextureRegion getCurrentFrame() {
		return currentFrame;
	}

	public void setCurrentFrame(TextureRegion currentFrame) {
		this.currentFrame = currentFrame;
	}

	public Animation getExplosionAnimation() {
		return explosionAnimation;
	}

	public void setExplosionAnimation(Animation explosionAnimation) {
		this.explosionAnimation = explosionAnimation;
	}

}
