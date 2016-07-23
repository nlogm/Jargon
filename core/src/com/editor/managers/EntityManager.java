package com.editor.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.utils.Array;

public class EntityManager {
	
	private static Array<Body> bodyList = new Array<Body>();
	private static Array<Vector2> bodyDimensions = new Array<Vector2>();
	
	
	
	public static Body createBody(BodyDef b, Vector2 dimensions, int worldNum){
		try{
			bodyList.add(WorldManager.worlds.get(worldNum).createBody(b));
			bodyDimensions.add(dimensions);
		}catch(Exception e){
			Gdx.app.log("EntityManager", "Failed to add body to list");
		}
		return bodyList.get(bodyList.size - 1);
	}

	public static void update(int num){
		int tracker = 0;
		for(Body currentBody : bodyList){
			if(Sprite.class.isAssignableFrom(currentBody.getClass()) && currentBody.getUserData() != null){
				//For position
				((Sprite)currentBody.getUserData()).setPosition(
						currentBody.getPosition().x - (bodyDimensions.get(tracker).x),
						currentBody.getPosition().y - (bodyDimensions.get(tracker).y)
						);
				//For rotations
				((Sprite)currentBody.getUserData()).setOrigin(
						currentBody.getPosition().x - (bodyDimensions.get(tracker).x),
						currentBody.getPosition().y - (bodyDimensions.get(tracker).y)
						);
				
				((Sprite)currentBody.getUserData()).setSize(bodyDimensions.get(tracker).x * 2, bodyDimensions.get(tracker).y * 2);
				tracker++;
			}
		}
	}
	public static void renderBodies(SpriteBatch batch){
		
		for(Body currentBody : bodyList)
			if(Sprite.class.isAssignableFrom(currentBody.getClass()) && currentBody.getUserData() != null){
				((Sprite)currentBody.getUserData()).draw(batch);
			}
		
		
	}
	
	
}
