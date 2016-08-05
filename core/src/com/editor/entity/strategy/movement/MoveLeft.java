package com.editor.entity.strategy.movement;

import com.editor.constants.PlayerTuples;
import com.editor.entity.Player;

public class MoveLeft implements PlayerMovement{

	@Override
	public void move(Player player) {
		player.getBody().applyForceToCenter(-PlayerTuples.MOVEMENT_FORCE, 0, true);
	}

	
}
