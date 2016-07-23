package com.editor.entity;

import java.util.HashMap;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.editor.managers.WorldManager;

public class JointedHuman {
	
	private Vector2 position, dimensions;
	private CircleEntity head;
	private BoxEntity body, leftArm, leftForeArm, rightArm, rightForeArm, leftLeg, rightLeg;
	private HashMap<String, BoxEntity> objectList;
	private float headRadius;
	private RevoluteJointDef headToBody, leftArmToBody, rightArmToBody, leftLegToBody, rightLegToBody, leftArmJoint, rightArmJoint; 
	private BodyType type = BodyType.DynamicBody;
	public JointedHuman(Vector2 position, Vector2 dimensions){
		this.position = position;
		this.dimensions = dimensions;
		headRadius = dimensions.y / 7.5f;
	}
	
	public void create(){
		/*
		head = new CircleEntity(position, headRadius, type);
		head.createBody();
		
		float torsoWidth = dimensions.x / 1.5f;
		float torsoHeight = dimensions.y / 3.5f;
		body = new BoxEntity(new Vector2(position.x, position.y - headRadius * 2),
				new Vector2(torsoWidth, torsoHeight), type);
		body.createBody();
		body.addFixtureDefProperties(10, .3f, 0);
		head.addFixtureDefProperties(10, .3f, 0);
		headToBody = new RevoluteJointDef();
		headToBody.maxMotorTorque = 100f;
		headToBody.bodyA = head.getBody();
		headToBody.bodyB = body.getBody();
		headToBody.collideConnected = true;
		headToBody.localAnchorA.set(0, -headRadius);
		headToBody.localAnchorB.set(0, dimensions.y / 3.5f);
		WorldManager.world.createJoint(headToBody);
		
		float armHeight = torsoHeight / 1.25f;
		leftArm = new BoxEntity(new Vector2(position.x - dimensions.x / 2, position.y - headRadius * 2),
				new Vector2(torsoWidth / 4, armHeight / 1.5f), type);
		leftArm.createBody();
		
		rightArm = new BoxEntity(new Vector2(position.x + dimensions.x / 2, position.y - headRadius * 2),
				new Vector2(torsoWidth / 4, armHeight / 1.5f), type);
		rightArm.createBody();
		
		leftArmToBody = new RevoluteJointDef();
		leftArmToBody.bodyA = leftArm.getBody();
		leftArmToBody.bodyB = body.getBody();
		//leftArmToBody.upperAngle = MathUtils.degreesToRadians * 90;
		//leftArmToBody.lowerAngle = MathUtils.degreesToRadians * 270;
		leftArmToBody.collideConnected = false;
		leftArmToBody.localAnchorA.set(0, armHeight / 1.3f);
		leftArmToBody.localAnchorB.set(-torsoWidth, torsoHeight);
		WorldManager.world.createJoint(leftArmToBody);
		
		rightArmToBody = new RevoluteJointDef();
		rightArmToBody.bodyA = rightArm.getBody();
		rightArmToBody.bodyB = body.getBody();
		//leftArmToBody.upperAngle = MathUtils.degreesToRadians * 90;
		//leftArmToBody.lowerAngle = MathUtils.degreesToRadians * 270;
		rightArmToBody.collideConnected = false;
		rightArmToBody.localAnchorA.set(0, armHeight / 1.3f);
		rightArmToBody.localAnchorB.set(torsoWidth, torsoHeight);
		WorldManager.world.createJoint(rightArmToBody);
		
		
		
		leftForeArm = new BoxEntity(new Vector2(position.x - dimensions.x / 2, position.y - headRadius * 2),
				new Vector2(torsoWidth / 4, armHeight / 1.5f), type);
		leftForeArm.createBody();
		
		
		leftArmJoint = new RevoluteJointDef();
		leftArmJoint.bodyA = leftArm.getBody();
		leftArmJoint.bodyB = leftForeArm.getBody();
		leftArmJoint.collideConnected = true;
		leftArmJoint.localAnchorA.set(0, -armHeight / 1.4f);
		leftArmJoint.localAnchorB.set(0, armHeight / 1.4f);
		
		WorldManager.world.createJoint(leftArmJoint);
		
		
		rightForeArm = new BoxEntity(new Vector2(position.x + dimensions.x / 2, position.y - headRadius * 2),
				new Vector2(torsoWidth / 4, armHeight / 1.5f), type);
		rightForeArm.createBody();
		
		rightArmJoint = new RevoluteJointDef();
		rightArmJoint.bodyA = rightArm.getBody();
		rightArmJoint.bodyB = rightForeArm.getBody();
		rightArmJoint.collideConnected = false;
		rightArmJoint.localAnchorA.set(0, -armHeight / 1.4f);
		rightArmJoint.localAnchorB.set(0, armHeight / 1.4f);
		
		WorldManager.world.createJoint(rightArmJoint);
		
		
		rightLeg = new BoxEntity(new Vector2(position.x + dimensions.x / 2, position.y - torsoHeight * 2 - headRadius),
				new Vector2(torsoWidth / 4, armHeight / 1.5f), type);
		rightLeg.createBody();
		
		rightLegToBody = new RevoluteJointDef();
		rightLegToBody.bodyA = rightLeg.getBody();
		rightLegToBody.bodyB = body.getBody();
		rightLegToBody.enableLimit = true;
		rightLegToBody.lowerAngle = 135 * MathUtils.degreesToRadians;
		rightLegToBody.upperAngle = 180 * MathUtils.degreesToRadians;
		rightLegToBody.collideConnected = false;
		rightLegToBody.localAnchorA.set(0, -armHeight / 1.4f);
		rightLegToBody.localAnchorB.set(torsoWidth - torsoWidth / 4, -torsoHeight);
		WorldManager.world.createJoint(rightLegToBody);
		
		leftLeg = new BoxEntity(new Vector2(position.x - dimensions.x / 2, position.y - torsoHeight * 2 - headRadius),
				new Vector2(torsoWidth / 4, armHeight / 1.5f), type);
		leftLeg.createBody();
		
		leftLegToBody = new RevoluteJointDef();
		leftLegToBody.bodyA = leftLeg.getBody();
		leftLegToBody.bodyB = body.getBody();
		leftLegToBody.enableLimit = true;
		leftLegToBody.lowerAngle = 90 * MathUtils.degreesToRadians;
		leftLegToBody.upperAngle = 90 * MathUtils.degreesToRadians;
		leftLegToBody.collideConnected = false;
		leftLegToBody.localAnchorA.set(0, -armHeight / 1.4f);
		leftLegToBody.localAnchorB.set(-torsoWidth + torsoWidth / 4, -torsoHeight);
		WorldManager.world.createJoint(leftLegToBody);
		*/
	}

}
