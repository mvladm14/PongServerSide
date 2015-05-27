package com.inria.myriads.interfaces;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

import com.inria.myriads.models.GryoscopeCoordinates;

public interface PongSvcApi {

	public static final String PONG_BALL_SVC_PATH = "/ball";

	/**
	 * This endpoint in the API returns the gyroscope coordinates that have been
	 * added to the server. The GryoscopeCoordinates objects should be returned
	 * as JSON.
	 * 
	 * To manually test this endpoint, run your server and open this URL in a
	 * browser: http://localhost:8080/pongball
	 * 
	 * @return
	 */
	@GET(PONG_BALL_SVC_PATH)
	public GryoscopeCoordinates getGryoscopeCoordinates();

	/**
	 * This endpoint allows clients to add GryoscopeCoordinates objects by
	 * sending POST requests that have an application/json body containing the
	 * GryoscopeCoordinates object information.
	 * 
	 * @return
	 */
	@POST(PONG_BALL_SVC_PATH)
	public GryoscopeCoordinates addGryoscopeCoordinates(
			@Body GryoscopeCoordinates v);

}
