package com.editor.entity;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.editor.constants.BodyReferences;
import com.editor.constants.Scaler;
import com.editor.managers.EntityManager;

public class Entity {

	protected Vector2 position, dimensions;
	protected float radius;
	
	protected BodyType type;
	
	protected Sprite entitySprite;
	
	protected HashMap<String, Object> bodyObjects = new HashMap<String, Object>();
	
	/**
	 * For rectangle or square bodies
	 * 
	 * @param position of body
	 * @param dimensions of body
	 * @param type of body (Kinematic, Static, Dynamic)
	 */
	public Entity(Vector2 positionInMeters, Vector2 dimensionsInMeters, BodyType type){
		this.position = positionInMeters;
		this.dimensions = dimensionsInMeters;
		this.type = type;
		
	}
	
	/**
	 * For Circle bodies
	 * 
	 * @param position of body
	 * @param radius of body
	 * @param type of body (Kinematic, Static, Dynamic)
	 */
	public Entity(Vector2 positionInMeters, float radius, BodyType type){
		this.position = positionInMeters;
		this.radius = radius;
		this.type = type;
	}
	
	public void attachNewSprite(Sprite sprite){
		entitySprite = sprite;
		((Body)bodyObjects.get(BodyReferences.BODY)).setUserData(entitySprite);
	}
	public void attachNewSprite(Texture tex){
		entitySprite = new Sprite(tex);
		((Body)bodyObjects.get(BodyReferences.BODY)).setUserData(entitySprite);
	}
	public void attachNewSprite(String internalPath){
		entitySprite = new Sprite(new Texture(Gdx.files.internal(internalPath)));
		((Body)bodyObjects.get(BodyReferences.BODY)).setUserData(entitySprite);
	}

	public void addFixtureDefProperties(float density, float friction, float restitution){
		
		((Body)(bodyObjects.get(BodyReferences.BODY))).getFixtureList().first().setDensity(density);
		((Body)(bodyObjects.get(BodyReferences.BODY))).getFixtureList().first().setFriction(friction);
		((Body)(bodyObjects.get(BodyReferences.BODY))).getFixtureList().first().setRestitution(restitution);
		
	}
	
	public void setDensity(float density){
		((Body)(bodyObjects.get(BodyReferences.BODY))).getFixtureList().first().setDensity(density);
	}
	public void setFriction(float friction){
		((Body)(bodyObjects.get(BodyReferences.BODY))).getFixtureList().first().setFriction(friction);
	}
	public void setRestitution(float restitution){
		((Body)(bodyObjects.get(BodyReferences.BODY))).getFixtureList().first().setFriction(restitution);
	}
	

	public void setSize(Vector2 dimensionsInMeters){
		this.dimensions = dimensionsInMeters;
	}
	
	public void setSize(float radiusInMeters){
		this.dimensions.x = radiusInMeters;
	}
	
	public float getRadius(){ return this.radius;};
	public Vector2 getDimensions(){ return this.dimensions;}
	public Sprite getEntitySprite(){ return this.entitySprite;}
	public BodyType getBodyType(){ return this.type;} 
	public Body getBody(){ return ((Body)bodyObjects.get(BodyReferences.BODY));}
}
