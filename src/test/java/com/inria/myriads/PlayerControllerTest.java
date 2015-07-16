package com.inria.myriads;

import static org.junit.Assert.assertTrue;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;

import models.player.HittableRegion;
import models.player.Player;
import models.player.PlayerState;

import org.junit.BeforeClass;
import org.junit.Test;

import restInterfaces.PlayerSvcApi;
import retrofit.RestAdapter;

public class PlayerControllerTest {

	private static String SERVER;
	private static PlayerSvcApi pongSvc;
	private static HittableRegion hittableRegionP1;
	private static HittableRegion hittableRegionP2;
	private static Player player1;
	private static Player player2;

	@BeforeClass
	public static void onceExecutedBeforeAll() {
		
		try {
			InetAddress IP = InetAddress.getLocalHost();
			SERVER = "http://" + IP.getHostAddress() + ":8080/myriads";
			//SERVER = "http://" + IP.getHostAddress() + ":8080/PongServerSide";
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		pongSvc = new RestAdapter.Builder().setEndpoint(SERVER).build()
				.create(PlayerSvcApi.class);

		hittableRegionP1 = HittableRegion.create().withX(20).build();
		hittableRegionP2 = HittableRegion.create().withX(30).build();

		player1 = Player.create().withId(1).withUsername("vlad").withScore(0)
				.withCanHitBall(true).withPlayerState(PlayerState.AVAILABLE)
				.withHittableRegion(hittableRegionP1).build();
		player2 = Player.create().withId(2).withUsername("roxy").withScore(0)
				.withCanHitBall(true).withPlayerState(PlayerState.AVAILABLE)
				.withHittableRegion(hittableRegionP2).build();

		System.out.println("@BeforeClass: onceExecutedBeforeAll");

	}

//	@Test
//	public void test_ADD_GET_Players() throws Exception {
//		pongSvc.addPlayer(player1);
//		pongSvc.addPlayer(player2);
//		Collection<Player> stored = pongSvc.getPlayersList();
//		assertTrue(stored.size() == 2);
//	}
	
	@Test
	public void test_ADD_GET_Players() throws Exception {
		System.out.println(player1.toString());
		pongSvc.addPlayer(player1);
		pongSvc.addPlayer(player2);
		Collection<Player> stored = pongSvc.getPlayersList();
		assertTrue(stored.size() == 2);
		System.out.println(stored.iterator().next().toString());
		assertTrue(stored.iterator().next().getPlayerState() == PlayerState.AVAILABLE);
	}
}
