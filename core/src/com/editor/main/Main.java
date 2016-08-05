package com.editor.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bootup.StartUp;
import com.editor.states.StateManager;

/**
 * File: Main.java
 * Purpose; - Used as the main entry point into the
 *          - Implements the functionality of StateManager.java
 * Last Edited: 7/30/2016 - 5:43 AM
 * @author: Douglas Rudolph
 */
public class Main extends ApplicationAdapter
{
    //StateManagner reference
    private StateManager stateManager;

    //LibGDX Object references
    private OrthographicCamera camera;
    private SpriteBatch spriteBatch;

    @Override
    public void create()
    {
        stateManager = new StateManager();
        stateManager.setState(new StartUp());

        camera = new OrthographicCamera();
        spriteBatch = new SpriteBatch();
    }

    @Override
    public void render()
    {
        //Set title to FPS
        Gdx.graphics.setTitle(Gdx.graphics.getFramesPerSecond() + "");

        //To be altered
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //clear frame buffer
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClearColor(0, 0, 0, 0);

        //update & render the currently selected state
        stateManager.updateCurrentState(Gdx.graphics.getDeltaTime());
        stateManager.renderCurrentState(spriteBatch);
    }

}
