package com.planez.gameObjects;

import java.util.ArrayList;

import com.planez.screens.HomeScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.planez.game.Planez;

import com.planez.extra.Extras;

public class FireBall {
	float i =1;
	private Button fireButton;
	
	public static float timer;
	public static float time;
	public static ArrayList<Ball> fireBallArray= new ArrayList<Ball>();
	
	public FireBall(Button fireButton){
		this.fireButton = fireButton;
	}
	
	public void addBall(float initX, float initY, float rotation,float speed){
		//fill the FireBall options
		TextureRegion fireBallTexture= new TextureRegion(new Texture(Gdx.files.internal("spriteSheet.png")),104,0,11,9);
		Sprite fireBallSprite = new Sprite(fireBallTexture);
		fireBallSprite.setSize(Extras.xUnite(11),Extras.yUnite(9));
		
		//Affect options to fireBall and add it to the fireball arrayList
		Ball fireBall = new Ball(fireBallSprite,rotation,initX,initY,speed);

		fireBallArray.add(fireBall);
	}
	
	public void draw(SpriteBatch batch,float delta){
		//Simulation of the balls
		
		for (int i = 0; i<fireBallArray.size();i++){
			Ball currentBall = fireBallArray.get(i);
			Sprite currentBallSprite =  fireBallArray.get(i).getSprite();

			
			if(currentBallSprite.getX()>=0 && currentBallSprite.getX()<Gdx.graphics.getWidth()
			  && currentBallSprite.getY()>Extras.yUnite(70) && currentBallSprite.getY()< Gdx.graphics.getHeight()){
				
				currentBallSprite.setX( currentBallSprite.getX() + Extras.xUnite((float)Math.cos(Math.toRadians(currentBallSprite.getRotation()))   * currentBall.speed));
				currentBallSprite.setY( currentBallSprite.getY() + Extras.yUnite((float) Math.sin(Math.toRadians(currentBallSprite.getRotation())) *currentBall.speed));
				
				
				//currentBallSprite.setX(currentBallSprite.getX()+5);
				currentBall.getSprite().setPosition((float) (currentBallSprite.getX()),currentBallSprite.getY());
				currentBall.setRotation(fireBallArray.get(i).getRotation());
				currentBallSprite.draw(batch);
			}else{
				fireBallArray.remove(i);
			}
		System.out.println(i);
	}
	}
	
	class Ball {
		private Sprite sprite;
		private float rotation;
		private float speed;
		private Vector2 initPos;
		
		public Ball(Sprite sprite,float rotation,float initPosX,float initPosY,float speed) {
			this.sprite = sprite;
			this.speed = speed;
			this.sprite.setPosition(initPosX,initPosY);
			this.sprite.setRotation(rotation);
			initPos= new Vector2(initPosX,initPosY);
			this.rotation = rotation;
		}
		public Sprite getSprite() {
			return sprite;
		}
		public void setSprite(Sprite sprite) {
			this.sprite = sprite;
		}
		public float getRotation() {
			return rotation;
		}
		public void setRotation(float rotation) {
			this.rotation = rotation;
		}
		public Vector2 getInitPos() {
			return initPos;
		}
		public void setInitPos(Vector2 initPos) {
			this.initPos = initPos;
		}
		
		
		
	}
	
}
