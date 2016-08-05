package com.editor.entity.strategy.movement;

import com.editor.constants.PlayerTuples;
import com.editor.entity.Player;

public class Jump implements PlayerMovement{

	@Override
	public void move(Player player) {
		player.getBody().applyForceToCenter(0, 9.81f, true);
	}

	
}
