package com.engine.joints;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.joints.PulleyJointDef;
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef;
import com.engine.joints.attributes.JointType;
import com.engine.joints.attributes.JointedAttributes;

public class RopeJoint extends JointedEntity {

	public RopeJoint(String id) {
		super(id);
		joint = new RopeJointDef();
		jointType = JointType.Rope;
	}

	@Override
	public void setAttribute(Object[] info, JointedAttributes.RopeAttributes... attribute) {
		super.setAttribute(info, attribute);
	}

	@Override
	public void assignAttributes() {
		super.assignAttributes();

		for (String key : jointAttributes.keySet()) {

			if (key.equals(JointedAttributes.RopeAttributes.Local_Anchor_A.name())) {
				((RopeJointDef) joint).localAnchorA.set(((Vector2) jointAttributes.get(key)));
				continue;

			} else if (key.equals(JointedAttributes.RopeAttributes.Local_Anchor_B.name())) {
				((RopeJointDef) joint).localAnchorB.set(((Vector2) jointAttributes.get(key)));
				continue;
			} else if (key.equals(JointedAttributes.RopeAttributes.Max_Length.name())) {
				((RopeJointDef) joint).maxLength = ((Float) jointAttributes.get(key));
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
	public void setAttribute(Object[] info, JointedAttributes.RevoluteAttributes... attribute) {
		super.setAttribute(info, attribute);
	}

	@Override
	@Deprecated
	public void setAttribute(Object[] info, JointedAttributes.PulleyAttributes... attribute) {
		super.setAttribute(info, attribute);
	}

}
