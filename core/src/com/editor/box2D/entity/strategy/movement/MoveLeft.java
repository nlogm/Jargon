package com.editor.box2D.entity.strategy.movement;

import com.editor.box2D.constants.PlayerTuples;
import com.editor.box2D.entity.Player;

public class MoveLeft implements PlayerMovement{

	@Override
	public void move(Player player) {
		player.getBody().applyForceToCenter(-PlayerTuples.MOVEMENT_FORCE, 0, true);
	}

	
}
