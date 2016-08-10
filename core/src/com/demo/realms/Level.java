package com.demo.realms;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.editor.box2D.constants.Helper;
import com.editor.box2D.entity.BoxEntity;
import com.editor.box2D.entity.Player;

public class Level {
	
	private Array<Realm> realms;
	private Realm currentRealm;
	private int realmIndex = 0;
	
	private Player player;
	
	public Level(){
		
		realms = new Array<Realm>();
		realms.add(new Realm("Realm1"));
		realms.add(new Realm("Realm2"));
		currentRealm = realms.get(realmIndex);
		
		
		//player = new Player(Helper.createVec(1, 1));
		//player.createBody(currentRealm);
		System.out.println("Body count: " + currentRealm.getWorld().getBodyCount());
		BoxEntity entity = new BoxEntity(Helper.createVec(1, 1), Helper.createVec(1, 1), BodyType.StaticBody);
		entity.createBody(currentRealm.getWorld());
		System.out.println("Body count: " + currentRealm.getWorld().getBodyCount());
	}
	
	public void switchRealm(){
		if(realmIndex < realms.size - 1){
			realmIndex ++;
		}else
			realmIndex = 0;
		
		
		currentRealm = realms.get(realmIndex);
	}
	
	public void render(OrthographicCamera camera){
		currentRealm.render(camera);
	}
	
	public void update(OrthographicCamera camera){
		currentRealm.update(camera);
		camera.update();
	}

}
