package com.editor.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.utils.Array;
import com.editor.entity.Entity;

public class EntityManager {
	
	private static Array<Body> bodyList = new Array<Body>();
	private static Array<Vector2> bodyDimensions = new Array<Vector2>();
	private static Array<Entity> entityList = new Array<Entity>();
	
	public static void addEntity(Entity e){
		entityList.add(e);
	}
		
	public static Body createBody(BodyDef b, Vector2 dimensions, String worldKey){
		try{
			bodyList.add(WorldManager.getWorld(worldKey).createBody(b));
			bodyDimensions.add(dimensions);
		}catch(Exception e){
			Gdx.app.log("EntityManager", "Failed to add body to list");
		}
		return bodyList.get(bodyList.size - 1);
	}

	public static void update()
	{
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
	
	public static Array<Entity> getEntities(){
		return entityList;
	}
	
	
}
