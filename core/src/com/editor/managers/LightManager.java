package com.editor.managers;

import box2dLight.RayHandler;

public class LightManager {
	
	public static RayHandler handler = new RayHandler(WorldManager.world);
	
	public static void init(){
		handler.setShadows(true);
		handler.setAmbientLight(.5f);
	}
	public static void update(){
		
	}

}
