package com.planez.gameObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.planez.extra.Extras;
import com.planez.game.Planez;
import com.planez.screens.HomeScreen;

public class PauseButton extends Button {

	public PauseButton(Sprite btnSprite) {
		super(btnSprite);
	}
	
	public PauseButton(Sprite btnSprite,Sprite btnSpriteTouched,Rectangle rectBtn){
		super(btnSprite, btnSprite,rectBtn);
	}
	
	public void draw(Planez game, SpriteBatch batch) {
		 if (this.isTouched()){
			 super.getBtnTouchedSprite().draw(batch); 
			 game.setScreen(new HomeScreen(game));
		 }else {
			 super.getSprite().draw(batch);
		 }
		 
	}

}
