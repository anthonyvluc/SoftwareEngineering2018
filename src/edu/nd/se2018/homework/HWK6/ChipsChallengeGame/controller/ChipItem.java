package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller;

import java.awt.Point;

import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.view.ItemView;

public class ChipItem extends Item {

	public ChipItem(Point coordinates, int scale) {
		super(coordinates, scale);
		
		// Set initial image.
		itemView = new ItemView(this, scale, "images/chip/textures/chipItem.png");
	}

}
