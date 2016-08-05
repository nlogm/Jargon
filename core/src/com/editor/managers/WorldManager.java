package com.editor.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import java.util.HashMap;
import com.badlogic.gdx.utils.Array;
import com.editor.constants.WorldConstants;

public class WorldManager {

	public static HashMap<String, World> worlds = new HashMap<String, World>();

	public static void init() 
	{	
		addWorld("one",new World(new Vector2(0, -9.81f), true));
		addWorld("two", worlds.get("one"));	
	}

	public static void addWorld(String key, World world)
	{
		worlds.put(key, world);
	}

	public static World getWorld(String key)
	{
		return worlds.get(key);
	}

	public static void disposeAllBodies(String key)
	{
		Array<Body> bodies = new Array<Body>();
		worlds.get(key).getBodies(bodies);
		int destroyCount = 0;
		System.out.println("Attempting to destroy " + worlds.get(key).getBodyCount() + " bodies");
		for (Body b : bodies) {
			((Sprite) b.getUserData()).getTexture().dispose();
			worlds.get(key).destroyBody(b);
			destroyCount++;
		}

		System.out.println("Successfully destroyed " + destroyCount + " body(s), " + worlds.get(key).getBodyCount() + " remain");

	}

}
