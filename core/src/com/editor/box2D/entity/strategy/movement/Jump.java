package com.editor.box2D.entity.strategy.movement;

import com.editor.box2D.entity.Player;

public class Jump implements PlayerMovement{

	@Override
	public void move(Player player) {
		player.getBody().applyForceToCenter(0, 9.81f, true);
	}

	
}
