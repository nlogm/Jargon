package com.engine.joints;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.utils.Array;
import com.engine.joints.attributes.JointType;
import com.engine.joints.attributes.JointedAttributes;

public class JointedEntity {

	protected HashMap<String, Object> jointList;
	protected HashMap<String, Object> jointAttributes;
	protected JointDef joint;
	protected Array<Sprite> spriteDistro;
	protected Array<String> jointIDs;
	protected HashMap<String, Body> bodyList;
	protected String jointID;
	protected JointType jointType;

	public JointedEntity(String id) {
		this.jointID = id;
		jointAttributes = new HashMap<String, Object>();
	}

	public void setAttribute(Object[] info, JointedAttributes.DistanceAttributes... attribute) {
		int count = 0;
		for (JointedAttributes.DistanceAttributes attr : attribute) {
			//if(Vector2.class.isAssignableFrom(info))
			jointAttributes.put(attr.name(), info[count++]);
		}
	}
	
	public void setAttribute(Object[] info, JointedAttributes.PrismaticAttributes... attribute) {
		for (JointedAttributes.PrismaticAttributes attr : attribute) {
			jointAttributes.put(attr.name(), info);
		}
	}
	
	public void setAttribute(Object[] info, JointedAttributes.RevoluteAttributes... attribute) {
		for (JointedAttributes.RevoluteAttributes attr : attribute) {
			jointAttributes.put(attr.name(), info);
		}
	}
	
	public void setAttribute(Object[] info, JointedAttributes.RopeAttributes... attribute) {
		for (JointedAttributes.RopeAttributes attr : attribute) {
			jointAttributes.put(attr.name(), info);
		}
	}
	
	public void setAttribute(Object[] info, JointedAttributes.PulleyAttributes... attribute) {
		for (JointedAttributes.PulleyAttributes attr : attribute) {
			jointAttributes.put(attr.name(), info);
		}
	}
	
	public void addBodyA(Body bodyA){
		joint.bodyA = bodyA;
	}
	public void addBodyB(Body bodyB){
		joint.bodyB = bodyB;
	}
	public void isCollideConnected(boolean condition){
		joint.collideConnected = condition;
	}
	
	public void assignAttributes(){
		
	}
	public void seek(String... name1) {
		System.out.println(name1);
		System.out.println(name1.toString());
	}
	
	public JointDef getJointDef(){
		return joint;
	}

}
