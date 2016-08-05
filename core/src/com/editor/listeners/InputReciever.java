package com.editor.listeners;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.editor.constants.PlayerTuples;
import com.editor.entity.Player;

public class InputReciever implements InputProcessor{

	
	private Player player;
	public InputReciever(Player player){
		this.player = player;
	}
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.A)
			player.setMovementBoolean(PlayerTuples.MOVE_LEFT, true);
		
		if(keycode == Keys.D)
			player.setMovementBoolean(PlayerTuples.MOVE_RIGHT, true);
		
		if(keycode == Keys.W)
			player.setMovementBoolean(PlayerTuples.JUMP, true);
		
		if(keycode == Keys.S)
			player.setMovementBoolean(PlayerTuples.TRY_DOWN, true);
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.A)
			player.setMovementBoolean(PlayerTuples.MOVE_LEFT, false);
		
		if(keycode == Keys.D)
			player.setMovementBoolean(PlayerTuples.MOVE_RIGHT, false);
		
		if(keycode == Keys.W)
			player.setMovementBoolean(PlayerTuples.JUMP, false);
		
		if(keycode == Keys.S)
			player.setMovementBoolean(PlayerTuples.TRY_DOWN, false);
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
