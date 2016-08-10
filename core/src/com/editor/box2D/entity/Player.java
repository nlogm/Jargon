package com.editor.box2D.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.editor.box2D.bodies.BodyCreator;
import com.editor.box2D.entity.strategy.movement.MovementController;

public class Player extends Entity{
	
	private Array<Boolean> movementCommands;
	private MovementController movementController;
	public Player(Vector2 position){
		super(position);
		
		this.dimensions = new Vector2(.1f, .1f);
		
		movementCommands = new Array<Boolean>();
		movementCommands.addAll(false, false, false, false);
		movementController = new MovementController(this);
	

	}
	
	public void createBody(String worldKey){
		bodyObjects = BodyCreator.createAndGetEntity(position, dimensions, type, false, worldKey);
	}
	
	public void createBody(World world){
		bodyObjects = BodyCreator.createAndGetEntity(position, dimensions, type, false, world);
	}
	
	public void update(){
		movementController.update();
	}
	
	
	public boolean getMovementBoolean(int index){
		return movementCommands.get(index);
	}
	public void setMovementBoolean(int index, boolean cond){
		movementCommands.set(index, cond);
	}
}
