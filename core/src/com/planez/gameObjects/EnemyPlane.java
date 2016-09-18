package com.planez.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.planez.extra.Extras;

public class EnemyPlane {

	public static final byte RED = 10;
	public static final byte BLUE = 11;
	public static final byte YELLOW = 12;

	private VirtualJoystick virtualJoystick;
	private Animation planeSprites;
	private Sprite planeAnimation;
	private Rectangle planeRect;
	private float startTime;
	private boolean animate;

	public EnemyPlane(byte planeColor, VirtualJoystick virtualJoystick) {
		this.virtualJoystick = virtualJoystick;

		if (planeColor == EnemyPlane.BLUE) {

			Sprite frame1 = new Sprite(new Texture(Gdx.files.internal("planeBlue2.png")));
			Sprite frame2 = new Sprite(new Texture(Gdx.files.internal("planeBlue2.png")));
			Sprite frame3 = new Sprite(new Texture(Gdx.files.internal("planeBlue2.png")));
			
			//FLIP from right to left
			frame1.flip(true, false);
			frame2.flip(true, false);
			frame3.flip(true, false);
			
			Sprite[] frames = { frame1, frame2, frame3 };
			planeSprites = new Animation(0.1f, frames);	
			planeRect= new Rectangle( Gdx.graphics.getWidth()-Extras.xUnite((float) 80), Gdx.graphics.getHeight()-Extras.yUnite((float) 70), Extras.xUnite((float) 37), Extras.yUnite((float) 37));
			TextureRegion tr = planeSprites.getKeyFrame(0.1f);
			planeAnimation = new Sprite(tr);
		}
		planeSprites.setPlayMode(Animation.PlayMode.LOOP);
		startTime = 0f;
		animate = true;

	}

	public void associate(VirtualJoystick virtualJoystick) {
		this.virtualJoystick = virtualJoystick;
	}

	public void render(SpriteBatch batch, float delta) {
		
		if (animate == true) {
			if (startTime == 0)
				startTime = delta;

			//frameCols * frameRows * 0.05f = 0.55f
			if ((delta - startTime) < 80 && animate == true) {

			}
		}

		planeAnimation.setTexture(planeSprites.getKeyFrame(delta).getTexture());
		planeAnimation.setSize(Extras.xUnite(37), Extras.yUnite(37));
		planeAnimation.setOriginCenter();
		planeAnimation.draw(batch);
		moveToLeft(1.5f);
	
	}

	private void move(float sp) {
		planeRect.x += Extras.xUnite((float) Math.cos(Math.toRadians(planeAnimation.getRotation())) * sp);
		planeRect.y += Extras.yUnite((float) Math.sin(Math.toRadians(planeAnimation.getRotation())) * sp);
		planeAnimation.setX(planeRect.x);
		planeAnimation.setY(planeRect.y);
	}
	
	private void moveToLeft(float sp) {
		planeRect.x -= Extras.xUnite((float) Math.cos(Math.toRadians(planeAnimation.getRotation())) * sp);
		planeRect.y += Extras.yUnite((float) Math.sin(Math.toRadians(planeAnimation.getRotation())) * sp);
		planeAnimation.setX(planeRect.x);
		planeAnimation.setY(planeRect.y);
	}

	public VirtualJoystick getVirtualJoystick() {
		return virtualJoystick;
	}

	public void setVirtualJoystick(VirtualJoystick virtualJoystick) {
		this.virtualJoystick = virtualJoystick;
	}

	public Sprite getPlaneAnimation() {
		return planeAnimation;
	}

	public void setPlaneAnimation(Sprite planeAnimation) {
		this.planeAnimation = planeAnimation;
	}

	public Rectangle getPlaneRect() {
		return planeRect;
	}

	public void setPlaneRect(Rectangle planeRect) {
		this.planeRect = planeRect;
	}


}
