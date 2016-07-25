package com.editor.generation;

import com.editor.entity.Entity;
import com.editor.managers.EntityManager;

public class EntityIDMaker {
	
	public static int generate(){
		return (int) Math.pow(EntityManager.getEntities().size, 2);
	}

}
