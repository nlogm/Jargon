package com.editor.listeners.collision;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;
import com.editor.box2D.EntityManager;
import com.editor.box2D.entity.Entity;
import com.editor.box2D.entity.Player;

public class CollisionReciever implements ContactListener{

	

	
	private Array<Entity> entityList;
	private Fixture fixtureA, fixtureB;
	private Player player;
	
	public CollisionReciever(Player player){
		this.player = player;
		entityList = EntityManager.getEntities();
		
	}
	
	public void maintainList(Array<Entity> newList){
		this.entityList = newList;
	}
	@Override
	public void beginContact(Contact contact) {
		fixtureA = contact.getFixtureA();
		fixtureB = contact.getFixtureB();
		System.out.println(fixtureA.getUserData());
	}

	@Override
	public void endContact(Contact contact) {
		preCheck();
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		preCheck();
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		preCheck();
	}
	
	public void preCheck(){
		maintainList(EntityManager.getEntities());
	}

}
