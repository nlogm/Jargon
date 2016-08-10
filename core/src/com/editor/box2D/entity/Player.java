package com.editor.box2D.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.editor.box2D.bodies.BodyCreator;
import com.editor.box2D.constants.BodyReferences;
import com.editor.box2D.constants.PlayerTuples;
import com.editor.box2D.entity.strategy.movement.MovementController;
import com.editor.listeners.collision.FixtureData;

public class Player extends Entity {

	private Array<Boolean> movementCommands;
	private MovementController movementController;
	private Array<Fixture> playerFixtures;
	
	
	public static int TOP_RIGHT_INDEX = 0;
	public static int TOP_LEFT_INDEX = 1;
	public static int BOTTOM_RIGHT_INDEX = 2;
	public static int BOTTOM_LEFT_INDEX = 3;
	public static int BODY = 4;
	
	public Player(Vector2 position) {
		super(position);

		this.dimensions = new Vector2(.1f, .1f);

		movementCommands = new Array<Boolean>();
		movementCommands.addAll(false, false, false, false, false, false, false, false);
		movementController = new MovementController(this);
		
		playerFixtures = new Array<Fixture>();

	}

	public void createBody(String worldKey) {
		bodyObjects = BodyCreator.createAndGetEntity(position, dimensions, type, false, worldKey);
	}

	public void createBody(World world) {
		bodyObjects = BodyCreator.createAndGetEntity(position, dimensions, type, false, world, FixtureData.PLAYER_MASK);
		((Body) bodyObjects.get(BodyReferences.BODY)).setAngularDamping(200);
		((Fixture) bodyObjects.get(BodyReferences.FIXTURE)).setUserData((int)FixtureData.PLAYER_MASK);
		playerFixtures.add(((Fixture) bodyObjects.get(BodyReferences.FIX_TOP_RIGHT)));
		playerFixtures.add(((Fixture) bodyObjects.get(BodyReferences.FIX_TOP_LEFT)));
		playerFixtures.add(((Fixture) bodyObjects.get(BodyReferences.FIX_BOTTOM_RIGHT)));
		playerFixtures.add(((Fixture) bodyObjects.get(BodyReferences.FIX_BOTTOM_LEFT)));
		playerFixtures.add(((Fixture) bodyObjects.get(BodyReferences.FIXTURE)));
	}
	private boolean transX = false;
	private boolean transY = false;
	private boolean goUp = false;
	public void update(){
		movementController.update();
		if(movementCommands.get(PlayerTuples.LEFT_FOOT_OFF) && movementCommands.get(PlayerTuples.RIGHT_FOOT_OFF)){
			movementCommands.set(PlayerTuples.CAN_JUMP, false);
		}else{
			movementCommands.set(PlayerTuples.CAN_JUMP, true);
		}
		
		
		if(movementCommands.get(PlayerTuples.IS_HANGING)){
			((Body)bodyObjects.get(BodyReferences.BODY)).setLinearVelocity(0, 0);
			((Body)bodyObjects.get(BodyReferences.BODY)).setGravityScale(0);
			movementCommands.set(PlayerTuples.CAN_JUMP, false);
			movementCommands.set(PlayerTuples.MOVE_LEFT, false);
			movementCommands.set(PlayerTuples.MOVE_RIGHT, false);
			if(Gdx.input.isKeyJustPressed(Keys.W))
				goUp = true;
			if(goUp){
			if(((Body)bodyObjects.get(BodyReferences.BODY)).getPosition().y < destination.y)
				((Body)bodyObjects.get(BodyReferences.BODY)).setLinearVelocity(0, 1);
			else
				transY = true;
			
			if(((Body)bodyObjects.get(BodyReferences.BODY)).getPosition().x < destination.x && transY)
				((Body)bodyObjects.get(BodyReferences.BODY)).setLinearVelocity(1, 0);
			else
				transX = true;
			
			if(transX && transY){
				movementCommands.set(PlayerTuples.IS_HANGING, false);
				transX = false;
				transY = false;
				((Body)bodyObjects.get(BodyReferences.BODY)).setGravityScale(1);
				goUp = false;
			}
		}

	}
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
	
	public void retainState(Player player){
		((Body) bodyObjects.get(BodyReferences.BODY)).setTransform(player.getBody().getPosition(), player.getBody().getAngle());
		((Body) bodyObjects.get(BodyReferences.BODY)).setLinearVelocity(player.getBody().getLinearVelocity());
	}

	public boolean getMovementBoolean(int index) {
		return movementCommands.get(index);
	}

	public void setMovementBoolean(int index, boolean cond) {
		movementCommands.set(index, cond);
	}
	
	public Array<Fixture> getFixtures(){
		return this.playerFixtures;
	}
	private Vector2 destination = new Vector2(0, 0);
	private float timeToClimb = 0;
	public void setDestination(Vector2 positionOfCornerOffset){
		destination = new Vector2(((Body) bodyObjects.get(BodyReferences.BODY)).getTransform().getPosition().x + positionOfCornerOffset.x, 
				((Body) bodyObjects.get(BodyReferences.BODY)).getTransform().getPosition().y + positionOfCornerOffset.y);
		System.out.println(destination);
	}
 	
}