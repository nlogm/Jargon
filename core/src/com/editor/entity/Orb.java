package com.editor.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.editor.bodies.LightCreator;

import box2dLight.ConeLight;

public class Orb {
	
	private float xCoor, yCoor, radius;
	private CircleEntity entity;
	public Orb(float x, float y, float radiusInMeters, BodyType type){
		this.xCoor = x;
		this.yCoor = y;
		this.radius = radiusInMeters;
		entity = new CircleEntity(new Vector2(x, y), radius, type);
		entity.createBody("one");
	}
	
	public void init(float bufferSize, Color ... color){
		Array<ConeLight> lights = new Array<ConeLight>();
		float current = (180 / (color.length));
		float distance = current + (current / bufferSize);
		float seperator = 0;
		int i = 0;
		for(Color col : color){
			lights.add(LightCreator.createConeLight(new Vector2(xCoor, yCoor),
					col, radius * 2, false, 0, distance));
			seperator = i * (360 / color.length);
			lights.get(lights.size - 1).attachToBody(entity.getBody(), 0, 0, seperator);
			lights.get(lights.size - 1).setColor(col);
			i++;
			
		}
	}

}
