package com.inria.myriads;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import models.ball.BallCoordinates;
import models.ball.PongBall;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import restInterfaces.PongBallSvcApi;

@Controller
public class PongBallController {

	private Map<Long, PongBall> pongBalls = new HashMap<Long, PongBall>();

	@RequestMapping(value = PongBallSvcApi.PONG_BALL, method = RequestMethod.GET)
	public @ResponseBody Collection<PongBall> getPongBalls() {
		return pongBalls.values();
	}

	@RequestMapping(value = PongBallSvcApi.PONG_BALL, method = RequestMethod.POST)
	public @ResponseBody PongBall addPongBall(@RequestBody PongBall pongBall) {
		savePongBall(pongBall);
		return pongBall;
	}

	@RequestMapping(value = PongBallSvcApi.PONG_BALL_PATH, method = RequestMethod.GET)
	public @ResponseBody BallCoordinates getCoordinates(@PathVariable("id") long id)
			throws IOException {
		BallCoordinates output = null;
		if (pongBalls.get(id) != null) {

			PongBall pongBall = pongBalls.get(id);

			output = pongBall.getCoordinates();
		}
		return output;
	}

	@RequestMapping(value = PongBallSvcApi.PONG_BALL_PATH, method = RequestMethod.POST)
	public @ResponseBody BallCoordinates setCoordinates(
			@PathVariable("id") long id, @RequestBody BallCoordinates coordinates)
			throws IOException {

		BallCoordinates output = null;

		if (pongBalls.get(id) != null) {
			PongBall pongBall = pongBalls.get(id);
			pongBall.setCoordinates(coordinates);
			output = pongBall.getCoordinates();
		}

		return output;
	}

	private PongBall savePongBall(PongBall pongBall) {
		pongBalls.put(pongBall.getId(), pongBall);
		return pongBall;
	}
}
