package com.demo.realms;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.editor.box2D.constants.Helper;
import com.editor.box2D.entity.BoxEntity;
import com.editor.box2D.entity.Player;
import com.editor.listeners.InputReciever;
import com.editor.listeners.collision.CollisionReciever;

public class Level {
	
	private Array<Realm> realms;
	private Array<Player> players;
	private Realm currentRealm;
	private int realmIndex = 0;
	
	private Player player;
	InputReciever inputReciever;
	private CollisionReciever collisionReciever;
	public Level(){
		
		realms = new Array<Realm>();
		realms.add(new Realm("Realm1"));
		realms.add(new Realm("Realm2"));
		currentRealm = realms.get(realmIndex);
		
		players = new Array<Player>();
		for(Realm realm : realms){
			players.add(new Player(new Vector2(1, 2)));
			players.get(players.size - 1).createBody(realm.getWorld());
		}
		BoxEntity entity = new BoxEntity(new Vector2(2, 1), new Vector2(1, .5f), BodyType.StaticBody);
		entity.createBody(currentRealm.getWorld());
		
		BoxEntity entity1 = new BoxEntity(new Vector2(2, 1), new Vector2(1, .5f), BodyType.StaticBody);
		entity1.createBody(realms.get(1).getWorld());
		
		inputReciever = new InputReciever(players.get(realmIndex), this);
		Gdx.input.setInputProcessor(inputReciever);
		
		collisionReciever = new CollisionReciever(player);
		currentRealm.getWorld().setContactListener(collisionReciever);
	}
	
	public void switchRealm(){
		if(realmIndex < realms.size - 1){
			realmIndex ++;
		}else
			realmIndex = 0;
		
		
		currentRealm = realms.get(realmIndex);
		currentRealm.getWorld().setContactListener(collisionReciever);
		inputReciever.setPlayer(player);
	}
	
	public void render(OrthographicCamera camera){
		currentRealm.render(camera);
	}
	
	public void update(OrthographicCamera camera){
		currentRealm.update(camera);
		players.get(realmIndex).update();
	}

}
