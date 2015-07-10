package com.inria.myriads;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import models.player.Player;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import restInterfaces.PlayerSvcApi;

@Controller
public class PlayerController implements PlayerSvcApi {

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

	private Player savePlayer(Player player) {
		players.put(player.getId(), player);
		return player;
	}
}
