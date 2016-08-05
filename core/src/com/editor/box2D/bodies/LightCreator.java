package com.editor.box2D.bodies;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import box2dLight.ConeLight;
import box2dLight.PointLight;

public class LightCreator {

	private static PointLight referencePointLight;
	private static ConeLight referenceLight;

	public static PointLight createPointLight(Vector2 positionInMeters, Color lightColor, int rays, float lightDistance,
			boolean bleed) {
	//	referencePointLight = new PointLight(LightManager.handler, rays);
		referencePointLight.setColor(lightColor);
		referencePointLight.setDistance(lightDistance);
		if (!bleed)
			referencePointLight.setSoftnessLength(0);
		referencePointLight.setPosition(positionInMeters);

		return referencePointLight;
	}

	public static PointLight createPointLight(Vector2 positionInMeters, Color lightColor, float lightDistance,
			boolean bleed) {
		//referencePointLight = new PointLight(LightManager.handler, WorldConstants.RAYS);
		referencePointLight.setColor(lightColor);
		referencePointLight.setDistance(lightDistance);
		if (!bleed)
			referencePointLight.setSoftnessLength(0);
		referencePointLight.setPosition(positionInMeters);

		return referencePointLight;
	}

	public static ConeLight createConeLight(Vector2 positionInMeters, Color lightColor, float lightDistance,
			boolean bleed, float directionLightDegrees, float coneLightDegrees) {
		
		///referenceLight = new ConeLight(LightManager.handler, 50, lightColor, lightDistance, positionInMeters.x,
				//positionInMeters.y, directionLightDegrees, coneLightDegrees);
		
		if(!bleed)
			referenceLight.setSoftnessLength(0f);
		
		return referenceLight;
	}

}
