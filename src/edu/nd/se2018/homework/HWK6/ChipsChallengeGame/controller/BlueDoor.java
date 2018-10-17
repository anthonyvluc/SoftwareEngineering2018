package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller;

import java.awt.Point;

import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model.LevelMap;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.view.DoorView;

public class BlueDoor extends Door {

	public BlueDoor(Point coordinates, LevelMap levelMap, int scale) {
		super(coordinates, levelMap, scale);
		
		// Set initial image.
		doorView = new DoorView(this, scale, "images/chip/textures/blueKeyWall.png");
	}

}
