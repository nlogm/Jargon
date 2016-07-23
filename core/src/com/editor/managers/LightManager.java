package com.editor.managers;

import com.badlogic.gdx.physics.box2d.World;

import box2dLight.RayHandler;

public class LightManager {
																		//Default
	public static RayHandler handler = new RayHandler(WorldManager.worlds.get(0));
	
	public static void init(){
		handler.setShadows(true);
		handler.setAmbientLight(.5f);
		
	}
	public static void update(){
		
	}
	
	public static void setWorld(World world){
		handler.setWorld(world);
	}

}
