package com.engine.joints.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef;
import com.badlogic.gdx.utils.Array;
import com.editor.box2D.WorldManager;
import com.editor.box2D.bodies.BodyCreator;

import box2dLight.ConeLight;
import box2dLight.PointLight;

public class Rope {

	private Vector2 position;
	private Array<Body> bodies;
	public Rope(float topX, float topY) {
		position = new Vector2(topX, topY);
	}

	public void create(float ropeLength, float angularDamping, float linearDamping) {
		float length = .015f;
		float widthToHeightRatio = 2;
		bodies = new Array<Body>();
		//anchor
		bodies.add(BodyCreator.createBody(position.x, position.y, length, length / widthToHeightRatio, BodyType.StaticBody));
		
		
		for (int i = 1; i < ropeLength; i++) {
			bodies.add(BodyCreator.createBody(position.x, position.y - (length * widthToHeightRatio * i), length, length * widthToHeightRatio,
					BodyType.DynamicBody));
			bodies.get(bodies.size - 1).setAngularDamping(angularDamping);
			bodies.get(bodies.size - 1).setLinearDamping(linearDamping);
			
			RopeJointDef rDef = new RopeJointDef();
			rDef.bodyA = bodies.get(0);
			rDef.bodyB = bodies.get(i);
			
			rDef.collideConnected = false;
			
			rDef.maxLength = i * (length * widthToHeightRatio * 2);
			
			rDef.localAnchorA.set(0, -((length * widthToHeightRatio)));
			rDef.localAnchorB.set(0, ((length * widthToHeightRatio)));
			
			WorldManager.getWorld("one").createJoint(rDef);

			RevoluteJointDef jDef = new RevoluteJointDef();
			jDef.bodyA = bodies.get(i - 1);
			jDef.bodyB = bodies.get(i);
			jDef.collideConnected = false;
			jDef.localAnchorA.set(0, -((length * widthToHeightRatio)));
			jDef.localAnchorB.set(0, ((length * widthToHeightRatio)));
			WorldManager.getWorld("one").createJoint(jDef);
		}
	}

	
	/**
	 * -1 to attach to end
	 * @param light
	 * @param index
	 */
	public void attachLight(PointLight light, int index){
		if(index >= 0)
			light.attachToBody(bodies.get(0));
		else
			light.attachToBody(bodies.get(bodies.size - 1));
	}
	
	/**
	 * -1 to attach to end
	 * @param light
	 * @param index
	 */
	public void attachLight(ConeLight light, int index, float angleOffset){
		if(index >= 0)
			light.attachToBody(bodies.get(0), 0, 0, angleOffset);
		else
			light.attachToBody(bodies.get(bodies.size - 1), 0, 0, angleOffset);
	}
	
}
