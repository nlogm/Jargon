package com.editor.workspace.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Array;
import com.editor.workspace.EditorConstants;
import com.editor.workspace.gui.layers.Layer;

/**
 * File: Canvas.java
 * Last Edited: 8/5/2016 - 4:31 AM
 * Purpose: Used as a collective object to manage images, lights, and fixtures within a level editor
 * @author: Douglas Rudolph
 */
public class Canvas
{
	private String fileName;
	private String saveDirectory;
	private Array<Layer> layerList;
	
	private Layer selectedLayer;



	public Canvas(Stage stage)
	{
		Window layerWdo = new Window("Canvas", EditorConstants.defaultSkin);
		layerWdo.setSize(Gdx.graphics.getWidth()*2/3,
				Gdx.graphics.getHeight()- Gdx.graphics.getHeight()/5);
		layerWdo.setPosition(0,0);

		stage.addActor(layerWdo);

		this.saveDirectory = saveDirectory;
		selectedLayer = null;
		this.fileName = fileName;
		layerList = new Array<Layer>();
	}

	//TODO: Add toolbox and Tool object to store the selected tool

	public void addImage(String imageDir)
	{

	}

	public void addAudio(String audioDir)
	{
	
	}

	public void addLight()
	{
			
	}

	/*
	public void swapTool(Tool newTool)
	{
		//For when tools are added
	}
	*/

	public void dispose()
	{
	
	}
	
}
