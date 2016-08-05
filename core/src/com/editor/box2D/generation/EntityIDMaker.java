package com.editor.box2D.generation;

import com.editor.box2D.EntityManager;

public class EntityIDMaker {
	
	public static int generate(){
		return (int) Math.pow(EntityManager.getEntities().size, 2);
	}

}
