package com.planez.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.planez.extra.Extras;

public class Explosion {

	private Texture explosionSheet;
	private TextureRegion[] explosionFrames;
	private Animation explosionAnimation;
	private TextureRegion currentFrame;
	private float startTime;
	private boolean animate;

	public Explosion(Texture explosionSheet, int frameRows, int frameCols) {
		this.explosionSheet = explosionSheet;

		TextureRegion[][] tmp = TextureRegion.split(explosionSheet, explosionSheet.getWidth() / frameCols,
				explosionSheet.getHeight() / frameRows);
		explosionFrames = new TextureRegion[frameCols * frameRows];

		int index = 0;
		for (int i = 0; i < frameRows; i++) {
			for (int j = 0; j < frameCols; j++) {
				explosionFrames[index++] = tmp[i][j];
			}
		}

		explosionAnimation = new Animation(0.005f, explosionFrames);
		explosionAnimation.setPlayMode(PlayMode.NORMAL);
		startTime = 0f;
		animate = false;
	}

	//render the explosion once
	public void render(SpriteBatch batch, float delta, float x, float y, float width, float height) {
		if (animate == true) {
			if (startTime == 0)
				startTime = delta;

			//frameCols * frameRows * 0.05f = 0.55f
			if ((delta - startTime) < 0.55f && animate == true) {
				currentFrame = explosionAnimation.getKeyFrame((delta - startTime), true);
				batch.draw(currentFrame, x, y);
			}
		}
	}

	public boolean isAnimate() {
		return animate;
	}

	public void setAnimate(boolean animate) {
		this.animate = animate;
	}

	public TextureRegion[] getExplosionFrames() {
		return explosionFrames;
	}

	public void setExplosionFrames(TextureRegion[] explosionFrames) {
		this.explosionFrames = explosionFrames;
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
