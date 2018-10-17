package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model;

import java.awt.Point;

import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.BlueDoor;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.Chip;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.Door;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.Portal;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.RedDoor;
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
		chip = new Chip(new Point(12, 10), this, scale);

		// Set portal location.
		portal = new Portal(new Point(0, 0), this, scale);
	
		/* Left side. --------------------- */
		// Left vertical wall
		levelBuilder.addWall(new Point(6, 0), new Point(6, 21));
		levelBuilder.addWall(new Point(6, 23), new Point(6, 24));
		
		// Left waters
		levelBuilder.addWater(new Point(0, 4), new Point(4, 4));
		levelBuilder.addWater(new Point(1, 9), new Point(5, 9));
		levelBuilder.addWater(new Point(0, 14), new Point(4, 14));
		levelBuilder.addWater(new Point(1, 19), new Point(5, 19));
		
		/* Center. ----------------------- */
		// Walls around Chip 
		levelBuilder.addWall(new Point(7, 12), new Point(14, 12)); // bottom
		levelBuilder.addWall(new Point(15, 12), new Point(15, 8)); // right
		levelBuilder.addWall(new Point(9, 8), new Point(14, 8)); // top
		
		
		
		
		/* Doors. ------------------------ */
		Door blueDoor = new BlueDoor(new Point(6, 22), this, scale);
		addDoor(blueDoor);		
		Door redDoor = new RedDoor(new Point(15, 15), this, scale);
		addDoor(redDoor);
		
	}
}
