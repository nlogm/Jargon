package com.engine.joints.attributes;


/**
 *========================================================
 *==  Tuples class or enums of types then the           ==
 *==  Method types                                      ==
 *== @author Luke Roche                                 ==
 *========================================================
                                                            */

public class JointedAttributes {
	
	
	/**
 *========================================================
 *==  Enum of the Distance joint with the cooresponding ==
 *==                     Method titles                  ==
 *==                                                    ==
 *========================================================
                                                            */
	public enum DistanceAttributes{
		Damping_Ratio,
		Frequency_Hz,
		Length,
		Local_Anchor_A,
		Local_Anchor_B
	}
		/**
 *========================================================
 *== Enum of the Prismatic joint with the cooresponding ==
 *==                     Method titles                  ==
 *==                                                    ==
 *========================================================
                                                            */
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
		/**
 *========================================================
 *==  Enum of the Revolute joint with the cooresponding ==
 *==                     Method titles                  ==
 *==                                                    ==
 *========================================================
                                                            */
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
		/**
 *========================================================
 *==    Enum of the Rope joint with the cooresponding   ==
 *==                     Method titles                  ==
 *==                                                    ==
 *========================================================
                                                            */
	public enum RopeAttributes{
		Local_Anchor_A,
		Local_Anchor_B,
		Max_Length
	}
		/**
 *========================================================
 *==   Enum of the Pulley joint with the cooresponding  ==
 *==                     Method titles                  ==
 *==                                                    ==
 *========================================================
                                                            */
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
