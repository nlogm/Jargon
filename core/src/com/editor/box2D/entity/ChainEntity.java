package com.editor.box2D.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.editor.box2D.bodies.BodyCreator;

public class ChainEntity extends Entity{

	public ChainEntity(BodyType type) {
		super(type);
	}

	
	@Override
	public void createBody(String worldKey){
		chainVerts = new Vector2[preLoadedVerts.size];
		int i = 0;
		for(Vector2 finalVertices : preLoadedVerts){
			chainVerts[i] = finalVertices;
			i++;
		}
		bodyObjects = BodyCreator.createChain(chainVerts, type, worldKey);
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
	@Deprecated
	public void setSize(float radiusInMeters) {
		super.setSize(radiusInMeters);
	}
	

	@Deprecated
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
