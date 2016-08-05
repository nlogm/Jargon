package com.editor.managers;


import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Set;

/**
 * File: AssetManager.java
 * Purpose: Used to as a way to load Textures and Audio files into the game by keyword/reference
 * Last Edited: 07/30/2016 - 5:38 AM
 * @author: Douglas Rudolph
 */
public class AssetManager
{

    private static HashMap<String, Texture> textures= new HashMap<String, Texture>();
    private static HashMap<String, Sound> sounds = new HashMap<String, Sound>();

    public static void addTexture(String key, Texture tex)
    {
        textures.put(key, tex);
    }

    public static Texture getTexture(String key)
    {
        return textures.get(key);
    }

    public static Set getTextureKeySet()
    {
        return textures.keySet();
    }

    public static void addSound(String key, Sound sound)
    {
        sounds.put(key, sound);
    }

    public static Sound getSound(String key)
    {
        return sounds.get(key);
    }

    public static Set getSoundsKeySet()
    {
        return sounds.keySet();
	}
}
