package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model;

import java.awt.Point;

import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.BlueDoor;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.BlueKey;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.Chip;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.ChipItem;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.Portal;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.RedDoor;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.RedKey;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.view.ChipsChallengeUI;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public class LevelOneMap extends LevelMap {
	
	// Portal portal
	
	public LevelOneMap(int dimension, int scale, ObservableList<Node> root, ChipsChallengeUI chipsChallengeUI) {
		super(dimension, scale, root, chipsChallengeUI);
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
		levelBuilder.addWall(new Point(7, 12), new Point(18, 12)); // bottom
		levelBuilder.addWall(new Point(15, 12), new Point(15, 8)); // right
		levelBuilder.addWall(new Point(9, 8), new Point(14, 8)); // top
		
		/* Cross. ------------------------ */
		levelBuilder.addWall(new Point(11, 1), new Point(11, 6)); // Vertical
		levelBuilder.addWater(new Point(9, 3), new Point(24, 3)); // Horizontal
		levelBuilder.addWater(new Point(9, 4), new Point(24, 4)); // Horizontal
		// Right water
		levelBuilder.addWater(new Point(21, 9), new Point(21, 1));
		levelBuilder.addWater(new Point(22, 9), new Point(22, 1));
		
		/* Next room. -------------------- */
		levelBuilder.addWall(new Point(20, 12), new Point(24, 12)); // entrance
		levelBuilder.addWall(new Point(24, 15), new Point(8, 17)); // first wall
		levelBuilder.addWall(new Point(8, 15), new Point(24, 17)); // first wall
		levelBuilder.addWater(new Point(7, 20), new Point(21, 22)); // water
		levelBuilder.addWater(new Point(7, 21), new Point(21, 23)); // water
		
		/* Doors. ------------------------ */
		addDoor(new BlueDoor(new Point(6, 22), this, scale));
		addDoor(new RedDoor(new Point(19, 12), this, scale));
		
		/* Keys. ------------------------ */
		addKey(new BlueKey(new Point(22, 23), scale));
		addKey(new RedKey(new Point(17, 10), scale));

		/* Chip Items. ------------------- */
		addChipItem(new ChipItem(new Point(1, 1), scale));
		addChipItem(new ChipItem(new Point(23, 2), scale));
		addChipItem(new ChipItem(new Point(24, 5), scale));
		addChipItem(new ChipItem(new Point(11, 16), scale));
		addChipItem(new ChipItem(new Point(5, 12), scale));
		addChipItem(new ChipItem(new Point(0, 24), scale));		
		
	}
}
