package com.engine.joints.attributes;

public class JointedAttributes {
	
	public enum DistanceAttributes{
		Damping_Ratio,
		Frequency_Hz,
		Length,
		Local_Anchor_A,
		Local_Anchor_B
	}
	
	public enum PrismaticAttributes{
		Enable_Limit,
		Enable_Motor,
		Local_Anchor_A,
		Local_Anchor_B,
		Local_Axis_A,
		Lower_Translation,
		Max_Motor_Force,
		Motor_Speed,
		Reference_Angle,
		Upper_Translation
	}
	
	public enum RevoluteAttributes{
		Enable_Limit,
		Enable_Motor,
		Local_Anchor_A,
		Local_Anchor_B,
		Max_Motor_Torque,
		Motor_Speed,
		Reference_Angle,
		Upper_Angle,
		Lower_Angle
	}
	
	public enum RopeAttributes{
		Local_Anchor_A,
		Local_Anchor_B,
		Max_Length
	}
	
	public enum PulleyAttributes{
		Local_Anchor_A,
		Local_Anchor_B,
		Length_A,
		Length_B,
		Ground_Anchor_A,
		Ground_Anchor_B,
		Ratio
	}

}
