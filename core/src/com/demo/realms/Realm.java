package com.demo.realms;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.editor.box2D.WorldManager;
import com.editor.box2D.constants.Helper;
import com.editor.box2D.constants.WorldConstants;
import com.editor.box2D.entity.BoxEntity;

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
	
	// World stepping variables
	private float frameTime;
	private double accumulator;

	public Realm(String realmID) {

		// id to reference realm
		this.realmID = realmID;

		// Create realm specific renderer
		render = new Box2DDebugRenderer();
		
		batch = new SpriteBatch();
		
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setAutoShapeType(true);
		
		
		
		// Creates a world for which the rayhandler will render to
		world = new World(new Vector2(0, -9.81f), true);
		

		// Create realm specific RayHandler
		handler = new RayHandler(world);
		handler.setAmbientLight(.5f);
		
		handler.setShadows(true);
		
		//BoxEntity entity = new BoxEntity(Helper.createVec(1, 1), Helper.createVec(100, 100), BodyType.StaticBody);
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(new Vector2(1, 1));

		Body body = world.createBody(bodyDef);

		FixtureDef fixtureDef = new FixtureDef();
		Fixture fixture;
			PolygonShape rectangle = new PolygonShape();
			rectangle.setAsBox(1,1);
			fixtureDef.shape = rectangle;
			fixtureDef.density = 5;
			fixtureDef.friction = .3f;
			fixtureDef.restitution = 0;
			fixture = body.createFixture(fixtureDef);
			//rectangle.dispose();
			
		
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

	public void update(OrthographicCamera camera) {
		doPhysicsStep(Gdx.graphics.getDeltaTime());

		
		batch.setProjectionMatrix(camera.combined);
		shapeRenderer.setProjectionMatrix(camera.combined);
		handler.update();
	}

	public void render(OrthographicCamera camera) {

		// refresh render buffer
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc (GL20.GL_DST_COLOR, GL20.GL_ZERO);
		Gdx.gl.glClearColor(0, 0, 0, 0);
		
		render.render(world, camera.combined);

		handler.render();
		
		batch.begin();
		
		batch.end();
		
		
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
