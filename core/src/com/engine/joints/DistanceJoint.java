package com.engine.joints;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.engine.joints.attributes.JointedAttributes;
import com.engine.joints.attributes.JointedAttributes.DistanceAttributes;
import com.engine.joints.attributes.JointedAttributes.PrismaticAttributes;

public class DistanceJoint extends JointedEntity{

	public DistanceJoint(String id) {
		super(id);
		joint = new DistanceJointDef();
	}

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
	
	
	@Override
	public void assignAttributes() {
		super.assignAttributes();
		for(String key : jointAttributes.keySet()){
			if(key.equals(JointedAttributes.DistanceAttributes.Damping_Ratio.name())){
				((DistanceJointDef)joint).dampingRatio = ((Float)jointAttributes.get(key));
				continue;
			}else if(key.equals(JointedAttributes.DistanceAttributes.Frequency_Hz.name())){
				((DistanceJointDef)joint).frequencyHz = ((Float)jointAttributes.get(key));
				continue;
			}else if(key.equals(JointedAttributes.DistanceAttributes.Length.name())){
				((DistanceJointDef)joint).length = ((Float)jointAttributes.get(key));
				continue;
			}else if(key.equals(JointedAttributes.DistanceAttributes.Local_Anchor_A.name())){
				((DistanceJointDef)joint).localAnchorA.set(((Vector2)jointAttributes.get(key)));
				continue;
			}else if(key.equals(JointedAttributes.DistanceAttributes.Local_Anchor_B.name())){
				((DistanceJointDef)joint).localAnchorB.set(((Vector2)jointAttributes.get(key)));
				continue;
			}
				
		}
		
	}
	
	

}
