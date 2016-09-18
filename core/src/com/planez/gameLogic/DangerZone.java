package com.planez.gameLogic;

import java.awt.Color;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.utils.Array;

//an array of danger areas (surfaces), when plane is on the danger zone it automatically explode  
public class DangerZone {
	private Array<DangerArea> dangerAreas;
	private ShapeRenderer shapeRenderer;

	public DangerZone() {
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setAutoShapeType(true);
		dangerAreas = new Array<DangerZone.DangerArea>();
	}

	public void addDangerZone(String uniqueAreaName, float x, float y, float width, float height) {
		dangerAreas.add(new DangerArea(uniqueAreaName, x, y, width, height));
	}

	// update the dangerArea Surface of the area with the given name
	public boolean updateDangerZone(String uniqueAreaName, Rectangle rect) {
		for (int i = 0; i < dangerAreas.size; i++) {
			DangerArea dangerArea = dangerAreas.get(i);
			if (dangerArea.getUniqueAreaName().equals(uniqueAreaName)) {
				dangerArea.setX(rect.x);
				dangerArea.setY(rect.y);
				dangerArea.setWidth(rect.width);
				dangerArea.setHeight(rect.height);
				return true;
			}
		}
		return false;
	}

	public void draw(SpriteBatch batch, float delta) {
		shapeRenderer.begin();
		for (int i = 0; i < dangerAreas.size; i++) {
			DangerArea dangerArea = dangerAreas.get(i);
			shapeRenderer.setColor(255, 0, 0, 0);
			shapeRenderer.rect(dangerArea.getX(),dangerArea.getY(),dangerArea.getWidth(), dangerArea.getHeight());
		}
		shapeRenderer.end();
	}

	// check if the given surface is in a danger area
	public boolean isDangerZone(Rectangle rect) {
		for (int i = 0; i < dangerAreas.size; i++) {
			DangerArea dangerArea = dangerAreas.get(i);
			System.out.println(dangerArea.getY()+" < "+ rect.y +" < " + (dangerArea.getY()+dangerArea.getHeight()));
			if ( ((dangerArea.getX()< rect.x) && ( rect.x  < (dangerArea.getX() + dangerArea.getWidth() )))
			 &&((dangerArea.getY()< rect.y) && ( rect.y  < (dangerArea.getY() + dangerArea.getHeight())))
			) {
				System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
				return true;
			}
		}
		return false;
	}

	public Array<DangerArea> getDangerAreas() {
		return dangerAreas;
	}

	public void setDangerAreas(Array<DangerArea> dangerAreas) {
		this.dangerAreas = dangerAreas;
	}

	// DangerArea Private class
	class DangerArea {
		float x,y ,width , height;
		String uniqueAreaName;

		public DangerArea(String uniqueAreaName, float x, float y, float width, float height) {
			this.uniqueAreaName = uniqueAreaName;
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}

		
		public String getUniqueAreaName() {
			return uniqueAreaName;
		}

		public void setUniqueAreaName(String uniqueAreaName) {
			this.uniqueAreaName = uniqueAreaName;
		}

		public float getX() {
			return x;
		}

		public void setX(float x2) {
			this.x = x2;
		}

		public float getY() {
			return y;
		}

		public void setY(float y2) {
			this.y = y2;
		}

		public float getWidth() {
			return width;
		}

		public void setWidth(float width) {
			this.width = width;
		}

		public float getHeight() {
			return height;
		}

		public void setHeight(float height) {
			this.height = height;
		}
	}

}
