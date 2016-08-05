package com.editor.workspace;

import com.badlogic.gdx.utils.Array;

public class Canvas
{

	private String fileName;
	private String saveDirectory;
	private Array<Layer> layerList;
	
	private Layer selectedLayer;

	public Canvas(String fileName, String saveDirectory)
	{
		this.fileName = fileName;
		this.saveDirectory = saveDirectory;
		selectedLayer = null;
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
