package com.editor.box2D.entity.strategy.movement;

import com.editor.box2D.entity.Player;

public class Reciever {
	
	private PlayerMovement command;
	
	public Reciever(PlayerMovement movement){
		this.command = movement;
	}
	
	public void execute(Player player){
		command.move(player);
	}

}
