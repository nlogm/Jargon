package com.engine.joints;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.utils.Array;
import com.engine.joints.attributes.JointedAttributes;

public class JointedEntity {

	protected HashMap<String, Object> jointList;
	protected HashMap<String, Object> jointAttributes;
	protected JointDef joint;
	protected Array<Sprite> spriteDistro;
	protected Array<String> jointIDs;
	protected HashMap<String, Body> bodyList;
	protected String jointID;
	public JointedEntity(String id){
		this.jointID = id;
	}
	
	public void setAttribute(JointedAttributes.DistanceAttributes attribute, Object info){
		switch(attribute){
		
		case Length:{
			if(Float.class.isAssignableFrom(info.getClass()))
				jointAttributes.put(attribute.name(), (Float) info);
			else{
				//Gdx.app.
			}
				
			break;
		}
		default:
			break;
		
		}
	}
	
	
	
}
