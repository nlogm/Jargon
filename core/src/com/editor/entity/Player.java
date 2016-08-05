package com.editor.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.editor.bodies.BodyCreator;
import com.editor.entity.strategy.movement.MovementController;

public class Player extends Entity{
	
	private Array<Boolean> movementCommands;
	private MovementController movementController;
	public Player(Vector2 position){
		super(position);
		
		this.dimensions = new Vector2(.1f, .1f);
		
		movementCommands = new Array<Boolean>();
		movementCommands.addAll(false, false, false, false);
		movementController = new MovementController(this);
	
		createBody();

	}
	
	public void createBody(){
		bodyObjects = BodyCreator.createAndGet(position, dimensions, type, false, "one");
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
