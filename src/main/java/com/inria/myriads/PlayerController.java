package com.inria.myriads;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inria.myriads.interfaces.PlayerSvcApi;
import com.inria.myriads.models.GyroscopeCoordinates;
import com.inria.myriads.models.Player;

@Controller
public class PlayerController {

	private Map<Long, Player> players = new HashMap<Long, Player>();

	@RequestMapping(value = PlayerSvcApi.PONG_PLAYERS, method = RequestMethod.GET)
	public @ResponseBody Collection<Player> getPlayersList() {
		return players.values();
	}

	@RequestMapping(value = PlayerSvcApi.PONG_PLAYERS, method = RequestMethod.POST)
	public @ResponseBody Player addPlayer(@RequestBody Player player) {
		savePlayer(player);
		return player;
	}

	@RequestMapping(value = PlayerSvcApi.PONG_PLAYER_PATH, method = RequestMethod.GET)
	public @ResponseBody GyroscopeCoordinates getGyroscopeCoordinates(
			@PathVariable("id") long id) throws IOException {
		GyroscopeCoordinates output = null;
		if (players.get(id) != null) {

			Player player = players.get(id);

			output = player.getGyroscopeCoordinates();
		}
		return output;
	}

	@RequestMapping(value = PlayerSvcApi.PONG_PLAYER_PATH, method = RequestMethod.POST)
	public @ResponseBody GyroscopeCoordinates setGyroscopeCoordinates(
			@PathVariable("id") long id, @RequestBody GyroscopeCoordinates gv)
			throws IOException {

		GyroscopeCoordinates output = null;

		if (players.get(id) != null) {
			Player player = players.get(id);
			player.setGyroscopeCoordinates(gv);
			output = player.getGyroscopeCoordinates();
		}

		return output;
	}

	private Player savePlayer(Player player) {
		players.put(player.getId(), player);
		return player;
	}
}
