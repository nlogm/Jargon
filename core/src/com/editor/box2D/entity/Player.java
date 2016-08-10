package com.editor.box2D.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.editor.box2D.bodies.BodyCreator;
import com.editor.box2D.constants.BodyReferences;
import com.editor.box2D.entity.strategy.movement.MovementController;

public class Player extends Entity {

	private Array<Boolean> movementCommands;
	private MovementController movementController;
	
	public enum PlayerState{
		IS_FALLING,
		IS_HANGING
		
	}
	
	private PlayerState playerState;

	public Player(Vector2 position) {
		super(position);

		this.dimensions = new Vector2(.1f, .1f);

		movementCommands = new Array<Boolean>();
		movementCommands.addAll(false, false, false, false);
		movementController = new MovementController(this);

	}

	public void createBody(String worldKey) {
		bodyObjects = BodyCreator.createAndGetEntity(position, dimensions, type, false, worldKey);
	}

	public void createBody(World world) {
		bodyObjects = BodyCreator.createAndGetEntity(position, dimensions, type, false, world);
		((Body) bodyObjects.get(BodyReferences.BODY)).setAngularDamping(200);
	}

	public void update(){
		movementController.update();
		
		
		//How we control flipping of player  |
		//								     v
		
		 /*float desiredAngle = 0;
		 float angleNow = body->GetAngle();
		 float changeExpected = body->GetAngularVelocity() * timeStepLengthInSeconds; //expected angle change in next timestep
		 float angleNextStep = angleNow + changeExpected;
		 float changeRequiredInNextStep = desiredAngle - angleNextStep;
		 float rotationalAcceleration = timeStepsPerSecond * changeRequiredInNextStep;
		 float torque = rotationalAcceleration * torqueAdjustment;
		 if ( torque > maximumPossibleTorque )
		    torque = maximumPossibleTorque;
		 body->ApplyTorque(torque);*/
	}

	public boolean getMovementBoolean(int index) {
		return movementCommands.get(index);
	}

	public void setMovementBoolean(int index, boolean cond) {
		movementCommands.set(index, cond);
	}
	
	public void setState(PlayerState state){
		this.playerState = state;
	}
	
	public PlayerState getState(){ return this.playerState; }
}
