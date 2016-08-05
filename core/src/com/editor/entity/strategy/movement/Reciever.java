package com.editor.entity.strategy.movement;

import com.editor.entity.Player;

public class Reciever {
	
	private PlayerMovement command;
	
	public Reciever(PlayerMovement movement){
		this.command = movement;
	}
	
	public void execute(Player player){
		command.move(player);
	}

}
