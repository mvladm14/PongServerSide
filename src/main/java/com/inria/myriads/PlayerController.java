package com.inria.myriads;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import models.phone.Accelerometer;
import models.phone.Gravity;
import models.phone.MagneticField;
import models.phone.PhoneSensors;
import models.player.Player;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping(value = PlayerSvcApi.PONG_PLAYER_PATH, method = RequestMethod.GET)
	public @ResponseBody PhoneSensors getPhoneSensors(
			@PathVariable("id") long id) {
		PhoneSensors output = null;
		if (players.get(id) != null) {

			Player player = players.get(id);

			output = player.getPhoneSensors();
		}
		return output;
	}

	@RequestMapping(value = PlayerSvcApi.PONG_PLAYER_PATH, method = RequestMethod.POST)
	public @ResponseBody PhoneSensors setPhoneSensors(
			@PathVariable("id") long id, @RequestBody PhoneSensors gv) {

		PhoneSensors output = null;

		if (players.get(id) != null) {
			Player player = players.get(id);
			player.setPhoneSensors(gv);
			output = player.getPhoneSensors();
		}

		return output;
	}

	private Player savePlayer(Player player) {
		players.put(player.getId(), player);
		return player;
	}

	@Override
	@RequestMapping(value = PlayerSvcApi.PLAYER_ACCELEROMETER, method = RequestMethod.GET)
	public @ResponseBody Accelerometer getAccelerometer(
			@PathVariable("id") long id) {
		Accelerometer accelerometer = null;
		if (players.get(id) != null) {

			Player player = players.get(id);

			accelerometer = player.getPhoneSensors().getAccelerometer();
		}
		return accelerometer;
	}

	@Override
	@RequestMapping(value = PlayerSvcApi.PLAYER_ACCELEROMETER, method = RequestMethod.POST)
	public @ResponseBody Accelerometer setAccelerometer(
			@PathVariable("id") long id,
			@RequestBody Accelerometer accelerometer) {

		if (players.get(id) != null) {
			Player player = players.get(id);
			player.getPhoneSensors().setAccelerometer(accelerometer);
		}

		return accelerometer;
	}

	@Override
	@RequestMapping(value = PlayerSvcApi.PLAYER_MAGNETIC, method = RequestMethod.GET)
	public @ResponseBody MagneticField getMagnetic(@PathVariable("id") long id) {
		MagneticField magneticField = null;
		if (players.get(id) != null) {

			Player player = players.get(id);

			magneticField = player.getPhoneSensors().getMagneticField();
		}
		return magneticField;
	}

	@Override
	@RequestMapping(value = PlayerSvcApi.PLAYER_MAGNETIC, method = RequestMethod.POST)
	public @ResponseBody MagneticField setMagnetic(@PathVariable("id") long id,
			@RequestBody MagneticField magneticField) {

		if (players.get(id) != null) {
			Player player = players.get(id);
			player.getPhoneSensors().setMagneticField(magneticField);
		}

		return magneticField;
	}

	@Override
	@RequestMapping(value = PlayerSvcApi.TIME_STAMP, method = RequestMethod.GET)
	public @ResponseBody long getTimeStamp(@PathVariable long id) {
		long timestamp = 0;
		if (players.get(id) != null) {

			Player player = players.get(id);

			timestamp = player.getPhoneSensors().getSensorTimeStamp();
		}
		return timestamp;
	}

	@Override
	@RequestMapping(value = PlayerSvcApi.TIME_STAMP, method = RequestMethod.POST)
	public @ResponseBody long setTimeStamp(@PathVariable long id,
			@RequestBody long timestamp) {
		long output = 0;

		if (players.get(id) != null) {
			Player player = players.get(id);
			player.getPhoneSensors().setSensorTimeStamp(timestamp);
			output = player.getPhoneSensors().getSensorTimeStamp();
		}

		return output;
	}

	@Override
	@RequestMapping(value = PlayerSvcApi.PLAYER_GRAVITY, method = RequestMethod.GET)
	public @ResponseBody Gravity getGravity(@PathVariable("id") long id) {
		Gravity gravity = null;
		if (players.get(id) != null) {

			Player player = players.get(id);

			gravity = player.getPhoneSensors().getGravity();
		}
		return gravity;
	}

	@Override
	@RequestMapping(value = PlayerSvcApi.PLAYER_GRAVITY, method = RequestMethod.POST)
	public @ResponseBody Gravity setGravity(@PathVariable("id") long id,
			@RequestBody Gravity gravity) {
		if (players.get(id) != null) {
			Player player = players.get(id);
			player.getPhoneSensors().setGravity(gravity);
		}

		return gravity;
	}
}
