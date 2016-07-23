package com.editor.workspace;

import com.editor.constants.WorldConstants;
import com.badlogic.gdx.graphics.OrthographicCamera;
import box2dLight.RayHandler;
import com.editor.managers.WorldManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
public class Layer
{
	private String name;
	private boolean renderFlag;
	private Array<Texture> texList;

	private Box2DDebugRenderer dRenderer;
	private OrthographicCamera camera;
	private static RayHandler rayHandler;
	private static WorldManager worldManager;

	public Layer(String name, WorldManager worldManager, OrthographicCamera camera)
	{
		renderFlag = false;
		texList = new Array<Texture>();

		this.dRenderer = new Box2DDebugRenderer();
		this.camera = camera;
		this.name = name;
		this.worldManager = worldManager;
	}

	public void update()
	{
		if(renderFlag)
		{
			worldManager.getWorld(name).step(WorldConstants.TIME_STEP, WorldConstants.VELOCITY_ITERATIONS, WorldConstants.POSITION_ITERATIONS);		
			rayHandler.update();
		}
	}

	public void render()
	{
		if(renderFlag)
		{
			dRenderer.render(worldManager.getWorld(name), camera.combined);
			rayHandler.render();
		}
	}

	public void dispose()
	{
		rayHandler.dispose();
		dRenderer.dispose();
	}

	public void toggleRenderFlag()
	{
		if(renderFlag)
			renderFlag = true;
		else
			renderFlag = false;
	}


}
