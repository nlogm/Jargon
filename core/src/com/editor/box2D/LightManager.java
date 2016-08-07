package com.editor.box2D;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.physics.box2d.World;
import com.editor.box2D.WorldManager;

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
	//private  static RayHandler handler = new RayHandler(WorldManager.worlds.get(0));

	//HashMap of all the RayHandlers within the game
	//Uses the same keys as the world they are associated with in WorldManager.java
	
	// this is fucking retarded we need one rayhandler and just change the world
	// to render to
	private static HashMap<String, RayHandler> rayHandlerHash = new HashMap<String, RayHandler>();

	public LightManager(){}

	public static RayHandler handler = new RayHandler(WorldManager.getWorld("one"));
	
	public static void init()
	{
		handler.setShadows(true);
		handler.setAmbientLight(0.5f);
		handler.diffuseBlendFunc.set(GL20.GL_DST_COLOR, GL20.GL_SRC_COLOR);
		handler.setBlur(true);
	}

	public static RayHandler deleteRayHandler(String key)
	{
		return rayHandlerHash.remove(key);
	}

	//do we really need this?
	
	///Yes?! How else will the light map work?!
	public static void update(){
		handler.update();
	}
	
	//Why the fuck would they change the handler like this
	//if they have the handler and the world we dont need a function for it
	public static void setWorld(RayHandler handler, World world){
		handler.setWorld(world);
	}

}
