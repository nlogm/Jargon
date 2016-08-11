package com.editor.listeners.collision;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;
import com.editor.box2D.EntityManager;
import com.editor.box2D.constants.PlayerTuples;
import com.editor.box2D.entity.Entity;
import com.editor.box2D.entity.Player;

public class CollisionReciever implements ContactListener {

	private Array<Entity> entityList;
	private Fixture fixtureA, fixtureB;
	private Player player;

	public CollisionReciever() {
		entityList = EntityManager.getEntities();

	}

	public void setPlayer(Player currentPlayer) {
		this.player = currentPlayer;
	}

	public void maintainList(Array<Entity> newList) {
		this.entityList = newList;
	}

	@Override
	public void beginContact(Contact contact) {
		fixtureA = contact.getFixtureA();
		fixtureB = contact.getFixtureB();
		int aData = 0;
		int bData = 0;
		// if(fixtureA)
		if (fixtureA.getUserData() != null && fixtureB.getUserData() != null) {
			try {
				aData = (int) fixtureA.getUserData();
			} catch (ClassCastException e) {
				Gdx.app.log("Fatal", "You are using Entity.createBody(String worldHash), which is deprecated, fix calls to Entity");
				e.printStackTrace();
				System.exit(0);
			}
			// Check for ability to jump (is on ground)
			if (aData == (int) player.getFixtures().get(Player.BOTTOM_LEFT_INDEX).getUserData()) {
				player.setMovementBoolean(PlayerTuples.LEFT_FOOT_OFF, false);
			}
			if (aData == (int) player.getFixtures().get(Player.BOTTOM_RIGHT_INDEX).getUserData()) {
				player.setMovementBoolean(PlayerTuples.RIGHT_FOOT_OFF, false);
			}

			// check for grab
			try {
				try {
					bData = (int) fixtureB.getUserData();
				} catch (ClassCastException e) {
					Gdx.app.log("Fatal", "You are using Entity.createBody(String worldHash), which is deprecated, fix calls to Entity");
					e.printStackTrace();
					System.exit(0);
				}
			} catch (ClassCastException e) {
				Gdx.app.log("Fatal", "You are using Entity.createBody(String worldHash), which is deprecated, fix calls to Entity");
				e.printStackTrace();
				System.exit(0);
			}
			if (aData == (int) player.getFixtures().get(Player.TOP_RIGHT_INDEX).getUserData() && bData == (int) (FixtureData.OTHER | FixtureData.TOP_LEFT_SENSOR)) {
				player.setDestination(new Vector2(.5f, .35f));
				player.setMovementBoolean(PlayerTuples.IS_HANGING, true);
			}

		}

		if (fixtureB.getUserData() != null && fixtureA.getUserData() != null) {
			try {
				aData = (int) fixtureA.getUserData();
				bData = (int) fixtureB.getUserData();
			} catch (ClassCastException e) {
				Gdx.app.log("Fatal", "You are using Entity.createBody(String worldHash), which is deprecated, fix calls to Entity");
				e.printStackTrace();
				System.exit(0);
			}
			if (bData == (int) player.getFixtures().get(Player.BOTTOM_LEFT_INDEX).getUserData()) {
				player.setMovementBoolean(PlayerTuples.LEFT_FOOT_OFF, false);
			}
			if (bData == (int) player.getFixtures().get(Player.BOTTOM_RIGHT_INDEX).getUserData()) {
				player.setMovementBoolean(PlayerTuples.RIGHT_FOOT_OFF, false);

			}

			if (bData == (int) player.getFixtures().get(Player.TOP_RIGHT_INDEX).getUserData() && aData == (int) (FixtureData.OTHER | FixtureData.TOP_LEFT_SENSOR)) {
				player.setDestination(new Vector2(.2f, .1f));
				player.setMovementBoolean(PlayerTuples.IS_HANGING, true);
			}
		}
	}

	@Override
	public void endContact(Contact contact) {
		fixtureA = contact.getFixtureA();
		fixtureB = contact.getFixtureB();
		int aData = 0;
		int bData = 0;
		if (fixtureA.getUserData() != null) {
			try {
				aData = (int) fixtureA.getUserData();
			} catch (ClassCastException e) {
				Gdx.app.log("Fatal", "You are using Entity.createBody(String worldHash), which is deprecated, fix calls to Entity");
				e.printStackTrace();
				System.exit(0);
			}
			if (aData == (int) player.getFixtures().get(Player.BOTTOM_LEFT_INDEX).getUserData()) {
				player.setMovementBoolean(PlayerTuples.LEFT_FOOT_OFF, true);
			}
			if (aData == (int) player.getFixtures().get(Player.BOTTOM_RIGHT_INDEX).getUserData()) {
				player.setMovementBoolean(PlayerTuples.RIGHT_FOOT_OFF, true);
			}
		}

		if (fixtureB.getUserData() != null) {
			try {
				bData = (int) fixtureB.getUserData();
			} catch (ClassCastException e) {
				Gdx.app.log("Fatal", "You are using Entity.createBody(String worldHash), which is deprecated, fix calls to Entity");
				e.printStackTrace();
				System.exit(0);
			}
			if (bData == (int) player.getFixtures().get(Player.BOTTOM_LEFT_INDEX).getUserData()) {
				player.setMovementBoolean(PlayerTuples.LEFT_FOOT_OFF, true);
			}
			if (bData == (int) player.getFixtures().get(Player.BOTTOM_RIGHT_INDEX).getUserData()) {
				player.setMovementBoolean(PlayerTuples.RIGHT_FOOT_OFF, true);
			}
		}
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		preCheck();
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		preCheck();
	}

	public void preCheck() {
		maintainList(EntityManager.getEntities());
	}

}
