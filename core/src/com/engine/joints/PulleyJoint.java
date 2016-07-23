package com.engine.joints;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import com.badlogic.gdx.physics.box2d.joints.PulleyJointDef;
import com.engine.joints.attributes.JointType;
import com.engine.joints.attributes.JointedAttributes;

public class PulleyJoint extends JointedEntity {

	public PulleyJoint(String id) {
		super(id);
		joint = new PulleyJointDef();
		jointType = JointType.Pulley;
	}

	@Override
	public void setAttribute(Object[] info, JointedAttributes.PulleyAttributes... attribute) {
		super.setAttribute(info, attribute);
	}

	@Override
	public void assignAttributes() {
		super.assignAttributes();

		for (String key : jointAttributes.keySet()) {

			if (key.equals(JointedAttributes.PulleyAttributes.Ground_Anchor_A.name())) {
				((PulleyJointDef) joint).groundAnchorA.set(((Vector2) jointAttributes.get(key)));
				continue;

			} else if (key.equals(JointedAttributes.PulleyAttributes.Ground_Anchor_B.name())) {
				((PulleyJointDef) joint).groundAnchorB.set(((Vector2) jointAttributes.get(key)));
				continue;

			}else if (key.equals(JointedAttributes.PulleyAttributes.Length_A.name())) {
				((PulleyJointDef) joint).lengthA = ((Float) jointAttributes.get(key));
				continue;

			}else if (key.equals(JointedAttributes.PulleyAttributes.Length_B.name())) {
				((PulleyJointDef) joint).lengthB = ((Float) jointAttributes.get(key));
				continue;

			}else if (key.equals(JointedAttributes.PulleyAttributes.Local_Anchor_A.name())) {
				((PulleyJointDef) joint).localAnchorA.set(((Vector2) jointAttributes.get(key)));
				continue;

			}else if (key.equals(JointedAttributes.PulleyAttributes.Local_Anchor_B.name())) {
				((PulleyJointDef) joint).localAnchorB.set(((Vector2) jointAttributes.get(key)));
				continue;

			}else if (key.equals(JointedAttributes.PulleyAttributes.Ratio.name())) {
				((PulleyJointDef) joint).ratio = ((Float) jointAttributes.get(key));
				continue;

			}

		}

	}

	@Override
	@Deprecated
	public void setAttribute(Object[] info, JointedAttributes.RopeAttributes... attribute) {
		super.setAttribute(info, attribute);
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

}
