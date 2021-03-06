package com.editor.box2D.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.editor.box2D.bodies.BodyCreator;
import com.editor.listeners.collision.FixtureData;

public class CircleEntity extends Entity{

	public CircleEntity(Vector2 positionInMeters, float radius, BodyType type) {
		super(positionInMeters, radius, type);
	}
	
	@Override
	@Deprecated
	public void createBody(String worldKey){
		bodyObjects = BodyCreator.createAndGetEntity(position, new Vector2(radius, radius), type, true, worldKey);
	}
	
	
	public void createBody(World world){
		bodyObjects = BodyCreator.createAndGetEntity(position, new Vector2(radius, radius), type, true, world, FixtureData.OTHER);
	}

	@Override
	public void attachNewSprite(Sprite sprite) {
		super.attachNewSprite(sprite);
	}

	@Override
	public void attachNewSprite(Texture tex) {
		super.attachNewSprite(tex);
	}

	@Override
	public void attachNewSprite(String internalPath) {
		super.attachNewSprite(internalPath);
	}

	/**
	 * Not applicable for type 'Circle'
	 */
	@Override
	@Deprecated
	public void setSize(Vector2 dimensionsInMeters) {
		super.setSize(dimensionsInMeters);
	}

	@Override
	public void setSize(float radiusInMeters) {
		super.setSize(radiusInMeters);
	}
	

	
	public float getRadius(){ return this.radius;}
	
	/**
	 * Not applicable for type 'Circle'
	 */
	@Override
	@Deprecated
	public Vector2 getDimensions(){ return this.dimensions;}
	
	@Override
	public Sprite getEntitySprite(){ return this.entitySprite;}
	
	@Override
	public BodyType getBodyType(){ return this.type;} 


}
