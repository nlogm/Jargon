package com.demo.realms;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.editor.box2D.entity.BoxEntity;
import com.editor.box2D.entity.Player;
import com.editor.listeners.InputReciever;
import com.editor.listeners.collision.CollisionReciever;


/**
 * This class is used to hold all the different realms
 * and other objects used for rendering and updating
 * 
 * @author Luke Roche 9/19/2016
 */

public class Level {
	
	//array of realms and players
	private Array<Realm> realms;
	private Array<Player> players;
	//lambda test usage
	private List<Realm> levelRealms;
	//the current realm
	private Realm currentRealm;
	//the current realms index in the list
	private int realmIndex = 0;
	
	//input handler
	private InputReciever inputReciever;
	//collision handler
	private CollisionReciever collisionReciever;
	public Level(){
		
		realms = new Array<Realm>();
		realms.add(new Realm("Realm1", Gdx.files.internal("example.lvl").file()));
		//realms.add(new Realm("Realm2"));
		currentRealm = realms.get(realmIndex);
		
		players = new Array<Player>();
		realms.forEach(entry -> {players.add(new Player(new Vector2(2, 3))); players.get(players.size - 1).createBody(entry.getWorld());});

		//pussy way
		/*for(Realm realm : realms){
			players.add(new Player(new Vector2(2, 3)));
			players.get(players.size - 1).createBody(realm.getWorld());
		}*/
		inputReciever = new InputReciever(players.get(realmIndex), this);
		Gdx.input.setInputProcessor(inputReciever);
		
		collisionReciever = new CollisionReciever();
		collisionReciever.setPlayer(players.get(realmIndex));
		currentRealm.getWorld().setContactListener(collisionReciever);
	}
	
	private Player tmpPlayer;
	public void switchRealm(){
		//when switching, we need to swap safely
		tmpPlayer = players.get(realmIndex);
		if(realmIndex < realms.size - 1){
			realmIndex ++;
		}else
			realmIndex = 0;
		
		
		//this all makes sure everything that needs to be updated upon switch is
		currentRealm = realms.get(realmIndex);
		currentRealm.getWorld().setContactListener(collisionReciever);
		inputReciever.setPlayer(players.get(realmIndex));
		//this transforms the player in the list to the position of the original
		players.get(realmIndex).retainState(tmpPlayer);
		collisionReciever.setPlayer(players.get(realmIndex));
	}
	
	public void render(OrthographicCamera camera){
		currentRealm.render(camera);
	}
	
	public void update(OrthographicCamera camera){
		//camera.position.set(players.get(realmIndex).getBody().getTransform().getPosition(), 0);
		
		currentRealm.update(camera);
		
		players.get(realmIndex).update();
	}

}
