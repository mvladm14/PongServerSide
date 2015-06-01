package com.inria.myriads;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import models.GyroscopeCoordinates;
import models.Player;

import org.junit.Test;

import restInterfaces.PlayerSvcApi;
import retrofit.RestAdapter;

public class PlayerControllerTest {

	private static final String SERVER = "http://131.254.101.102:8080/myriads";

	private GyroscopeCoordinates gyroscopeCoordinatesP1 = GyroscopeCoordinates
			.create().withX(10.1).withY(20.2).withZ(30.3).build();
	private GyroscopeCoordinates gyroscopeCoordinatesP2 = GyroscopeCoordinates
			.create().withX(30.3).withY(20.2).withZ(10.1).build();

	private Player player1 = Player.create().withId(1).withUsername("player1")
			.withGyroscopeCoordinates(gyroscopeCoordinatesP1).build();

	private Player player2 = Player.create().withId(2).withUsername("player2")
			.withGyroscopeCoordinates(gyroscopeCoordinatesP2).build();

	private PlayerSvcApi pongSvc = new RestAdapter.Builder().setEndpoint(SERVER)
			.build().create(PlayerSvcApi.class);

	@Test
	public void test_GET_Players() throws Exception {
		pongSvc.addPlayer(player1);
		pongSvc.addPlayer(player2);
		Collection<Player> stored = pongSvc.getPlayersList();
		assertTrue(stored.size() == 2);
	}
	
	@Test
	public void test_GET_PlayerGyroscopeCoordinates() throws Exception {
		pongSvc.addPlayer(player1);
		GyroscopeCoordinates stored = pongSvc.getGyroscopeCoordinates(player1.getId());
		assertTrue(stored.equals(gyroscopeCoordinatesP1));
	}
	
	@Test
	public void test_POST_PlayerGyroscopeCoordinates() throws Exception {
		pongSvc.addPlayer(player1);
		pongSvc.setGyroscopeCoordinates(player1.getId(), gyroscopeCoordinatesP2);
		GyroscopeCoordinates stored = pongSvc.getGyroscopeCoordinates(player1.getId());
		assertTrue(stored.equals(gyroscopeCoordinatesP2));
	}
}
