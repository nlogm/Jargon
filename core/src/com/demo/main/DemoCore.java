package com.demo.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bootup.StartUp;
import com.editor.states.StateManager;

public class DemoCore extends ApplicationAdapter {

	// StateManagner reference
	private StateManager stateManager;

	// LibGDX Object references
	private OrthographicCamera camera;
	private SpriteBatch spriteBatch;

	@Override
	public void create() {

		camera = new OrthographicCamera();
		spriteBatch = new SpriteBatch();
	}

	@Override
	public void render() {
		//===============================
		//===========Update==============
		//===============================
		
		// Set title to FPS
		Gdx.graphics.setTitle(Gdx.graphics.getFramesPerSecond() + "");

		
		
		
		//===============================
		//===========Render==============
		//===============================

	}

}
