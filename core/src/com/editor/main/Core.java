package com.editor.main;

import java.util.StringTokenizer;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.editor.box2D.EntityManager;
import com.editor.box2D.LightManager;
import com.editor.box2D.WorldManager;
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
import com.engine.joints.utils.Rope;

import box2dLight.ConeLight;
import box2dLight.PointLight;
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
	ConeLight l1, l2;
	PointLight pussy;
	Rope r,r2;
	TextureRegion tmpRegion;
	Sprite tmpSprite, dumb;
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
		

		LightManager.init();
		batch = new SpriteBatch();
		render = new Box2DDebugRenderer();
		//LightManager.setWorld(WorldManager.getWorld("one"));
		WorldManager.getWorld("one").setContactListener(new CollisionReciever());
		player = new Player(new Vector2(1, 1));
		player.setFriction(0.1f);
		Texture tmp = new Texture(Gdx.files.internal("player15.png"));
		tmpRegion = new TextureRegion(tmp, tmp.getWidth() / 4, tmp.getHeight() / 4);
		tmpSprite = new Sprite(tmpRegion);
		player.attachNewSprite(tmpSprite);
		input = new InputReciever(player);
		Gdx.input.setInputProcessor(input);
		StringTokenizer t;
		t = new StringTokenizer("Anal, Beads");
		System.out.println(t.nextToken(" "));
		dumb = new Sprite(new Texture(Gdx.files.internal("dumb.png")));
		dumb.setAlpha(0);
		testZone();
	}
	
	public void testZone(){
	
		e = new ChainEntity(BodyType.StaticBody);
		for(float i = -10; i < 20; i+= .1f){
			e.addVertice(new Vector2(i, (float) (Math.sin(i) / 12)));
		}
		e.createBody("one");
		
		r = new Rope(1, 5);
		r.create(5, 1000, 1);
		l1 = LightCreator.createConeLight(new Vector2(3 , 3), Color.DARK_GRAY, 25f, false, 15, 15);
		l1.setColor(l1.getColor().r, l1.getColor().g, l1.getColor().b, 0);
		r.attachLight(l1, -1, -90);
		
		r2 = new Rope(1.5f, 5);
		r2.create(5, 1000, 1);
		l2 = LightCreator.createConeLight(new Vector2(2 , 4), Color.GREEN, 100f, false, 15, 360);
		l2.setColor(l2.getColor().r, l2.getColor().g, l2.getColor().b, 0);
		r.attachLight(l2, -1, -90);
		pussy = LightCreator.createPointLight(new Vector2(1.85f, .5f), Color.RED, 1, false);
		pussy.setColor(pussy.getColor().r, pussy.getColor().g, pussy.getColor().b, 0);
		
	}
	ConeLight c;
	int num = 0;
	float el;
	@Override
	public void render() {
		// Synch physics
		doPhysicsStep(Gdx.graphics.getDeltaTime() * WorldConstants.PHYSICS_SPEED);
		// Update entities
		EntityManager.update();
		el += Gdx.graphics.getDeltaTime();

		
		if(el > 3 && l2.getConeDegree()  > 5 && el < 7){
			l2.setColor(l2.getColor().r, l2.getColor().g, l2.getColor().b, el);
			l2.setConeDegree(360 - el * 100);
			l1.setColor(l1.getColor().r, l1.getColor().g, l1.getColor().b, l1.getColor().a - el / 50);
		
		}else{
			l1.setColor(l1.getColor().r, l1.getColor().g, l1.getColor().b, el / 2);
		}
		if(el > 10){
			
			l2.setConeDegree(l2.getConeDegree() - el / 100);
			l1.setConeDegree(l1.getConeDegree() - el / 100);
			l1.setColor(l1.getColor().r, l1.getColor().g, l1.getColor().b, l1.getColor().a - el / 50);
			l2.setColor(l2.getColor().r, l2.getColor().g, l2.getColor().b, l2.getColor().a - el / 50);
		}
		if(el > 15){
			dumb.setAlpha(1);
			pussy.setColor(pussy.getColor().r, pussy.getColor().g, pussy.getColor().b, 1);
			pussy.setPosition(2.5f, .75f);
			pussy.setDistance(.75f);
		}
		System.out.println("com.engine.main.core");
		// Update everything with scaled property
		camera.update();
		camera.position.set(player.getBody().getPosition().x, player.getBody().getPosition().y + 1, 0);
		//camera.position.set(player.getBody().getPosition(), 0);
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
		tmpSprite.setPosition(1, .1f);
		tmpSprite.setSize(1, 1);
		dumb.setPosition(2, .1f);
		dumb.setSize(1, 1);
		dumb.draw(batch);
		tmpSprite.draw(batch);
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
