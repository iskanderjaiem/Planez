package com.planez.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.planez.extra.Extras;


public class ScrollingSprite extends Sprite{
	private double spritePosition;
	//Constructors
	public ScrollingSprite(){
		super();
		spritePosition=0;
	}

	public ScrollingSprite(Texture texture){
		super(texture);
		spritePosition=0;
	}

	public ScrollingSprite(Sprite sprite){
		super(sprite);
		spritePosition=0;
	}
	
	//Methods
	
	public void animate(Batch batch, double speedAnimation){
		
		if ((this.getWidth()-Gdx.graphics.getWidth()) > -spritePosition)
			spritePosition-= speedAnimation;
		else 
			spritePosition = 0;
		//System.out.println("spritePosition = "+spritePosition);
		/*
			System.out.println("spritePosition = "+spritePosition);
			System.out.println("X = "+this.getBoundingRectangle().getX());
			System.out.println("getWidth= "+this.getWidth()+"   getScreenW= "+Gdx.graphics.getWidth());*/
		this.setX((int)spritePosition);
		this.draw(batch);
	}
}