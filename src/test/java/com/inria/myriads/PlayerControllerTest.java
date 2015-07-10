package com.inria.myriads;

import static org.junit.Assert.assertTrue;

import java.util.Collection;
import models.player.HittableRegion;
import models.player.Player;

import org.junit.Test;

import restInterfaces.PlayerSvcApi;
import retrofit.RestAdapter;

public class PlayerControllerTest {

	private static final String SERVER = "http://131.254.101.102:8080/myriads";

	private PlayerSvcApi pongSvc = new RestAdapter.Builder()
			.setEndpoint(SERVER).build().create(PlayerSvcApi.class);

	private HittableRegion hittableRegionP1 = HittableRegion.create()
			.withX(20).build();
	private HittableRegion hittableRegionP2 = HittableRegion.create()
			.withX(30).build();

	private Player player1 = Player.create()
			.withId(1)
			.withUsername("vlad")
			.withScore(0)
			.withCanHitBall(true)
			.withCanPlay(false)
			.withHittableRegion(hittableRegionP1)			
			.build();
	private Player player2 = Player.create()
			.withId(2)
			.withUsername("roxy")
			.withScore(0)
			.withCanHitBall(true)
			.withCanPlay(false)
			.withHittableRegion(hittableRegionP2)			
			.build();
	
	@Test
	public void test_ADD_GET_Players() throws Exception {
		pongSvc.addPlayer(player1);
		pongSvc.addPlayer(player2);
		Collection<Player> stored = pongSvc.getPlayersList();
		assertTrue(stored.size() == 2);
	}
	
//	@Test
//	public void test_PLAYING_Players() throws Exception {
//		pongSvc.addPlayer(player1);
//		pongSvc.addPlayer(player2);
//		Collection<Player> stored = pongSvc.getPlayersList();
//		assertTrue(stored.size() == 2);
//		assertTrue(stored.iterator().next().canPlay() == false);
//		
//		player1.setCanPlay(true);
//		player2.setCanPlay(true);
//		pongSvc.addPlayer(player1);
//		pongSvc.addPlayer(player2);
//		stored = pongSvc.getPlayersList();
//		assertTrue(stored.size() == 2);
//		assertTrue(stored.iterator().next().canPlay() == true);
//		
//	}
}
