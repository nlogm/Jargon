package com.editor.box2D.entity.strategy.movement;

import com.editor.box2D.constants.PlayerTuples;
import com.editor.box2D.entity.Player;

public class MovementController {
	
	private Reciever moveLeft, moveRight, jump, down;
	private Player player;
	public MovementController(Player player){
		moveLeft = new Reciever(new MoveLeft());
		moveRight = new Reciever(new MoveRight());
		jump = new Reciever(new Jump());
		down = new Reciever(new Down());
		this.player = player;
	}
	
	public void update(){
		
		if(player.getMovementBoolean(PlayerTuples.TRIES_JUMP) && player.getMovementBoolean(PlayerTuples.CAN_JUMP)){	jump.execute(player); }
		if(player.getMovementBoolean(PlayerTuples.CAN_GO_DOWN)){ down.execute(player); }
		if(player.getMovementBoolean(PlayerTuples.MOVE_LEFT)){ moveLeft.execute(player); }
		if(player.getMovementBoolean(PlayerTuples.MOVE_RIGHT)){ moveRight.execute(player); }
		
	}

}
