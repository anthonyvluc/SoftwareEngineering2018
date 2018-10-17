package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller;

import java.awt.Point;

import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.view.KeyView;

public class BlueKey extends Key {

	public BlueKey(Point coordinates, int scale) {
		super(coordinates, scale);
		
		// Set initial image.
		keyView = new KeyView(this, scale, "images/chip/textures/blueKey.png");
	}

}
