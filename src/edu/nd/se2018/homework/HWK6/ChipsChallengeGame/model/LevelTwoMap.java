package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model;

import java.awt.Point;

import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.Chip;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.Portal;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public class LevelTwoMap extends LevelMap {

	public LevelTwoMap(int dimension, int scale, ObservableList<Node> root) {
		super(dimension, scale, root);
	}

	@Override
	public void generateLevel() {
		// Set Chip location.
		chip = new Chip(new Point(12, 12), this, scale);

		// Set portal location.
		this.portal = new Portal(new Point(12, 10), this, scale);
		
	}
}
