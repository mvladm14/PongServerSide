package com.inria.myriads;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inria.myriads.interfaces.PongSvcApi;
import com.inria.myriads.models.GryoscopeCoordinates;

@Controller
public class MyriadsController {

	private GryoscopeCoordinates gryoscopeCoordinates;

	@RequestMapping(value = PongSvcApi.PONG_BALL_SVC_PATH, method = RequestMethod.GET)
	public @ResponseBody GryoscopeCoordinates getGryoscopeCoordinates() {
		return gryoscopeCoordinates;
	}

	@RequestMapping(value = PongSvcApi.PONG_BALL_SVC_PATH, method = RequestMethod.POST)
	public @ResponseBody GryoscopeCoordinates addGryoscopeCoordinates(
			@RequestBody GryoscopeCoordinates gv) {

		save(gv);

		return gv;
	}

	private void save(GryoscopeCoordinates gv) {
		gryoscopeCoordinates = gv;
	}
}
