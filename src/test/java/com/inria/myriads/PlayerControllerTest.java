package com.inria.myriads;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import models.phone.Accelerometer;
import models.phone.MagneticField;
import models.phone.PhoneSensors;
import models.player.HittableRegion;
import models.player.Player;

import org.junit.Test;

import restInterfaces.PlayerSvcApi;
import retrofit.RestAdapter;

public class PlayerControllerTest {

	private static final String SERVER = "http://131.254.101.102:8080/myriads";
	// private static final String SERVER =
	// "http://131.254.101.102:8080/PongServerSide";

	private static final float[] valuesM = { 10.1f, 10.2f, 10.3f };
	private static final float[] valuesA = { 10.4f, 10.5f, 10.6f };
	
	private static final long timeStampP1 = 100;
	private static final long timeStampP2 = 200;

	private MagneticField magneticField = MagneticField.create()
			.withValues(valuesM).build();
	private Accelerometer accelerometer = Accelerometer.create()
			.withValues(valuesA).build();

	private PhoneSensors phoneCoordinatesP1 = PhoneSensors.create()
			.withSensorTimeStamp(timeStampP1)
			.withMagneticField(magneticField).withAccelerometer(accelerometer)
			.build();
	private PhoneSensors phoneCoordinatesP2 = PhoneSensors.create()
			.withSensorTimeStamp(timeStampP2)
			.withMagneticField(magneticField).withAccelerometer(accelerometer)
			.build();

	private HittableRegion hittableRegionP1 = HittableRegion.create().withX(20)
			.build();
	private HittableRegion hittableRegionP2 = HittableRegion.create().withX(30)
			.build();

	private Player player1 = Player.create().withId(1).withUsername("player1")
			.withPhoneSensors(phoneCoordinatesP1)
			.withHittableRegion(hittableRegionP1).build();

	private Player player2 = Player.create().withId(2).withUsername("player2")
			.withPhoneSensors(phoneCoordinatesP2)
			.withHittableRegion(hittableRegionP2).build();

	private PlayerSvcApi pongSvc = new RestAdapter.Builder()
			.setEndpoint(SERVER).build().create(PlayerSvcApi.class);

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
		PhoneSensors stored = pongSvc.getPhoneSensors(player1.getId());
		assertTrue(stored.equals(phoneCoordinatesP1));
	}

	@Test
	public void test_POST_PlayerGyroscopeCoordinates() throws Exception {
		pongSvc.addPlayer(player1);
		pongSvc.setPhoneSensors(player1.getId(), phoneCoordinatesP2);
		PhoneSensors stored = pongSvc.getPhoneSensors(player1.getId());
		assertTrue(stored.equals(phoneCoordinatesP2));
	}

	@Test
	public void test_GET_PlayerAccelerometer() throws Exception {
		pongSvc.addPlayer(player1);
		Accelerometer stored = pongSvc.getPhoneSensors(player1.getId())
				.getAccelerometer();
		assertTrue(stored.equals(accelerometer));
	}

	@Test
	public void test_POST_PlayerAccelerometer() throws Exception {
		pongSvc.addPlayer(player1);
		Accelerometer other = Accelerometer.create().withValues(valuesM)
				.build();
		pongSvc.setAccelerometer(player1.getId(), other);
		Accelerometer stored = pongSvc.getPhoneSensors(player1.getId())
				.getAccelerometer();
		assertTrue(stored.equals(other));
	}
	
	@Test
	public void test_GET_PlayerMagnetic() throws Exception {
		pongSvc.addPlayer(player1);
		MagneticField stored = pongSvc.getPhoneSensors(player1.getId())
				.getMagneticField();
		assertTrue(stored.equals(magneticField));
	}

	@Test
	public void test_POST_PlayerMagnetic() throws Exception {
		pongSvc.addPlayer(player1);
		MagneticField other = MagneticField.create().withValues(valuesA)
				.build();
		pongSvc.setMagnetic(player1.getId(), other);
		MagneticField stored = pongSvc.getPhoneSensors(player1.getId())
				.getMagneticField();
		assertTrue(stored.equals(other));
	}
	
	@Test
	public void test_GET_Player_TimeStamp() throws Exception {
		pongSvc.addPlayer(player1);
		long stored = pongSvc.getPhoneSensors(player1.getId())
				.getSensorTimeStamp();
		assertTrue(stored == timeStampP1);
	}

	@Test
	public void test_POST_Player_TimeStamp() throws Exception {
		pongSvc.addPlayer(player1);
		pongSvc.setTimeStamp(player1.getId(), timeStampP2);
		long stored = pongSvc.getPhoneSensors(player1.getId())
				.getSensorTimeStamp();
		assertTrue(stored == timeStampP2);
	}
}
