package com.editor.bodies;

import java.util.HashMap;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.editor.constants.BodyReferences;
import com.editor.managers.EntityManager;

public class BodyCreator {
	
	
	/**
	 * Returns HashMap of bodies
	 * @param position of body
	 * @param dimensions of body
	 * @param type of body
	 * @param isCircle ...
	 * @return the body created
	 */
	public static HashMap<String, Object> createAndGet(Vector2 position, Vector2 dimensions,
			BodyType type, boolean isCircle, String worldKey){
		HashMap<String, Object> bodyObjectsHash = new HashMap<String, Object>();
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = type;
		bodyDef.position.set(new Vector2(position.x, position.y));
		
		Body body = EntityManager.createBody(bodyDef, dimensions, worldKey);
		
		FixtureDef fixtureDef = new FixtureDef();
		Fixture fixture;
		if(isCircle){
			CircleShape circle = new CircleShape();
			circle.setRadius(dimensions.x);
			fixtureDef.shape = circle;
			fixtureDef.density = 5;
			fixtureDef.friction = .3f;
			fixtureDef.restitution = 0f;
			fixture = body.createFixture(fixtureDef);
			circle.dispose();
		}else{
			PolygonShape rectangle = new PolygonShape();
			rectangle.setAsBox(dimensions.x, dimensions.y);
			fixtureDef.shape = rectangle;
			fixtureDef.density = 5;
			fixtureDef.friction = .3f;
			fixtureDef.restitution = 0;
			fixture = body.createFixture(fixtureDef);
			rectangle.dispose();
		}
		
		bodyObjectsHash.put(BodyReferences.BODY, body);
		bodyObjectsHash.put(BodyReferences.BODY_DEF, bodyDef);
		bodyObjectsHash.put(BodyReferences.FIXTURE, fixture);
		bodyObjectsHash.put(BodyReferences.FIXTURE_DEF, fixtureDef);
		
		return bodyObjectsHash;
	}
	
	public static RevoluteJointDef createJoint(Body a, Body b, Vector2 anchorA, Vector2 anchorB){
		
		
		RevoluteJointDef joint = new RevoluteJointDef();
		joint.bodyA = a;
		joint.bodyB = b;
		joint.localAnchorA.set(anchorA);
		joint.localAnchorB.set(anchorB);
		
		return null;
		
	}

}
