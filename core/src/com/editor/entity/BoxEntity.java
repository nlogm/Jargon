package com.editor.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.editor.bodies.BodyCreator;
import com.editor.constants.BodyReferences;

public class BoxEntity extends Entity{

	public BoxEntity(Vector2 positionInMeters, Vector2 dimensionsInMeteres, BodyType type) {
		super(positionInMeters, dimensionsInMeteres, type);
	}
	
	public void createBody(int worldNum){
		
		bodyObjects = BodyCreator.createAndGet(position, dimensions, type, false, worldNum);
		
	}
	
	@Override
	public void addFixtureDefProperties(float density, float friction, float restitution){
		super.addFixtureDefProperties(density, friction, restitution);
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

	@Override
	public void setSize(Vector2 dimensionsInMeters) {
		super.setSize(dimensionsInMeters);
	}

	/**
	 * Not applicable for type 'Circle'
	 */
	@Override
	@Deprecated
	public void setSize(float radiusInMeters) {
		super.setSize(radiusInMeters);
	}
	
	/**
	 * Not applicable for type 'Circle'
	 */
	@Override
	@Deprecated
	public float getRadius(){ return this.radius;}
	
	@Override
	public Vector2 getDimensions(){ return this.dimensions;}
	
	@Override
	public Sprite getEntitySprite(){ return this.entitySprite;}
	
	@Override
	public BodyType getBodyType(){ return this.type;} 

}
