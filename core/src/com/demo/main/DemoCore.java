package com.demo.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.demo.realms.Level;
import com.editor.box2D.constants.Scaler;
import com.editor.listeners.InputReciever;

public class DemoCore extends ApplicationAdapter {

	// LibGDX Object references
	private OrthographicCamera camera;
	private SpriteBatch spriteBatch;
	// Level stuff
	private Level demoLevel;
	
	
	@Override
	public void create() {

		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth() / Scaler.PPM, Gdx.graphics.getHeight() / Scaler.PPM);
		spriteBatch = new SpriteBatch();

		demoLevel = new Level();

		

	}

	@Override
	public void render() {
		// ===============================
		// ===========Update==============
		// ===============================
		camera.update();
		
		
		demoLevel.update(camera);

		// Set title to FPS
		Gdx.graphics.setTitle(Gdx.graphics.getFramesPerSecond() + "");

		demoLevel.render(camera);
		// ===============================
		// ===========Render==============
		// ===============================

	}

}
