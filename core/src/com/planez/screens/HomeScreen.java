package com.planez.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.planez.game.Planez;

import com.planez.extra.Extras;
import com.planez.gameObjects.Button;
import com.planez.gameObjects.Explosion;
import com.planez.gameObjects.ScrollingSprite;

public class HomeScreen implements Screen{

public final static float SCREEN_WIDTH = Gdx.graphics.getWidth();
public final static float SCREEN_HEIGHT= Gdx.graphics.getHeight();
	private Planez game;
	private SpriteBatch batch;
	private ScrollingSprite bg1S,bg2S,bg3S ;
	private Sprite gameTitleS;
	
	private float time;
	private Sprite panelBg,singlePlayerButtonSprite,multiplayerButtonSprite,scoreBoardButtonSprite,aboutButtonSprite;
	private Button singlePlayerButton,multiplayerButton,scoreBoardButton,aboutButton;
	
	public HomeScreen(Planez game){
		this.game = game;
	}


	//****************************   SHOW  *******************************
	@Override
	public void show() {

		//Sprites
		batch = new SpriteBatch();
		bg1S = new ScrollingSprite(new Texture(Gdx.files.internal("bg1.png")));
		bg2S = new ScrollingSprite(new Texture(Gdx.files.internal("bg2.png")));
		bg3S = new ScrollingSprite(new Texture(Gdx.files.internal("bg3.png")));

		bg1S.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		bg1S.setPosition(Extras.xUnite(0),Extras.yUnite(0));
		bg2S.setSize(Extras.xUnite(bg2S.getWidth()), Extras.yUnite(bg2S.getHeight()));
		bg3S.setSize(Extras.xUnite(bg3S.getWidth()), Extras.yUnite(bg3S.getHeight()));
		
		gameTitleS = new Sprite(new Texture(Gdx.files.internal("gameTitle.png")));
		gameTitleS.getTexture().setFilter(TextureFilter.Nearest, TextureFilter.MipMapLinearLinear);
		gameTitleS = new Sprite(gameTitleS);
		gameTitleS.setPosition(Extras.xUnite(128+64), Extras.yUnite(320));
		gameTitleS.setSize(Extras.xUnite(423), Extras.yUnite(100));
		
		//ScreenSprite decomposer
		
		//*************SINGLE PLAYER BUTTON***********************
		TextureRegion screenTexture= new TextureRegion(new Texture(Gdx.files.internal("screenSprite.png")),0,0,135,42);
		singlePlayerButtonSprite = new Sprite(screenTexture);
		singlePlayerButtonSprite.setPosition(Extras.xUnite(400-135/2), Extras.yUnite(480-50-155));
		singlePlayerButtonSprite.setSize(Extras.xUnite(135),Extras.yUnite(43));
		singlePlayerButtonSprite.getTexture().setFilter(TextureFilter.Nearest, TextureFilter.MipMapLinearLinear);
		singlePlayerButton=new Button(singlePlayerButtonSprite);
		
		//*************MULTIPLAYER BUTTON***********************
		screenTexture= new TextureRegion(new Texture(Gdx.files.internal("screenSprite.png")),0,125,135,50);
		multiplayerButtonSprite = new Sprite(screenTexture);
		multiplayerButtonSprite.setPosition(Extras.xUnite(400-135/2), Extras.yUnite(480-50-200));
		multiplayerButtonSprite.setSize(Extras.xUnite(135),Extras.yUnite(50));
		multiplayerButtonSprite.getTexture().setFilter(TextureFilter.Nearest, TextureFilter.MipMapLinearLinear);
		multiplayerButton=new Button(multiplayerButtonSprite);
		
		//*************scoreBOARD BUTTON***********************
		screenTexture= new TextureRegion(new Texture(Gdx.files.internal("screenSprite.png")),0,85,135,42);
		scoreBoardButtonSprite = new Sprite(screenTexture);
		scoreBoardButtonSprite.setPosition(Extras.xUnite(400-135/2), Extras.yUnite(480-50-245));
		scoreBoardButtonSprite.setSize(Extras.xUnite(135),Extras.yUnite(43));
		scoreBoardButtonSprite.getTexture().setFilter(TextureFilter.Nearest, TextureFilter.MipMapLinearLinear);
		scoreBoardButton=new Button(scoreBoardButtonSprite);
		
		//*************ABOUT BUTTON***********************
		screenTexture= new TextureRegion(new Texture(Gdx.files.internal("screenSprite.png")),0,44,135,42);
		aboutButtonSprite = new Sprite(screenTexture);
		aboutButtonSprite.setPosition(Extras.xUnite(400-135/2), Extras.yUnite(480-50-290));
		aboutButtonSprite.setSize(Extras.xUnite(135),Extras.yUnite(43));
		aboutButtonSprite.getTexture().setFilter(TextureFilter.Nearest, TextureFilter.MipMapLinearLinear);
		aboutButton=new Button(aboutButtonSprite);
		
		
		screenTexture= new TextureRegion(new Texture(Gdx.files.internal("screenSprite.png")),137,0,260,300);
		panelBg = new Sprite(screenTexture);
		panelBg.setPosition(Extras.xUnite(400-260/2), Extras.yUnite(480/2-300/2));
		panelBg.setSize(Extras.xUnite(260),Extras.yUnite(300));
		panelBg.getTexture().setFilter(TextureFilter.Nearest, TextureFilter.MipMapLinearLinear);
	}

	
	//****************************   RENDER *******************************
	@Override
	public void render(float delta) {
		time+=delta;
		batch.begin();
			bg1S.draw(batch);
			bg2S.animate(batch,0.1f);
			bg3S.animate(batch,0.2f);
			
				eventsListener();
				//*********Animation Play
				
				panelBg.draw(batch);
				singlePlayerButtonSprite.draw(batch);
				multiplayerButtonSprite.draw(batch);
				scoreBoardButtonSprite.draw(batch);
				aboutButtonSprite.draw(batch);
				

				//*********Animation Title
				System.out.println(time);
				gameTitleS.draw(batch);
						
		batch.end();
	}
	

	public void eventsListener(){

		if(singlePlayerButton.isTouched()){
			game.setScreen(new PlayScreen(game));
		}else if (multiplayerButton.isTouched()){
			game.setScreen(new MultiplayerScreen(game));
		}
	}
	
	@Override
	public void resize(int width, int height) {
        show();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		bg1S.getTexture().dispose();
		bg2S.getTexture().dispose();
		bg3S.getTexture().dispose();
		gameTitleS.getTexture().dispose();
	}
	
   
    
}