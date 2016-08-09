package com.demo.realms;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.editor.box2D.constants.WorldConstants;

import box2dLight.RayHandler;

public class Realm {

	// Box2D objects
	private Box2DDebugRenderer render;
	private RayHandler handler;
	private World world;

	// Realm objects
	private String realmID;
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	private Matrix4 currentMatrix;
	
	// World stepping variables
	private float frameTime;
	private double accumulator;

	public Realm(String realmID, Matrix4 cameraMatrix) {

		//Matrix to update and render on
		this.currentMatrix = cameraMatrix;
		// id to reference realm
		this.realmID = realmID;

		// Create realm specific renderer
		render = new Box2DDebugRenderer();

		// Creates a world for which the rayhandler will render to
		world = new World(new Vector2(0, -9.81f), true);

		// Create realm specific RayHandler
		handler = new RayHandler(world);

	}

	/**
	 * More efficient way of setting ambient light, no need to release Color
	 * objects
	 * 
	 * @param r
	 * @param g
	 * @param b
	 * @param a
	 */
	public void setMoodAmbient(float r, float g, float b, float a) {
		handler.setAmbientLight(r, g, b, a);
	}

	/**
	 * Sets color of the ambient light
	 * 
	 * @param color
	 */
	public void setMoodAmbient(Color color) {
		handler.setAmbientLight(color);
	}

	/**
	 * Sets the darkness of light
	 * 
	 * @param ambient,
	 *            the brightness for 0-1.0f
	 */
	public void setMoodAmbient(float ambient) {
		handler.setAmbientLight(ambient);
	}

	public void update() {
		doPhysicsStep(Gdx.graphics.getDeltaTime());
		batch.setProjectionMatrix(currentMatrix);
	}

	public void render() {

		// refresh render buffer
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClearColor(0, 0, 0, 0);

	}

	/**
	 * Sync physics and frame rate
	 * 
	 * @param deltaTime
	 */
	private void doPhysicsStep(float deltaTime) {
		// fixed time step for all devices
		frameTime = Math.min(deltaTime, 0.25f);
		accumulator += frameTime;
		while (accumulator >= WorldConstants.TIME_STEP) {
			world.step(WorldConstants.TIME_STEP, WorldConstants.VELOCITY_ITERATIONS, WorldConstants.POSITION_ITERATIONS);
			accumulator -= WorldConstants.TIME_STEP;
		}
	}

	public RayHandler getRayHandler() {
		return this.handler;
	}

	public World getWorld() {
		return this.world;
	}

}
