package com.editor.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.editor.constants.WorldConstants;

public class WorldManager {

	public static Array<World> worlds = new Array<World>();

	public static void init() {
		
		worlds.add(new World(new Vector2(0, -9.81f), true));
		worlds.add(worlds.get(0));
		
	}

	public static void disposeAllBodies(int id) {
		Array<Body> bodies = new Array<Body>();
		worlds.get(id).getBodies(bodies);
		int destroyCount = 0;
		System.out.println("Attempting to destroy " + worlds.get(id).getBodyCount() + " bodies");
		for (Body b : bodies) {
			((Sprite) b.getUserData()).getTexture().dispose();
			worlds.get(id).destroyBody(b);
			destroyCount++;
		}

		System.out.println("Successfully destroyed " + destroyCount + " body(s), " + worlds.get(id).getBodyCount() + " remain");

	}

}
