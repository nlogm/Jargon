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
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.editor.bodies.LightCreator;
import com.editor.constants.Scaler;
import com.editor.constants.WorldConstants;
import com.editor.entity.BoxEntity;
import com.editor.entity.CircleEntity;
import com.editor.entity.Entity;
import com.editor.managers.EntityManager;
import com.editor.managers.LightManager;
import com.editor.managers.WorldManager;
import com.engine.joints.DistanceJoint;
import com.engine.joints.JointedEntity;
import com.engine.joints.attributes.JointedAttributes.DistanceAttributes;

import box2dLight.RayHandler;

public class Core extends ApplicationAdapter {

	public OrthographicCamera camera;
	private double accumulator;
	public Box2DDebugRenderer render;
	public RayHandler handler;
	public SpriteBatch batch;
	Entity e,k;

	@Override
	public void create() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth() / Scaler.PPM, Gdx.graphics.getHeight() / Scaler.PPM);
		WorldManager.init();
		LightManager.init();
		batch = new SpriteBatch();
		render = new Box2DDebugRenderer();
		LightManager.setWorld(WorldManager.getWorld("one"));
		
		
		testZone();
		
	}
	
	public void testZone(){
		JointedEntity jj = new DistanceJoint("");
		JointedEntity l = new JointedEntity("");
		l.seek("one, ", " two, " + " three");

		e = new CircleEntity(new Vector2(3.34f, 3.5f), .2f, BodyType.DynamicBody);
		e.createBody("one");
		e.setRestitution(1);
		k = new BoxEntity(new Vector2(3.34f, .1f), new Vector2(.5f, .1f), BodyType.StaticBody);
		k.createBody("one");
		jj.addBodyA(k.getBody());
		k = new BoxEntity(new Vector2(3.34f, .5f), new Vector2(.5f, .1f), BodyType.DynamicBody);
		k.createBody("one");
		jj.addBodyB(k.getBody());
		Object[] bods = {
			new Vector2(0,0), new Vector2(0,0), .7f, 2f, 0
		};
		jj.setAttribute(bods, DistanceAttributes.Local_Anchor_A, DistanceAttributes.Local_Anchor_B,
				DistanceAttributes.Length, DistanceAttributes.Frequency_Hz, DistanceAttributes.Damping_Ratio);
		jj.assignAttributes();
		WorldManager.getWorld("one").createJoint((DistanceJointDef)jj.getJointDef());
		LightCreator.createPointLight(new Vector2(3, 3), Color.WHITE, 3, false);
		e = new BoxEntity(new Vector2(0, 0), new Vector2(50f, .01f), BodyType.StaticBody);
		e.createBody("one");
		
		k = new BoxEntity(new Vector2(2.7f, .5f), new Vector2(.1f, .5f), BodyType.StaticBody);
		k.createBody("one");
		k = new BoxEntity(new Vector2(4f, .5f), new Vector2(.1f, .5f), BodyType.StaticBody);
		k.createBody("one");
	}

	int num = 0;

	@Override
	public void render() {
		// Synch physics
		doPhysicsStep(Gdx.graphics.getDeltaTime() * WorldConstants.PHYSICS_SPEED);
		// Update entities
		EntityManager.update();

		// Update everything with scaled property
		camera.update();
		batch.setProjectionMatrix(camera.combined);

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
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClearColor(0, 0, 0, 0);

		// Render bodies and lights
		batch.begin();
		EntityManager.renderBodies(batch);
		batch.end();

		LightManager.handler.render();

		// Render bodies in Box2DDebugRenderer
		render.render(WorldManager.getWorld("one"), camera.combined);

	}

	private float frameTime;

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
