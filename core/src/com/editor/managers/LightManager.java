package com.editor.managers;

import com.badlogic.gdx.physics.box2d.World;

import java.util.HashMap;

import box2dLight.RayHandler;

/**
 * File: LightManager.java
 * Purpose: Used as a way to refer to the different RayHandlers that are assoiated with their repesctive worlds
 * Last Edited: 7/26/2016
 *
 * @author Luke Roche
 */
public class LightManager
{
																		//Default
	//public static RayHandler handler = new RayHandler(WorldManager.worlds.get(0));

	//HashMap of all the RayHandlers within the game
	//Uses the same keys as the world they are associated with in WorldManager.java
	private static HashMap<String, RayHandler> rayHandlerHash = new HashMap<String, RayHandler>();

	public LightManager(){}

	public static void addRayHandler(String key, World world)
	{
		RayHandler handler= new RayHandler(world);
		rayHandlerHash.put(key, handler);

	}

	public static RayHandler deleteRayHandler(String key)
	{
		return rayHandlerHash.remove(key);
	}

	//do we really need this?
	public static void update(){}
	
	public static void setWorld(RayHandler handler, World world){
		handler.setWorld(world);
	}

}
