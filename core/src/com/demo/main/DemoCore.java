package com.demo.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.demo.realms.Level;
import com.editor.box2D.constants.Scaler;
import com.engine.filemanager.parser.LVLParser;

/**
 * The core file for the Demo
 * 
 * @author Luke Roche 9/19/2016
 */

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
	
	/**
	 * Gives Doug the ability to modify the camera
	 * to check the level parsing
	 */
	public void cameraControllerForDoug(float speed){
		if(Gdx.input.isKeyPressed(Keys.I)){
			camera.translate(0, speed);
		}
		if(Gdx.input.isKeyPressed(Keys.K)){
			camera.translate(0, -speed);
		}
		if(Gdx.input.isKeyPressed(Keys.J)){
			camera.translate(-speed, 0);
		}
		if(Gdx.input.isKeyPressed(Keys.L)){
			camera.translate(speed, 0);
		}
		if(Gdx.input.isKeyPressed(Keys.U)){
			camera.zoom += speed / 10.0f;
		}
		if(Gdx.input.isKeyPressed(Keys.O)){
			camera.zoom -= speed / 10.0f;
		}
		
	}

	@Override
	public void render() {
		// ===============================
		// ===========Update==============
		// ===============================
		camera.update();
		cameraControllerForDoug(0.1f);
		
		
		demoLevel.update(camera);

		// Set title to FPS
		Gdx.graphics.setTitle(Gdx.graphics.getFramesPerSecond() + "");

		demoLevel.render(camera);
		// ===============================
		// ===========Render==============
		// ===============================

	}

}
