package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller;

import java.awt.Point;

import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.view.DoorView;

public class RedDoor extends Door {

	public RedDoor(Point coordinates, int scale) {
		super(coordinates, scale);
		
		// Set initial image.
		doorView = new DoorView(this, scale, "images/chip/textures/redKeyWall.png");
	}

}