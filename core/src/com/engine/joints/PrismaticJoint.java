package com.engine.joints;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import com.engine.joints.attributes.JointType;
import com.engine.joints.attributes.JointedAttributes;

public class PrismaticJoint extends JointedEntity {

	public PrismaticJoint(String id) {
		super(id);
		joint = new PrismaticJointDef();
		jointType = JointType.Prismatic;
	}

	@Override
	public void setAttribute(Object[] info, JointedAttributes.PrismaticAttributes... attribute) {
		super.setAttribute(info, attribute);
	}

	@Override
	public void assignAttributes() {
		super.assignAttributes();
		
		for (String key : jointAttributes.keySet()) {

			if(key.equals(JointedAttributes.PrismaticAttributes.Enable_Limit.name())){
				((PrismaticJointDef)joint).enableLimit = ((Boolean)jointAttributes.get(key));
				continue;
				
			}else if(key.equals(JointedAttributes.PrismaticAttributes.Enable_Motor.name())){
				((PrismaticJointDef)joint).enableMotor = ((Boolean)jointAttributes.get(key));
				continue;
				
			}else if(key.equals(JointedAttributes.PrismaticAttributes.Local_Anchor_A.name())){
				((PrismaticJointDef)joint).localAnchorA.set(((Vector2)jointAttributes.get(key)));
				continue;
				
			}else if(key.equals(JointedAttributes.PrismaticAttributes.Local_Anchor_B.name())){
				((PrismaticJointDef)joint).localAnchorB.set(((Vector2)jointAttributes.get(key)));
				continue;
				
			}else if(key.equals(JointedAttributes.PrismaticAttributes.Local_Axis_A.name())){
				((PrismaticJointDef)joint).localAxisA.set(((Vector2)jointAttributes.get(key)));
				continue;
				
			}else if(key.equals(JointedAttributes.PrismaticAttributes.Lower_Translation.name())){
				((PrismaticJointDef)joint).lowerTranslation = ((Float)jointAttributes.get(key));
				continue;
				
			}else if(key.equals(JointedAttributes.PrismaticAttributes.Max_Motor_Force.name())){
				((PrismaticJointDef)joint).maxMotorForce = ((Float)jointAttributes.get(key));
				continue;
				
			}else if(key.equals(JointedAttributes.PrismaticAttributes.Motor_Speed.name())){
				((PrismaticJointDef)joint).motorSpeed = ((Float)jointAttributes.get(key));
				continue;
				
			}else if(key.equals(JointedAttributes.PrismaticAttributes.Upper_Translation.name())){
				((PrismaticJointDef)joint).upperTranslation = ((Float)jointAttributes.get(key));
				continue;
				
			}else if(key.equals(JointedAttributes.PrismaticAttributes.Reference_Angle.name())){
				((PrismaticJointDef)joint).referenceAngle = ((Float)jointAttributes.get(key));
				continue;
				
			}
			
		}

	}

	@Override
	@Deprecated
	public void setAttribute(Object[] info, JointedAttributes.DistanceAttributes... attribute) {
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

}
