package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model;

import java.awt.Point;

import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.BlueDoor;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.Chip;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.Door;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.Portal;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public class LevelOneMap extends LevelMap {
	
	// Portal portal
	
	public LevelOneMap(int dimension, int scale, ObservableList<Node> root) {
		super(dimension, scale, root);
	}

	@Override
	public void generateLevel() {
		// Set Chip location.
		chip = new Chip(new Point(12, 12), this, scale);

		// Set portal location.
		portal = new Portal(new Point(0, 0), this, scale);
	
		// Add walls on left-hand side.
		levelBuilder.addWall(new Point(6, 0), new Point(6, 20));
		
		Door blueDoor = new BlueDoor(new Point(6, 23), this, scale);
		addDoor(blueDoor);
		
	}
}
