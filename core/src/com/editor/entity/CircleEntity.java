package com.editor.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.editor.bodies.BodyCreator;

public class CircleEntity extends Entity{

	public CircleEntity(Vector2 positionInMeters, float radius, BodyType type) {
		super(positionInMeters, radius, type);
	}
	
	public void createBody(int worldNum){
		
		bodyObjects = BodyCreator.createAndGet(position, new Vector2(radius, radius), type, true, worldNum);
		
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
