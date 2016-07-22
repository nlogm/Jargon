package com.editor.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.editor.constants.WorldConstants;

public class WorldManager{
	
	public static World world = new World(new Vector2(0, -9.81f), true);
	
	@SuppressWarnings("static-access")
	public static void checkBodies(){
		
		if(world.getVelocityThreshold() > WorldConstants.VELOCITY_ITERATIONS){
			Gdx.app.log("Velocity out of step", "Error on world step");
		}
		
	}
	
	public static void disposeAllBodies(){
		Array<Body> bodies = new Array<Body>();
		world.getBodies(bodies);
		int destroyCount = 0;
		System.out.println("Attempting to destroy " + world.getBodyCount()+ " bodies");
		for(Body b : bodies){
			((Sprite)b.getUserData()).getTexture().dispose();
			world.destroyBody(b);
			destroyCount++;
		}
			
		System.out.println("Successfully destroyed " + destroyCount + " body(s), " + world.getBodyCount() + " remain");
		
		
	}
	

}
