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

public class Plane {

	public static final byte RED = 10;
	public static final byte BLUE = 11;
	public static final byte YELLOW = 12;
	private VirtualJoystick virtualJoystick;
	private Animation planeSprites;
	private Sprite planeAnimation;
	private Rectangle planeRect;

	//Constructor
	public Plane(byte planeColor) {
		if (planeColor == Plane.RED) {
			Sprite frame1 = new Sprite(new Texture(Gdx.files.internal("planeRed1.png")));
			Sprite frame2 = new Sprite(new Texture(Gdx.files.internal("planeRed2.png")));
			Sprite frame3 = new Sprite(new Texture(Gdx.files.internal("planeRed3.png")));
			Sprite[] frames = { frame1, frame2, frame3 };
			planeSprites = new Animation(0.1f, frames);
		}
		planeRect= new Rectangle(Extras.xUnite((float) 20), Extras.yUnite((float) 70), Extras.xUnite((float) 37), Extras.yUnite((float) 37));
		TextureRegion tr = planeSprites.getKeyFrame(0.1f);
		planeAnimation = new Sprite(tr);
		planeSprites.setPlayMode(Animation.PlayMode.LOOP);
	}
	
	//overload constructor
	public Plane(byte planeColor, VirtualJoystick virtualJoystick) {
		this(planeColor);
		this.virtualJoystick = virtualJoystick;
		planeRect= new Rectangle(Extras.xUnite((float) 20), Extras.yUnite((float) 70), Extras.xUnite((float) 37), Extras.yUnite((float) 37));
	}
	public Plane(byte planeColor, VirtualJoystick virtualJoystick, int x, int y) {
		this(planeColor);
		this.virtualJoystick = virtualJoystick;
		planeRect= new Rectangle(Extras.xUnite((float) x), Extras.yUnite((float) y), Extras.xUnite((float) 37), Extras.yUnite((float) 37));
	}
	public Plane(byte planeColor, int x, int y) {
		this(planeColor);
		planeRect= new Rectangle(Extras.xUnite((float) x), Extras.yUnite((float) y), Extras.xUnite((float) 37), Extras.yUnite(37));
	}

	public void associate(VirtualJoystick virtualJoystick) {
		this.virtualJoystick = virtualJoystick;
	}

	public void init(SpriteBatch batch, float time){
		planeAnimation.setTexture(planeSprites.getKeyFrame(time).getTexture());
		planeAnimation.setBounds(planeRect.x,planeRect.y,planeRect.width,planeRect.height);
		planeAnimation.setOriginCenter();
		planeAnimation.draw(batch);
	}
	
	public void render(SpriteBatch batch, float time) {
		planeAnimation.setTexture(planeSprites.getKeyFrame(time).getTexture());
		planeAnimation.setSize(Extras.xUnite(37), Extras.yUnite(37));
		planeAnimation.setOriginCenter();
		if ((planeRect.x + planeRect.width < Gdx.graphics
				.getWidth())
				&& (planeRect.x + planeRect.width >= 0)
				&& !(planeRect.y
						+ planeRect.height >= Gdx.graphics.getHeight())) {
			// if plane is touching the Right border
			move(3.5f);
			if (virtualJoystick!=null && virtualJoystick.isDragging()) {
				planeAnimation.setRotation((float) virtualJoystick.getAngle());
				planeAnimation.draw(batch);
			} else {
				planeAnimation.draw(batch);
			}
		} else if (planeRect.x + planeRect.width < 0) {
			// if plane is touching the Left border
			planeRect.x = Gdx.graphics.getWidth() - planeRect.width
					- Extras.xUnite((float) 1);
			planeAnimation.setX(planeRect.x);
			planeAnimation.setX(Gdx.graphics.getWidth() - planeRect.width - 1);
		} else if (planeRect.y+ planeRect.height >= Gdx.graphics.getHeight()) {
			// if plane is touching the TOP of the border
			planeRect.y = planeRect.y - Extras.xUnite((float) 5);
			planeAnimation.setY(planeRect.y);
		} else {
			planeRect.x = 0;
			planeAnimation.setX(planeRect.x);
		}
	}
	
	public void draw(SpriteBatch batch, float time) {
		planeAnimation.setTexture(planeSprites.getKeyFrame(time).getTexture());
		planeAnimation.setSize(Extras.xUnite(37), Extras.yUnite(37));
		planeAnimation.setOriginCenter();
		planeAnimation.setPosition(planeRect.x, planeRect.y);
		planeAnimation.draw(batch);
	}

	private void move(float sp) {
		planeRect.x += Extras.xUnite((float) Math.cos(Math.toRadians(planeAnimation.getRotation())) * sp);
		planeRect.y += Extras.yUnite((float) Math.sin(Math.toRadians(planeAnimation.getRotation())) * sp);
		planeAnimation.setPosition(planeRect.x, planeRect.y);
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

	public Rectangle getPlaneRect() {
		return planeRect;
	}

	public void setPlaneRect(Rectangle planeRect) {
		this.planeRect = planeRect;
	}

}
