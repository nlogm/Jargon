package com.engine.joints.utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef;
import com.badlogic.gdx.utils.Array;
import com.editor.bodies.BodyCreator;
import com.editor.constants.Scaler;
import com.editor.managers.WorldManager;

public class Rope {
	
public void create(float length, float ropeLength) {
		Array<Body> bodies = new Array<Body>();
		//bodies.add(BodyCreator.createAndGet(WorldManager.getWorld("one"), 5, 5, length * 8, length / 2, BodyType.StaticBody, 0,
			//	0, 0, "RopeMain"));

		for (int i = 1; i < ropeLength; i++) {
		//	bodies.add(BodyCreator.createBox(WorldManager.getWorld("one"), 5, 5 - (((length * 2) / Scaler.PPM) * i), length,
			//		length * 2, BodyType.DynamicBody, .0001f, 0, 0, "RopeBody" + i));
			
			RopeJointDef rDef = new RopeJointDef();
			rDef.bodyA = bodies.get(0);
			rDef.bodyB = bodies.get(i);
			rDef.collideConnected = true;
			rDef.maxLength = i * (length / 4) / Scaler.PPM * 10;
			System.out.println("legnth: " + i * (length / 4) / Scaler.PPM * 10);
			System.out.println("legnth working: " + i * 0.05f);
			rDef.localAnchorA.set(0, -((length) / Scaler.PPM));
			rDef.localAnchorB.set(0, ((length) / Scaler.PPM));
			WorldManager.getWorld("one").createJoint(rDef);

			RevoluteJointDef jDef = new RevoluteJointDef();
			jDef.bodyA = bodies.get(i - 1);
			jDef.bodyB = bodies.get(i);
			jDef.localAnchorA.set(0, -((length) / Scaler.PPM));
			jDef.localAnchorB.set(0, ((length) / Scaler.PPM));
			WorldManager.getWorld("one").createJoint(jDef);
		}
	}


}
