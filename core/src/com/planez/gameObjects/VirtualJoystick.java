package com.planez.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier.Angular;
import com.badlogic.gdx.math.Vector2;

import com.planez.extra.Extras;

public class VirtualJoystick {

	public static final int UP = 1,RIGHT = 2,DOWN =3,LEFT = 4;
	private Sprite bgSprite;
	private Sprite knobSprite;
	private Vector2 translation;
	private boolean dragging =true;
	private int pointerId =0;
	private boolean pointerActive =false;
	
	public VirtualJoystick(Sprite bgSprite, Sprite knobSprite){	
		this.bgSprite = bgSprite;
		this.knobSprite = knobSprite;
		translation=new Vector2(0,0);
		bgSprite.setAlpha(0.2f);
		knobSprite.setAlpha(0.4f);
		//to ignore blur effect
		bgSprite.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		knobSprite.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		//System.out.println("Spritee =>"+knobSprite.getBoundingRectangle().width);
		bgSprite.setBounds(translation.x, translation.y, bgSprite.getBoundingRectangle().width,bgSprite.getBoundingRectangle().height);
		knobSprite.setBounds(knobSprite.getBoundingRectangle().width/2+translation.x, knobSprite.getBoundingRectangle().height/2 +translation.y, knobSprite.getBoundingRectangle().width, knobSprite.getBoundingRectangle().height);
		
	}
	
	public boolean draw(SpriteBatch batch){
		//Draw Joystick Background and Knob
		bgSprite.draw(batch);
		knobSprite.draw(batch);
		pointerActive =isDragging();
		return pointerActive;
	}
	
	
	
	public boolean isDragging(){
		if (Gdx.input.isTouched(pointerId)){
			//if inside joybg rect
			if((Gdx.input.getX(pointerId)>=bgSprite.getBoundingRectangle().x && Gdx.input.getX(pointerId)<=bgSprite.getBoundingRectangle().x+bgSprite.getBoundingRectangle().width)
				&& (Gdx.graphics.getHeight()-Gdx.input.getY(pointerId)>=bgSprite.getBoundingRectangle().y && Gdx.graphics.getHeight()-Gdx.input.getY(pointerId)<=bgSprite.getBoundingRectangle().y+bgSprite.getBoundingRectangle().height)){
				double dist = Extras.Distance(bgSprite.getBoundingRectangle().x+bgSprite.getBoundingRectangle().width/2, bgSprite.getBoundingRectangle().y+bgSprite.getBoundingRectangle().height/2, Gdx.input.getX(pointerId), Gdx.graphics.getHeight()-Gdx.input.getY(pointerId));

					if(dist <= bgSprite.getBoundingRectangle().width/2){
						//Inside the cercle
						knobSprite.setX( (Gdx.input.getX(pointerId)-knobSprite.getBoundingRectangle().width/2 )) ;
						knobSprite.setY(Gdx.graphics.getHeight()-Gdx.input.getY(pointerId)-knobSprite.getBoundingRectangle().height/2);
						dragging = true;
					}
			}
			else{//outside the rectangle
				//if drag is always active, move the knob to the same direction as the cursor's direction
				if ( dragging == true){
					float angle = (float) getAngle();
					//System.out.println("cos = "+Math.cos(Math.toRadians(angle)));
					knobSprite.setX( (float) Math.cos(Math.toRadians(angle))*(knobSprite.getBoundingRectangle().width)  + (knobSprite.getBoundingRectangle().width/2) +translation.x) ;
					//knobSprite.setY(Gdx.graphics.getHeight()-100);
					knobSprite.setY(((float) Math.sin(Math.toRadians(angle))*(knobSprite.getBoundingRectangle().width)) + (knobSprite.getBoundingRectangle().width/2)  +translation.y);
					//System.out.println(knobSprite.getBoundingRectangle().x);
				}
			}
		}else{
			//Not touched = not dragged, return knob to default position (the center of the Joystick bg)
			//*********************************************************
			knobSprite.setX(knobSprite.getBoundingRectangle().width/2+bgSprite.getBoundingRectangle().x ) ;
			knobSprite.setY(knobSprite.getBoundingRectangle().height/2+bgSprite.getBoundingRectangle().y);
			dragging = false;
		}
		return dragging;
	}
	
	public double getAngle(){
		Vector2 cursor = new Vector2(Gdx.input.getX(pointerId),Gdx.graphics.getHeight()-Gdx.input.getY(pointerId));
		Vector2 joystickBgCenter = new Vector2(bgSprite.getBoundingRectangle().x+bgSprite.getBoundingRectangle().width/2,bgSprite.getBoundingRectangle().y+bgSprite.getBoundingRectangle().height/2);
		
		float deltaX = cursor.x - joystickBgCenter.x;
		float deltaY = cursor.y - joystickBgCenter.y;
		double angle = Math.toDegrees(Math.atan2(deltaY, deltaX));
		if (angle <0)
			angle+=360;
		
		return angle ;
	}

	public Sprite getBgSprite() {
		return bgSprite;
	}

	public void setBgSprite(Sprite bgSprite) {
		this.bgSprite = bgSprite;
	}

	public Sprite getKnobSprite() {
		return knobSprite;
	}

	public void setKnobSprite(Sprite knobSprite) {
		this.knobSprite = knobSprite;
	}

	public Vector2 getTranslation() {
		return translation;
	}

	public void setTranslation(Vector2 translation) {
		this.translation.x = translation.x;
		this.translation.y = translation.y;
		bgSprite.setBounds(translation.x, +translation.y, bgSprite.getBoundingRectangle().width,bgSprite.getBoundingRectangle().height);
		knobSprite.setBounds(knobSprite.getBoundingRectangle().width/2+translation.x, knobSprite.getBoundingRectangle().height/2 +translation.y, knobSprite.getBoundingRectangle().width, knobSprite.getBoundingRectangle().height);
	
	}

	public void setDragging(boolean dragging) {
		this.dragging = dragging;
	}
	

	public int getKey(){
		double angle = getAngle();
		
		if (isDragging()){
			if (angle>=45 && angle < 135)
				return VirtualJoystick.UP;
			else if (angle>=135 && angle < 225)
				return VirtualJoystick.LEFT;
			else if (angle>=225 && angle < 315)
				return VirtualJoystick.DOWN;
			else if (  (angle>=315 && angle <= 360) || (angle>=0 && angle < 45))
				return VirtualJoystick.RIGHT;
			else return 0;
		}
		else 
			return 0;
			
	}
	//DEFAULT GETTERS AND SETTERS 

	public int getPointerId() {
		return pointerId;
	}

	public void setPointerId(int pointerId) {
		this.pointerId = pointerId;
	}

	public boolean isPointerActive() {
		return pointerActive;
	}

	
	
	
}
