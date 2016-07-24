package com.editor.managers;

import java.util.HashMap;
import java.util.Set;
import com.badlogic.gdx.graphics.Texture;

public class AssetManager
{

	private static HashMap<String, Texture> textures= new HashMap<String, Texture>();

	public static void addTexture(String key, Texture tex)
	{
		textures.put(key, tex);
	}

	public static Texture getTexture(String key)
	{
		return textures.get(key);
	}

	public static Set getKeySet()
	{
		return textures.keySet();
		
	}

}
