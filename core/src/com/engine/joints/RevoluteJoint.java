package com.engine.joints;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.joints.PulleyJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.engine.joints.attributes.JointType;
import com.engine.joints.attributes.JointedAttributes;

public class RevoluteJoint extends JointedEntity {

	public RevoluteJoint(String id) {
		super(id);
		joint = new RevoluteJointDef();
		jointType = JointType.Revolute;
	}

	@Override
	public void setAttribute(Object[] info, JointedAttributes.RevoluteAttributes... attribute) {
		super.setAttribute(info, attribute);
	}

	@Override
	public void assignAttributes() {
		super.assignAttributes();

		for (String key : jointAttributes.keySet()) {

			if (key.equals(JointedAttributes.RevoluteAttributes.Enable_Limit.name())) {
				((RevoluteJointDef) joint).enableLimit = ((Boolean)jointAttributes.get(key));
				continue;
			}else if (key.equals(JointedAttributes.RevoluteAttributes.Enable_Motor.name())) {
				((RevoluteJointDef) joint).enableMotor = ((Boolean)jointAttributes.get(key));
				continue;
			}else if (key.equals(JointedAttributes.RevoluteAttributes.Local_Anchor_A.name())) {
				((RevoluteJointDef) joint).localAnchorA.set(((Vector2) jointAttributes.get(key)));
				continue;
			}else if (key.equals(JointedAttributes.RevoluteAttributes.Local_Anchor_B.name())) {
				((RevoluteJointDef) joint).localAnchorB.set(((Vector2) jointAttributes.get(key)));
				continue;
			}else if (key.equals(JointedAttributes.RevoluteAttributes.Lower_Angle.name())) {
				((RevoluteJointDef) joint).lowerAngle = ((Float) jointAttributes.get(key));
				continue;
			}else if (key.equals(JointedAttributes.RevoluteAttributes.Max_Motor_Torque.name())) {
				((RevoluteJointDef) joint).maxMotorTorque = ((Float) jointAttributes.get(key));
				continue;
			}else if (key.equals(JointedAttributes.RevoluteAttributes.Motor_Speed.name())) {
				((RevoluteJointDef) joint).motorSpeed = ((Float) jointAttributes.get(key));
				continue;
			}else if (key.equals(JointedAttributes.RevoluteAttributes.Reference_Angle.name())) {
				((RevoluteJointDef) joint).referenceAngle = ((Float) jointAttributes.get(key));
				continue;
			}else if (key.equals(JointedAttributes.RevoluteAttributes.Upper_Angle.name())) {
				((RevoluteJointDef) joint).upperAngle = ((Float) jointAttributes.get(key));
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
	public void setAttribute(Object[] info, JointedAttributes.PrismaticAttributes... attribute) {
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
