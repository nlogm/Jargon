package com.engine.joints;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.engine.joints.attributes.JointType;
import com.engine.joints.attributes.JointedAttributes;

public class DistanceJoint extends JointedEntity{

	public DistanceJoint(String id) {
		super(id);
		joint = new DistanceJointDef();
		jointType = JointType.Distance;
	}

	
	/**
	 * @param attribute 's of joint
	 * @param info , values of attributes
	 */
	@Override
	public void setAttribute(Object[] info, JointedAttributes.DistanceAttributes... attribute) {
		super.setAttribute(info, attribute);
	}
	
	@Override
	@Deprecated
	public void setAttribute(Object[] info, JointedAttributes.PrismaticAttributes... attribute) {
		super.setAttribute(info, attribute);
	}
	
	@Override
	@Deprecated
	public void setAttribute(Object[] info, JointedAttributes.RevoluteAttributes... attribute) {
		super.setAttribute(info, attribute);
	}

	@Override
	@Deprecated
	public void setAttribute(Object[] info, JointedAttributes.RopeAttributes... attribute) {
		super.setAttribute(info, attribute);
	}
	
	@Override
	@Deprecated
	public void setAttribute(Object[] info, JointedAttributes.PulleyAttributes... attribute) {
		super.setAttribute(info, attribute);
	}
	
	/**
	 * Automatically assigns Object values to JointDef
	 */
	@Override
	public void assignAttributes() {
		super.assignAttributes();
		for(String key : jointAttributes.keySet()){
			if(key.equals(JointedAttributes.DistanceAttributes.Damping_Ratio.name())){
				((DistanceJointDef)joint).dampingRatio = ((Integer)jointAttributes.get(key));
				continue;
			}else if(key.equals(JointedAttributes.DistanceAttributes.Frequency_Hz.name())){
				((DistanceJointDef)joint).frequencyHz = ((Float)jointAttributes.get(key));
				continue;
			}else if(key.equals(JointedAttributes.DistanceAttributes.Length.name())){
				((DistanceJointDef)joint).length = ((Float)jointAttributes.get(key));
				continue;
			}else if(key.equals(JointedAttributes.DistanceAttributes.Local_Anchor_A.name())){
				((DistanceJointDef)joint).localAnchorA.set
				(new Vector2(((Vector2)jointAttributes.get(key)).x, ((Vector2)jointAttributes.get(key)).y));
				continue;
			}else if(key.equals(JointedAttributes.DistanceAttributes.Local_Anchor_B.name())){
				((DistanceJointDef)joint).localAnchorB.set
				(new Vector2(((Vector2)jointAttributes.get(key)).x, ((Vector2)jointAttributes.get(key)).y));
				continue;
			}
				
		}
		
	}
	
	

}
