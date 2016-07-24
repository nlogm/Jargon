package com.editor.states;

/**
 * File: StateManager.java
 * Purpose: Used to manage what state is loaded on screen
 *	- The functionality of StateManager is only accessible from inside of each concrete State and 
 *    Core.java {@link Core.java} 
 * Last Edited: 7/24/2016 - 1:12 am
 * @author: Douglas Rudolph
 */
public class StateManager
{
	
	//Reference to current State on screen
	private static State currentState;
	
	/**
	 * Default Constructor 
	 */
	public StateManger(){ }

	/**
	 * Allows for the current state on screen to be changed. 
	 */
	public setState(State newState)
	{
		//if the first state is being loaded
		if(currentState == null)
		{
			//load the first state
			currentState = newState;
			currentState.create();
		}

		//otherwise, if there is already a state loaded to the screen
		else
		{
			//dispose old state 
			oldSstate = currentState;
			oldState.dispose();
			oldState.hide();
			
			//load new state
			currentState = newState;
			currentState.create();
		}
	}

	/**
	 * Updates currentState
	 */
	public void updateCurrentState(float delta)
	{
		currentState.update(delta);
	}

	/**
	 * Renders currentState
	 */
	public void renderCurrentState(SpriteBatch spriteBatch)
	{
		currentState.render(spriteBatch);
	}
	
}
