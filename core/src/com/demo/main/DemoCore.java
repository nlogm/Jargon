package com.demo.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.demo.realms.Level;
import com.editor.box2D.constants.Scaler;
import com.engine.filemanager.parser.LVLParser;

public class DemoCore extends ApplicationAdapter {

	// LibGDX Object references
	private OrthographicCamera camera;
	private SpriteBatch spriteBatch;
	// Level stuff
	private Level demoLevel;
	
	private LVLParser parse;
	@Override
	public void create() {

		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth() / Scaler.PPM, Gdx.graphics.getHeight() / Scaler.PPM);
		spriteBatch = new SpriteBatch();

		demoLevel = new Level();

		parse = new LVLParser();
		
		parse.load("C:\\Users\\Owner\\Downloads\\example.lvl");
		

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
