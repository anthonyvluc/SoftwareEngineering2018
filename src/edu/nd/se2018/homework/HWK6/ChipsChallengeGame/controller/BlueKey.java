package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller;

import java.awt.Point;

import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.view.ItemView;

public class BlueKey extends Item {

	public BlueKey(Point coordinates, int scale) {
		super(coordinates, scale);
		
		// Set initial image.
		itemView = new ItemView(this, scale, "images/chip/textures/blueKey.png");
	}

}
