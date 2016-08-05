package com.editor.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.editor.box2D.bodies.LightCreator;
import com.editor.box2D.constants.Scaler;
import com.editor.box2D.constants.WorldConstants;
import com.editor.box2D.entity.BoxEntity;
import com.editor.box2D.entity.ChainEntity;
import com.editor.box2D.entity.Entity;
import com.editor.box2D.entity.Orb;
import com.editor.box2D.entity.Player;
import com.editor.listeners.CollisionReciever;
import com.editor.listeners.InputReciever;
import com.editor.box2D.EntityManager;
import com.editor.box2D.LightManager;
import com.editor.box2D.WorldManager;

import box2dLight.ConeLight;
import box2dLight.RayHandler;

public class Core extends ApplicationAdapter {

	public OrthographicCamera camera;
	private double accumulator;
	public Box2DDebugRenderer render;
	public RayHandler handler;
	public SpriteBatch batch;
	Entity e,k;
	private Orb orb;
	private Player player;
	private InputReciever input;

	@Override
	public void create() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth() / Scaler.PPM, Gdx.graphics.getHeight() / Scaler.PPM);
		WorldManager.init();

		//LightManager.init();
		//h = new JointedHuman(new Vector2(2.8f,4), new Vector2(.25f, 1));
		//h.create();
		batch = new SpriteBatch();
		render = new Box2DDebugRenderer();

		//ground
		//e = new BoxEntity(new Vector2(0,0), new Vector2(10, .1f), BodyType.StaticBody);
		//e.createBody();
		//e.addFixtureDefProperties(1, .3f, 0);

		e = new BoxEntity(new Vector2(2.4f,2.5f), new Vector2(.1f, .1f), BodyType.StaticBody);
		//e.createBody(1);
		
		e = new BoxEntity(new Vector2(3.2f,2.5f), new Vector2(.1f, .1f), BodyType.StaticBody);
		//e.createBody(num);
		//LightManager.setWorld(WorldManager.get(num));
		LightCreator.createPointLight(new Vector2(3 , 3), Color.WHITE, 3, false);

		LightManager.init();
		batch = new SpriteBatch();
		render = new Box2DDebugRenderer();
		//LightManager.setWorld(WorldManager.getWorld("one"));
		WorldManager.getWorld("one").setContactListener(new CollisionReciever());
		testZone();
		player = new Player(new Vector2(1, 1));
		player.setFriction(0.1f);
		input = new InputReciever(player);
		Gdx.input.setInputProcessor(input);
	}
	
	public void testZone(){
	
		e = new ChainEntity(BodyType.StaticBody);
		for(float i = -10; i < 10; i+= .1f){
			e.addVertice(new Vector2(i, (float) (Math.sin(i) / 6)));
		}
		e.createBody("one");
	}
	ConeLight c;
	int num = 0;

	@Override
	public void render() {
		// Synch physics
		doPhysicsStep(Gdx.graphics.getDeltaTime() * WorldConstants.PHYSICS_SPEED);
		// Update entities
		EntityManager.update();

		// Update everything with scaled property
		camera.update();
		camera.position.set(player.getBody().getPosition(), 0);
		batch.setProjectionMatrix(camera.combined);

		player.update();
		// camera.combined.cpy().scl(Scaler.PPM) deprecated. This accomplishes
		// same thing.
		LightManager.handler.setCombinedMatrix(camera);

		// Update and handle exceptions in lights
		LightManager.handler.update();

		// Set title to FPS
		Gdx.graphics.setTitle(Gdx.graphics.getFramesPerSecond() + "");

		// To be altered
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc (GL20.GL_DST_COLOR, GL20.GL_ZERO);
		Gdx.gl.glClearColor(0, 0, 0, 0);
		

		// Render bodies and lights
		batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		batch.begin();
		EntityManager.renderBodies(batch);
		batch.end();

		LightManager.handler.render();

		// Render bodies in Box2DDebugRenderer
		render.render(WorldManager.getWorld("one"), camera.combined);
	}

	private float frameTime;

	/**
	 * Sync physics and frame rate 
	 * @param deltaTime
	 */
	private void doPhysicsStep(float deltaTime) {
		// fixed time step for all devices
		frameTime = Math.min(deltaTime, 0.25f);
		accumulator += frameTime;
		while (accumulator >= WorldConstants.TIME_STEP) {
			WorldManager.getWorld("one").step(WorldConstants.TIME_STEP, WorldConstants.VELOCITY_ITERATIONS,
					WorldConstants.POSITION_ITERATIONS);
			accumulator -= WorldConstants.TIME_STEP;
		}
	}

	/*
	 * public void interpolate(float alpha) { for (Entity entity : entities) {
	 * Transform transform = entity.getBody().getTransform(); Vector2
	 * bodyPosition = transform.getPosition(); Vector2 position =
	 * entity.getPosition(); float angle = entity.getAngle(); float bodyAngle =
	 * transform.getRotation();
	 * 
	 * position.x = bodyPosition.x * alpha + position.x * (1.0f - alpha);
	 * position.y = bodyPosition.y * alpha + position.x * (1.0f - alpha);
	 * entity.setAngle(bodyAngle * alpha + angle * (1.0f - alpha)); } }
	 */
}
