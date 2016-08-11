package com.editor.listeners;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.demo.realms.Level;
import com.editor.box2D.constants.PlayerTuples;
import com.editor.box2D.entity.Player;

public class InputReciever implements InputProcessor{

	
	private Player player;
	// this one is gonna get deleted
	public InputReciever(Player player){
		this.player = player;
	}
	
	
	private Level currentLevel;
	
	public InputReciever(Player player, Level currentLevel){
		this.player = player;
		this.currentLevel = currentLevel;
	}
	public void setPlayer(Player current){
		this.player = current;
		System.out.println("set");
	}
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.A || keycode == Keys.LEFT)
			player.setMovementBoolean(PlayerTuples.MOVE_LEFT, true);
		
		if(keycode == Keys.D || keycode == Keys.RIGHT)
			player.setMovementBoolean(PlayerTuples.MOVE_RIGHT, true);
		
		if(keycode == Keys.W || keycode == Keys.UP){
			player.setMovementBoolean(PlayerTuples.TRIES_JUMP, true);
		}
		if(keycode == Keys.S || keycode == Keys.DOWN)
			player.setMovementBoolean(PlayerTuples.CAN_GO_DOWN, true);
		
		return false;
	}
	private boolean lifted;
	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.A || keycode == Keys.LEFT)
			player.setMovementBoolean(PlayerTuples.MOVE_LEFT, false);
		
		if(keycode == Keys.D || keycode == Keys.RIGHT){
			player.setMovementBoolean(PlayerTuples.MOVE_RIGHT, false);
		}
		
		if(keycode == Keys.W || keycode == Keys.UP){
			player.setMovementBoolean(PlayerTuples.TRIES_JUMP, false);
		}
		
		if(keycode == Keys.S || keycode == Keys.DOWN)
			player.setMovementBoolean(PlayerTuples.CAN_GO_DOWN, false);
		
		if(keycode == Keys.SHIFT_LEFT && currentLevel != null)
			currentLevel.switchRealm();
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
