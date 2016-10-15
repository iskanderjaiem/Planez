package com.planez.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
public class ExplosionFire {

	private Animation explosionAnimation;
	private TextureRegion currentFrame;
	private TextureRegion[] explosionFrames;
	private boolean animate;
	private float time;
	private Rectangle explosionRect;
	public static float BLINK_TIME = 0.005f;
	
	//Constructor
	public ExplosionFire(Texture explosionSheet, int frameRows, int frameCols) {
		TextureRegion[][] tmp = TextureRegion.split(explosionSheet, explosionSheet.getWidth() / frameCols,explosionSheet.getHeight() / frameRows);
		explosionFrames = new TextureRegion[frameCols * frameRows];
		int index = 0;
		for (int i = 0; i < frameRows; i++) {
			for (int j = 0; j < frameCols; j++) {
				explosionFrames[index++] = tmp[i][j];
			}
		}
		explosionAnimation = new Animation(BLINK_TIME, explosionFrames);
		explosionAnimation.setPlayMode(PlayMode.NORMAL);
		animate = false;
		explosionRect=new Rectangle(0,0,explosionFrames[0].getRegionWidth(),explosionFrames[0].getRegionHeight());
	}
	
	//overload constructor
	public ExplosionFire(Texture explosionSheet, int frameRows, int frameCols,Rectangle explosionRect) {
		this(explosionSheet, frameRows, frameCols);
		this.explosionRect=explosionRect;
	}
	public ExplosionFire(Texture explosionSheet, int frameRows, int frameCols, float width, float height) {
		this(explosionSheet, frameRows, frameCols);
		this.explosionRect=new Rectangle(0, 0, width, height);
	}

	//Render the explosion once
	public void render(SpriteBatch batch) {
		if (animate)
			if (!explosionAnimation.isAnimationFinished(time)){
				time+= Gdx.graphics.getDeltaTime();  
				currentFrame = explosionAnimation.getKeyFrame(time, false);
				batch.draw(currentFrame, explosionRect.x, explosionRect.y);
			}else{
				animate=false;
				time=0;
			}
	}

	
	public Rectangle getExplosionRect() {
		return explosionRect;
	}

	public void setExplosionRect(Rectangle explosionRect) {
		this.explosionRect = explosionRect;
	}

	public boolean isAnimate() {
		return animate;
	}

	public void animate(float x, float y) {
		explosionRect.setCenter(x, y);
		this.animate = true;
	}
	public void animate() {
		this.animate = true;
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
