package com.engine.joints;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.utils.Array;
import com.engine.joints.attributes.JointedAttributes;
import com.engine.joints.attributes.JointedAttributes.DistanceAttributes;
import com.engine.joints.attributes.JointedAttributes.PrismaticAttributes;
import com.engine.joints.attributes.JointedAttributes.PulleyAttributes;
import com.engine.joints.attributes.JointedAttributes.RevoluteAttributes;
import com.engine.joints.attributes.JointedAttributes.RopeAttributes;

public class JointedEntity {

	protected HashMap<String, Object> jointList;
	protected HashMap<String, Object> jointAttributes;
	protected JointDef joint;
	protected Array<Sprite> spriteDistro;
	protected Array<String> jointIDs;
	protected HashMap<String, Body> bodyList;
	protected String jointID;

	public JointedEntity(String id) {
		this.jointID = id;
		jointAttributes = new HashMap<String, Object>();
	}

	public void setAttribute(Object[] info, JointedAttributes.DistanceAttributes... attribute) {
		for (JointedAttributes.DistanceAttributes attr : attribute) {
			jointAttributes.put(attr.name(), info);
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
	
	public void assignAttributes(){
		
	}
	public void seek(String... name1) {
		System.out.println(name1);
		System.out.println(name1.toString());
	}

}
