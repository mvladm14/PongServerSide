package com.inria.myriads;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import retrofit.RestAdapter;

import com.inria.myriads.interfaces.PongSvcApi;
import com.inria.myriads.models.GryoscopeCoordinates;

public class MyriadsControllerTest {

	private static final String SERVER = "http://localhost:8080/myriads";

	private GryoscopeCoordinates gryoscopeCoordinates = GryoscopeCoordinates
			.create().withX(10.1).withY(20.2).withZ(30.3).build();

	private PongSvcApi pongSvc = new RestAdapter.Builder().setEndpoint(SERVER)
			.build().create(PongSvcApi.class);

	@Test
	public void testAddGetGryoscopeCoordinates() throws Exception {
		pongSvc.addGryoscopeCoordinates(gryoscopeCoordinates);
		GryoscopeCoordinates stored = pongSvc.getGryoscopeCoordinates();
		assertTrue(stored.equals(gryoscopeCoordinates));
	}
}
