package com.editor.lights;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import box2dLight.ChainLight;
import box2dLight.ConeLight;
import box2dLight.PointLight;

public class Light {
	
	protected Vector2 position;
	protected float rays, distance, coneDegree, distanceDegree;
	protected float[] chain;
	protected Color lightColor;
	
	protected boolean bleed;
	
	protected PointLight pointLight;
	protected ConeLight coneLight;
	protected ChainLight chainLight;
	
	public Light(Vector2 position, float rays, float distance, Color color){
		
	}
	
	public void attachToBody(Body body){
		
	}

}
