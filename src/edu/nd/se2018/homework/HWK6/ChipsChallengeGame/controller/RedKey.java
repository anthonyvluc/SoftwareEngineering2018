package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller;

import java.awt.Point;

import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.view.ItemView;

public class RedKey extends Item {

	public RedKey(Point coordinates, int scale) {
		super(coordinates, scale);
		
		// Set initial image.
		itemView = new ItemView(this, scale, "images/chip/textures/redKey.png");
	}

}
