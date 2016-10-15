package com.planez.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Button {

	private Sprite sprite;
	private Sprite btnTouchedSprite;
	private int pointerId =0;//to be able to use multiple touch (with button ID)
	private boolean pointerActive =false;//current button state
	private int touchPeriod;
	public Button(Sprite btnSprite){
		this.sprite = btnSprite;
	}

	public Button(Sprite btnSprite,Sprite btnTouchedSprite){
		this.sprite = btnSprite;
		this.btnTouchedSprite = btnTouchedSprite;
	}
	
	public Button(Sprite btnSprite,Sprite btnTouchedSprite,Rectangle rectBtn){
		this(btnSprite,btnTouchedSprite);
		btnSprite.setPosition(rectBtn.x,rectBtn.y);
		btnSprite.setSize(rectBtn.width,rectBtn.height);
	}

	public boolean isTouched(){
		if (Gdx.input.isTouched(pointerId))
			if ((Gdx.input.getX(pointerId)>sprite.getX()) && (Gdx.input.getX(pointerId) < sprite.getX()+sprite.getWidth()) &&
				(Gdx.graphics.getHeight()-Gdx.input.getY(pointerId)>sprite.getY()) && (Gdx.graphics.getHeight()-Gdx.input.getY(pointerId)<sprite.getY()+sprite.getHeight()))
				{
					touchPeriod++;
				    pointerActive = true;
				
				}
			else 
			{
				pointerActive = false;
				touchPeriod=0;
			}
		else {
			touchPeriod=0;
			pointerActive = false;
			}
		return pointerActive;
	}



	//DEFAULT GETTERS AND SETTERS 

	public int getPointerId() {
		return pointerId;
	}

	public void setPointerId(int pointerId) {
		this.pointerId = pointerId;
	}
	
	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public Sprite getBtnTouchedSprite() {
		return btnTouchedSprite;
	}

	public void setBtnTouchedSprite(Sprite btnTouchedSprite) {
		this.btnTouchedSprite = btnTouchedSprite;
	}

	public int getTouchPeriod() {
		return touchPeriod;
	}

	public void setTouchPeriod(int touchPeriod) {
		this.touchPeriod = touchPeriod;
	}

	
}
