package com.editor.managers;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.physics.box2d.World;

import box2dLight.RayHandler;

public class LightManager {
																		//Default
	public static RayHandler handler = new RayHandler(WorldManager.getWorld("one"));
	
	public static void init(){
		handler.setShadows(true);
		handler.setAmbientLight(0f);
		handler.diffuseBlendFunc.set(GL20.GL_DST_COLOR, GL20.GL_SRC_COLOR);
		handler.setBlur(true);
		
	}
	public static void update(){
		
	}
	
	public static void setWorld(World world){
		handler.setWorld(world);
	}

}
