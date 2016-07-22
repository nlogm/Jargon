package com.editor.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.editor.constants.Scaler;
import com.editor.constants.WorldConstants;
import com.editor.entity.BoxEntity;
import com.editor.entity.JointedHuman;
import com.editor.managers.EntityManager;
import com.editor.managers.LightManager;
import com.editor.managers.WorldManager;

import box2dLight.RayHandler;

public class Core extends ApplicationAdapter {
	
	public OrthographicCamera camera;
    private double accumulator;
	public Box2DDebugRenderer render;
	public RayHandler handler;
	public SpriteBatch batch;
	private JointedHuman h;
	BoxEntity e;
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth() / Scaler.PPM, Gdx.graphics.getHeight() / Scaler.PPM);
		LightManager.init();

		h = new JointedHuman(new Vector2(2.8f,4), new Vector2(.25f, 1));
		h.create();
		batch = new SpriteBatch();
		render = new Box2DDebugRenderer();
		
		
		//ground
		e = new BoxEntity(new Vector2(0,0), new Vector2(10, .1f), BodyType.StaticBody);
		e.createBody();
		e.addFixtureDefProperties(1, .3f, 0);
		
		
		
		e = new BoxEntity(new Vector2(2.4f,1), new Vector2(.1f, .1f), BodyType.StaticBody);
		e.createBody();
		
		e = new BoxEntity(new Vector2(3.2f,2.5f), new Vector2(.1f, .1f), BodyType.StaticBody);
		e.createBody();
		
		
	}
	@Override
	public void render () {
		if(Gdx.input.isKeyJustPressed(Keys.SPACE))
			WorldManager.disposeAllBodies();
		//Synch physics
		doPhysicsStep(Gdx.graphics.getDeltaTime() * WorldConstants.PHYSICS_SPEED);
		
		//Update entities
		EntityManager.update();
		
		//Update everything with scaled property
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		//camera.combined.cpy().scl(Scaler.PPM) deprecated. This accomplishes same thing.
		LightManager.handler.setCombinedMatrix(camera);
		
		//Update and handle exceptions in lights
		LightManager.handler.update();
		
		
		
		//Set title to FPS
		Gdx.graphics.setTitle(Gdx.graphics.getFramesPerSecond() + "");
		
		//To be altered
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		
		//Render bodies and lights
		batch.begin();
		EntityManager.renderBodies(batch);
		batch.end();
		
		LightManager.handler.render();
		
		//Render bodies in Box2DDebugRenderer
		render.render(WorldManager.world, camera.combined);
	}
	
	private float frameTime;
	private void doPhysicsStep(float deltaTime) {
	    // fixed time step for all devices
	    frameTime = Math.min(deltaTime, 0.25f);
	    accumulator += frameTime;
	    while (accumulator >= WorldConstants.TIME_STEP) {
	       WorldManager.world.step(WorldConstants.TIME_STEP, WorldConstants.VELOCITY_ITERATIONS, WorldConstants.POSITION_ITERATIONS);
	        accumulator -= WorldConstants.TIME_STEP;
	    }
	}
	
/*public void interpolate(float alpha) {
	    for (Entity entity : entities) {
	        Transform transform = entity.getBody().getTransform();
	        Vector2 bodyPosition = transform.getPosition();
	        Vector2 position = entity.getPosition();
	        float angle = entity.getAngle();
	        float bodyAngle = transform.getRotation();

	        position.x = bodyPosition.x * alpha + position.x * (1.0f - alpha);
	        position.y = bodyPosition.y * alpha + position.x * (1.0f - alpha);
	        entity.setAngle(bodyAngle * alpha + angle * (1.0f - alpha));
	    }
	}*/
}
