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

import box2dLight.ConeLight;
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

		e = new CircleEntity(new Vector2(3.34f - 2.5f, .5f), .2f, BodyType.DynamicBody);
		e.createBody("one");
		e.setDensity(1f);
		float d = .5f;
		c = LightCreator.createConeLight(new Vector2(3, 5), Color.RED, d, false, -90, 45);
		c.attachToBody(e.getBody());
		c = LightCreator.createConeLight(new Vector2(3, 5), Color.BLUE, d, false, -90, 90);
		c.attachToBody(e.getBody());
		c = LightCreator.createConeLight(new Vector2(3, 5), Color.GOLD, d, false, -90, 135);
		c.attachToBody(e.getBody());
		c = LightCreator.createConeLight(new Vector2(3, 5), Color.CYAN, d, false, -90, 180);
		c.attachToBody(e.getBody());
		
		
		k = new BoxEntity(new Vector2(3.2f - 2.5f, .1f), new Vector2(.5f, .1f), BodyType.StaticBody);
		k.createBody("one");
		k.setAngleDegrees(-30);
		jj.addBodyA(k.getBody());
		
		k = new BoxEntity(new Vector2(3.34f - 2.5f, .3f), new Vector2(.5f, .1f), BodyType.DynamicBody);
		k.createBody("one");
		k.setAngleDegrees(-30);
		
		jj.addBodyB(k.getBody());
		Object[] bods = {
			new Vector2(0,0), new Vector2(0,0), 1f, 2.5f, 0
		};
		jj.setAttribute(bods, DistanceAttributes.Local_Anchor_A, DistanceAttributes.Local_Anchor_B,
				DistanceAttributes.Length, DistanceAttributes.Frequency_Hz, DistanceAttributes.Damping_Ratio);
		jj.assignAttributes();
		WorldManager.getWorld("one").createJoint((DistanceJointDef)jj.getJointDef());
		
		e = new BoxEntity(new Vector2(0, 0), new Vector2(50f, .01f), BodyType.StaticBody);
		e.createBody("one");
		
		k = new BoxEntity(new Vector2(2.89f - 2.5f, .75f), new Vector2(.1f, .95f), BodyType.StaticBody);
		k.createBody("one");
		k.setAngleDegrees(-30f);
		//c = LightCreator.createConeLight(new Vector2(0, 4f), Color.GRAY, 10, false, -45, 45);
		c = LightCreator.createConeLight(new Vector2(Gdx.graphics.getWidth() / Scaler.PPM, 4f), Color.GRAY,
				10, false, 225, 45);
		k = new BoxEntity(new Vector2(4.15f - 2.5f, .5f), new Vector2(.1f, .75f), BodyType.StaticBody);
		k.createBody("one");
		k.setAngleDegrees(-30f);
		
		k = new BoxEntity(new Vector2(5.76f, .5f), new Vector2(.1f, .5f), BodyType.StaticBody);
		k.createBody("one");
		k = new BoxEntity(new Vector2(5f, .5f), new Vector2(.1f, .5f), BodyType.StaticBody);
		k.createBody("one");
		
		e = new CircleEntity(new Vector2(.5f, 3f), .5f, BodyType.StaticBody);
		e.createBody("one");
		c = LightCreator.createConeLight(new Vector2(3, 5), Color.RED, 1, false, -90, 45);
		c.attachToBody(e.getBody());
		
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
