package com.editor.managers;

import java.util.HashMap;
import java.util.Set;
import com.badlogic.gdx.graphics.GL.Texture;

public AssetManager
{

	private static HashMap<String, Texture> textures= new HashMap<String, Texture>();

	public void addTexture(String key, Texture tex)
	{
		textures.put(key, tex);
	}

	public Textue getTexture(String key)
	{
		textures.get(key);
	}

	public Set getKeySet()
	{
		return textures.keySet();
		
	}

}
