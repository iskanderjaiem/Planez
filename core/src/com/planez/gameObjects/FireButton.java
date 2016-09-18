package com.planez.gameObjects;

import com.planez.screens.HomeScreen;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.planez.game.Planez;

public class FireButton extends Button {

	public FireButton(Sprite btnSprite) {
		super(btnSprite);
	}

	public FireButton(Sprite btnSprite,Sprite btnTouchedSprite){
		super(btnSprite,btnTouchedSprite);
	}

	public void draw(SpriteBatch batch) {
		 if (this.isTouched()){
			 super.getBtnTouchedSprite().draw(batch); 
		 }else {
			 super.getSprite().draw(batch);
		 }
		 
	}
	

}
