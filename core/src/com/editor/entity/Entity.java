package com.editor.entity;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Array;
import com.editor.bodies.BodyCreator;
import com.editor.constants.BodyReferences;
import com.editor.generation.EntityIDMaker;
import com.editor.managers.EntityManager;
import com.editor.managers.WorldManager;

public class Entity {

	protected Vector2 position, dimensions;
	protected float radius;

	protected BodyType type;

	protected Sprite entitySprite;

	protected HashMap<String, Object> bodyObjects = new HashMap<String, Object>();
	protected boolean fixtureDataSet;
	protected Vector2[] chainVerts;
	protected Array<Vector2> preLoadedVerts = new Array<Vector2>();
	/**
	 * For rectangle or square bodies
	 * 
	 * @param position
	 *            of body
	 * @param dimensions
	 *            of body
	 * @param type
	 *            of body (Kinematic, Static, Dynamic)
	 */
	public Entity(Vector2 positionInMeters, Vector2 dimensionsInMeters, BodyType type) {
		this.position = positionInMeters;
		this.dimensions = dimensionsInMeters;
		this.type = type;
		EntityManager.addEntity(this);

	}
	
	public Entity(BodyType type){
		this.type = type;
	}
	
	public void setChains(Vector2... chain){
		
		for(Vector2 vert : chain){
			preLoadedVerts.add(vert);
		}
	}
	
	public void addVertice(Vector2...additionalVertices){
		for(Vector2 vert : additionalVertices){
			preLoadedVerts.add(vert);
		}
	}
	
	public Entity(Vector2 position){
		this.position = position;
		this.type = BodyType.DynamicBody;
		EntityManager.addEntity(this);
	}

	/**
	 * For Circle bodies
	 * 
	 * @param position
	 *            of body
	 * @param radius
	 *            of body
	 * @param type
	 *            of body (Kinematic, Static, Dynamic)
	 */
	public Entity(Vector2 positionInMeters, float radius, BodyType type) {
		this.position = positionInMeters;
		this.radius = radius;
		this.type = type;
	}

	public void attachNewSprite(Sprite sprite) {
		entitySprite = sprite;
		((Body) bodyObjects.get(BodyReferences.BODY)).setUserData(entitySprite);
	}

	public void attachNewSprite(Texture tex) {
		entitySprite = new Sprite(tex);
		((Body) bodyObjects.get(BodyReferences.BODY)).setUserData(entitySprite);
	}

	public void attachNewSprite(String internalPath) {
		entitySprite = new Sprite(new Texture(Gdx.files.internal(internalPath)));
		((Body) bodyObjects.get(BodyReferences.BODY)).setUserData(entitySprite);
	}

	public void addFixtureDefProperties(float density, float friction, float restitution) {

		((Body) (bodyObjects.get(BodyReferences.BODY))).getFixtureList().first().setDensity(density);
		((Body) (bodyObjects.get(BodyReferences.BODY))).getFixtureList().first().setFriction(friction);
		((Body) (bodyObjects.get(BodyReferences.BODY))).getFixtureList().first().setRestitution(restitution);

	}

	public void setDensity(float density) {
		((Body) (bodyObjects.get(BodyReferences.BODY))).getFixtureList().first().setDensity(density);
	}

	public void setFriction(float friction) {
		((Body) (bodyObjects.get(BodyReferences.BODY))).getFixtureList().first().setFriction(friction);
	}

	public void setRestitution(float restitution) {
		((Body) (bodyObjects.get(BodyReferences.BODY))).getFixtureList().first().setFriction(restitution);
	}

	public void setAngleDegrees(float angleInDegrees) {
		((Body) (bodyObjects.get(BodyReferences.BODY))).setTransform(position, angleInDegrees * MathUtils.degreesToRadians);
	}
	public void setAngleRadian(float angleInRadians) {
		((Body) (bodyObjects.get(BodyReferences.BODY))).setTransform(position, angleInRadians);
	}
	
	public void setFixtureData(boolean isTriggerable){
		((Fixture) (bodyObjects.get(BodyReferences.FIXTURE))).setUserData(isTriggerable + "" + EntityIDMaker.generate());
		fixtureDataSet = true;
		System.out.println("ID of " + EntityManager.getEntities().size + ": " + ((Fixture) (bodyObjects.get(BodyReferences.FIXTURE))).getUserData());
	}

	public void setSize(Vector2 dimensionsInMeters) {
		this.dimensions = dimensionsInMeters;
	}

	public void setSize(float radiusInMeters) {
		this.dimensions.x = radiusInMeters;
	}
	
	public void trigger(){
		System.out.println("trigger activated");
	}

	public float getRadius() {
		return this.radius;
	};

	public Vector2 getDimensions() {
		return this.dimensions;
	}

	public Sprite getEntitySprite() {
		return this.entitySprite;
	}

	public BodyType getBodyType() {
		return this.type;
	}

	public Body getBody() {
		return ((Body) bodyObjects.get(BodyReferences.BODY));
	}
	public void assureFixtureData(){
		if(!fixtureDataSet)
			((Fixture) (bodyObjects.get(BodyReferences.FIXTURE))).setUserData(Boolean.toString(false) + "~" + EntityIDMaker.generate());
	}
	public void createBody(String worldHash) {
		WorldManager.getWorld(worldHash).createBody(((BodyDef) (bodyObjects.get(BodyReferences.BODY_DEF))));
	}
	
	public void createBody(Vector2[] vertices, String worldKey){
		WorldManager.getWorld(worldKey).createBody(((BodyDef) (bodyObjects.get(BodyReferences.BODY_DEF))));
	}
}
