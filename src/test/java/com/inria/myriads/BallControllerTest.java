package com.inria.myriads;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import models.Coordinates;
import models.PongBall;

import org.junit.Test;

import restInterfaces.PongBallSvcApi;
import retrofit.RestAdapter;


public class BallControllerTest {

	//private static final String SERVER = "http://131.254.101.102:8080/myriads";
	private static final String SERVER = "http://131.254.101.102:8080/PongServerSide";

	private PongBallSvcApi pongBallSvc = new RestAdapter.Builder()
			.setEndpoint(SERVER).build().create(PongBallSvcApi.class);

	private Coordinates initialCoordinates = Coordinates.create().withX(10)
			.withY(20).build();
	
	private Coordinates otherCoordinates = Coordinates.create().withX(30)
			.withY(40).build();

	private PongBall pongBall = PongBall.create().withId(1).withDiameter(5)
			.withCoordinates(initialCoordinates).build();
	
	private PongBall pongBall2 = PongBall.create().withId(2).withDiameter(5)
			.withCoordinates(initialCoordinates).build();
	
	@Test
	public void test_GET_PongBalls() throws Exception {
		pongBallSvc.addPongBall(pongBall);
		pongBallSvc.addPongBall(pongBall2);
		Collection<PongBall> stored = pongBallSvc.getPongBalls();
		assertTrue(stored.size() == 2);
	}
	
	@Test
	public void test_GET_PongBallCoordinates() throws Exception {
		pongBallSvc.addPongBall(pongBall);
		Coordinates stored = pongBallSvc.getCoordinates(pongBall.getId());
		assertTrue(stored.equals(initialCoordinates));
	}
	
	@Test
	public void test_POST_PongBallCoordinates() throws Exception {
		pongBallSvc.addPongBall(pongBall);
		pongBallSvc.setCoordinates(pongBall.getId(), otherCoordinates);
		Coordinates stored = pongBallSvc.getCoordinates(pongBall.getId());
		assertTrue(stored.equals(otherCoordinates));
	}

}
