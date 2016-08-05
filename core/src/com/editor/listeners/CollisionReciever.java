package com.editor.listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;
import com.editor.box2D.entity.Entity;
import com.editor.box2D.EntityManager;

public class CollisionReciever implements ContactListener{

	

	
	private Array<Entity> entityList;
	private Fixture fixtureA, fixtureB;
	
	
	public CollisionReciever(){
		
		entityList = EntityManager.getEntities();
		
	}
	
	public void maintainList(Array<Entity> newList){
		this.entityList = newList;
	}
	@Override
	public void beginContact(Contact contact) {
		preCheck();
		fixtureA = contact.getFixtureA();
		fixtureB = contact.getFixtureB();
		for(Entity e : entityList){
			try{
			if(e.getBody().getFixtureList().first().getUserData().equals(fixtureA.getUserData())){
				e.trigger();
			}
			}catch(Exception d){
				Gdx.app.log("Null", "Null fixture");
			}
		}
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
