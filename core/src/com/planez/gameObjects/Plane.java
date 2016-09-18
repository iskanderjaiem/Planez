package com.planez.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.planez.extra.Extras;

public class Plane {

	public static final byte RED = 10;
	public static final byte BLUE = 11;
	public static final byte YELLOW = 12;

	private VirtualJoystick virtualJoystick;
	private Animation planeSprites;
	private Sprite planeAnimation;
	private Vector2 planePos;

	public Plane(byte planeColor, VirtualJoystick virtualJoystick) {
		this.virtualJoystick = virtualJoystick;

		if (planeColor == Plane.RED) {

			Sprite frame1 = new Sprite(new Texture(Gdx.files.internal("planeRed1.png")));
			Sprite frame2 = new Sprite(new Texture(Gdx.files.internal("planeRed2.png")));
			Sprite frame3 = new Sprite(new Texture(Gdx.files.internal("planeRed3.png")));
			Sprite[] frames = { frame1, frame2, frame3 };
			planeSprites = new Animation(0.1f, frames);
			planePos = new Vector2(Extras.xUnite((float) 20), Extras.yUnite((float) 70));
			TextureRegion tr = planeSprites.getKeyFrame(0.1f);
			planeAnimation = new Sprite(tr);
		}
		planeSprites.setPlayMode(Animation.PlayMode.LOOP);

	}

	public void associate(VirtualJoystick virtualJoystick) {
		this.virtualJoystick = virtualJoystick;
	}

	public void render(SpriteBatch batch, float time) {
		planeAnimation.setTexture(planeSprites.getKeyFrame(time).getTexture());
		planeAnimation.setSize(Extras.xUnite(37), Extras.yUnite(37));
		planeAnimation.setOriginCenter();
		planeAnimation.draw(batch);

		if ((planeAnimation.getBoundingRectangle().x + planeAnimation.getBoundingRectangle().width < Gdx.graphics
				.getWidth())
				&& (planeAnimation.getBoundingRectangle().x + planeAnimation.getBoundingRectangle().width >= 0)
				&& !(planeAnimation.getBoundingRectangle().y
						+ planeAnimation.getBoundingRectangle().height >= Gdx.graphics.getHeight())) {
			// if plane is touching the Right border
			move(3.5f);
			if (virtualJoystick.isDragging()) {
				planeAnimation.setRotation((float) virtualJoystick.getAngle());
				planeAnimation.draw(batch);
			} else {
				planeAnimation.draw(batch);
			}
		} else if (planeAnimation.getBoundingRectangle().x + planeAnimation.getBoundingRectangle().width < 0) {
			// if plane is touching the Left border
			planePos.x = Gdx.graphics.getWidth() - planeAnimation.getBoundingRectangle().getWidth()
					- Extras.xUnite((float) 1);
			planeAnimation.setX(planePos.x);
			planeAnimation.setX(Gdx.graphics.getWidth() - planeAnimation.getBoundingRectangle().getWidth() - 1);
		} else if (planeAnimation.getBoundingRectangle().y+ planeAnimation.getBoundingRectangle().height >= Gdx.graphics.getHeight()) {
			// if plane is touching the TOP of the border
			planePos.y = planeAnimation.getBoundingRectangle().y - Extras.xUnite((float) 5);
			planeAnimation.setY(planePos.y);
		} else {
			planePos.x = 0;
			planeAnimation.setX(planePos.x);
		}
	}

	private void move(float sp) {
		planePos.x += Extras.xUnite((float) Math.cos(Math.toRadians(planeAnimation.getRotation())) * sp);
		planePos.y += Extras.yUnite((float) Math.sin(Math.toRadians(planeAnimation.getRotation())) * sp);
		planeAnimation.setX(planePos.x);
		planeAnimation.setY(planePos.y);

	}

	/*
	 * 
	 * int key = 0; if (virtualJoystick != null){ key =
	 * virtualJoystick.getKey();
	 * 
	 * switch (key) { case VirtualJoystick.UP: System.out.println("UP"); break;
	 * case VirtualJoystick.RIGHT: System.out.println("RIGHT");
	 * 
	 * break; case VirtualJoystick.DOWN: System.out.println("DOWN");
	 * 
	 * break; case VirtualJoystick.LEFT: System.out.println("LEFT");
	 * 
	 * break;
	 * 
	 * default: break; } }
	 */

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

	public Vector2 getPlanePos() {
		return planePos;
	}

	public void setPlanePos(Vector2 planePos) {
		this.planePos = planePos;
	}

}
