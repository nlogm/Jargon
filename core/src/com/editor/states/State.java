package com.editor.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.editor.managers.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * File: State.java
 * Last Edited: 7/23/2016 : 11:19 pm
 * Purpose: Used to abstract the different states that can be loaded.
 *	- Each state can be loaded into the editor workspace,
 *	  the viewing menu, or the game viewer
 * @author Douglas Rudolph
 */
public abstract class State
{
    //There exists one universal ref to StateManager among all states.
    protected static StateManager stateManager;

    //The orthographicCamera will be used to render the state depending on
    //what sub system is calling the state
    protected OrthographicCamera camera;

    //The AssetManager will be used to load in assets that exists on each state
    protected AssetManager assetManager;

    //stores if the state is being referenced by the workspace, viewing menu, or the game viewer
    public enum SUB_STATE{GAME_VIEW, MENU_VIEW, EDITOR_VIEW};

    protected SUB_STATE currentSubState;

    //stores the directory of where the level file will be saved
    protected String saveDirectory;

    /**
     * Defualt Constructor
     */
    public State(){ }

    /**
     * Contstructor that allows for the first state to be set
     */
    public State(SUB_STATE state)
    {
        setSubState(state);
    }

    //loads the state
    public abstract void create();

    //updates the state
    public abstract void update(float delta);

    //renders the state
    public abstract void render(SpriteBatch batch);

    //disposes the state
    public abstract void dispose();

    //plays the animation while the objects are being disposed
    public abstract void hide();

    /**
     * changes the reference as to what sub-system is using this state
     */
    public void setSubState(SUB_STATE state)
    {
        currentSubState = state;
    }

    /**
     * @return: the current sub system that the state is in
     */
    public SUB_STATE getSubState()
    {
        return currentSubState;
    }

    /**
     * Changes the save directory of where a sub-system references the file
     */
    public void setSaveDirectory(String newSaveDirectory)
    {
        this.saveDirectory = newSaveDirectory;
    }

    /**
     * @return: the diretory of where state should be saved
     */
    public String getSaveDirectory()
    {
        return saveDirectory;
    }

}