package com.inria.myriads;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import models.PhoneCoordinates;
import models.Player;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import restInterfaces.PlayerSvcApi;


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
	public @ResponseBody PhoneCoordinates getPhoneCoordinates(
			@PathVariable("id") long id) throws IOException {
		PhoneCoordinates output = null;
		if (players.get(id) != null) {

			Player player = players.get(id);

			output = player.getPhoneCoordinates();
		}
		return output;
	}

	@RequestMapping(value = PlayerSvcApi.PONG_PLAYER_PATH, method = RequestMethod.POST)
	public @ResponseBody PhoneCoordinates setPhoneCoordinates(
			@PathVariable("id") long id, @RequestBody PhoneCoordinates gv)
			throws IOException {

		PhoneCoordinates output = null;

		if (players.get(id) != null) {
			Player player = players.get(id);
			player.setPhoneCoordinates(gv);
			output = player.getPhoneCoordinates();
		}

		return output;
	}

	private Player savePlayer(Player player) {
		players.put(player.getId(), player);
		return player;
	}
}
