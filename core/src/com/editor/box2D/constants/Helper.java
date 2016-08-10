package com.editor.box2D.constants;

import com.badlogic.gdx.math.Vector2;

public class Helper {
	
	private static Vector2 pooled = new Vector2();
	public static Vector2 createVec(float x, float y){
		pooled.x = x;
		pooled.y = y;
		return pooled;
	}

}
