package com.planez.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.planez.extra.Extras;

public class InfoBar {
	Sprite barBg;
	Sprite barGreenColor,barRedColor,barYellowColor;
	float healthPerc;
	public InfoBar(int x, int y) {
		//INFO BARS
				healthPerc = 100;
				TextureRegion lifeTexture= new TextureRegion(new Texture(Gdx.files.internal("lifeBar.png")),0,36,107,15);
				barBg = new Sprite(lifeTexture);
				barBg.setPosition(Extras.xUnite(x), Extras.yUnite(480-30-y));
				barBg.setSize(Extras.xUnite(214),Extras.yUnite(30));
				
				
				lifeTexture= new TextureRegion(new Texture(Gdx.files.internal("lifeBar.png")),0,0,104,12);
				barGreenColor = new Sprite(lifeTexture);
				barGreenColor.setPosition(Extras.xUnite(x+2), Extras.yUnite(480-24-2-y));
				//barGreenColor.setSize(Extras.xUnite(208),Extras.yUnite(24));
				

				lifeTexture= new TextureRegion(new Texture(Gdx.files.internal("lifeBar.png")),0,12,104,12);
				barYellowColor = new Sprite(lifeTexture);
				barYellowColor.setPosition(Extras.xUnite(x+2), Extras.yUnite(480-24-2-y));
				//barYellowColor.setSize(Extras.xUnite(208),Extras.yUnite(24));
				
				lifeTexture= new TextureRegion(new Texture(Gdx.files.internal("lifeBar.png")),0,24,104,12);
				barRedColor = new Sprite(lifeTexture);
				barRedColor.setPosition(Extras.xUnite(x+2), Extras.yUnite(480-24-2-y));
				//barRedColor.setSize(Extras.xUnite(208),Extras.yUnite(24));
				
				
				//Alpha Set
				barBg.setAlpha(0.5f);
				barGreenColor.setAlpha(0.5f);
				barYellowColor.setAlpha(0.5f);
				barRedColor.setAlpha(0.5f);
				
	}

	public void draw(SpriteBatch batch) {
		barBg.draw(batch);
		if (healthPerc >=75 && healthPerc <=100){
			barGreenColor.setSize(Extras.xUnite((208*healthPerc)/100),Extras.yUnite(24));
			barGreenColor.draw(batch);
		}else if (healthPerc >=30 && healthPerc <75 ){
			barYellowColor.setSize(Extras.xUnite((208*healthPerc)/100),Extras.yUnite(24));
			barYellowColor.draw(batch);
		}else if (healthPerc >=0 && healthPerc <30){
			barRedColor.setSize(Extras.xUnite((208*healthPerc)/100),Extras.yUnite(24));
			barRedColor.draw(batch);
		}
		 
	}

	public float getHealthPerc() {
		return healthPerc;
	}

	public void setHealthPerc(float healthPerc) {
		this.healthPerc = healthPerc;
	}
	
	
}
