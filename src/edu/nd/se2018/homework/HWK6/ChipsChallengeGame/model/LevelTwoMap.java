package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model;

import java.awt.Point;

import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.BlueDoor;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.BlueKey;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.Chip;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.ChipItem;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.Door;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.Item;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.Portal;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.RedDoor;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.RedKey;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.view.ChipsChallengeUI;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public class LevelTwoMap extends LevelMap {

	public LevelTwoMap(int dimension, int scale, ObservableList<Node> root, ChipsChallengeUI chipsChallengeUI) {
		super(dimension, scale, root, chipsChallengeUI);
	}

	@Override
	public void generateLevel() {
		// Set Chip location.
		chip = new Chip(new Point(12, 14), this, scale);

		// Set portal location.
		this.portal = new Portal(new Point(20, 5), this, scale);
		
		/* Big horizontal wall. -------------------- */
		levelBuilder.addWall(new Point(1, 7), new Point(24, 7));
		levelBuilder.addWall(new Point(1, 8), new Point(24, 8));
		levelBuilder.addWall(new Point(1, 9), new Point(24, 9));
		levelBuilder.addWall(new Point(1, 10), new Point(24, 10));
		levelBuilder.addWall(new Point(1, 11), new Point(24, 11));
		
		/* Water above big wall. ------------------- */
		levelBuilder.addWater(new Point(1, 6), new Point(24, 6));
		levelBuilder.addWater(new Point(1, 1), new Point(1, 5));
		levelBuilder.addWater(new Point(2, 1), new Point(2, 5));
		levelBuilder.addWater(new Point(3, 1), new Point(3, 5));
		levelBuilder.addWater(new Point(5, 0), new Point(5, 4));
		levelBuilder.addWater(new Point(6, 0), new Point(6, 4));
		levelBuilder.addWater(new Point(7, 0), new Point(7, 4));
		levelBuilder.addWater(new Point(9, 1), new Point(9, 5));
		levelBuilder.addWater(new Point(10, 1), new Point(10, 5));
		levelBuilder.addWater(new Point(11, 1), new Point(11, 5));
		levelBuilder.addWater(new Point(13, 0), new Point(13, 4));
		levelBuilder.addWater(new Point(14, 0), new Point(14, 4));
		levelBuilder.addWater(new Point(15, 0), new Point(15, 4));
		levelBuilder.addWater(new Point(17, 2), new Point(17, 5));
		levelBuilder.addWater(new Point(18, 2), new Point(18, 5));
		levelBuilder.addWater(new Point(19, 2), new Point(19, 5));
		levelBuilder.addWater(new Point(21, 2), new Point(21, 5));
		levelBuilder.addWater(new Point(22, 2), new Point(22, 5));
		levelBuilder.addWater(new Point(23, 2), new Point(23, 5));

		/* Big X's. -------------------------------- */
		levelBuilder.addWall(new Point(2, 15), new Point(7, 20)); // \
		levelBuilder.addWall(new Point(0, 22), new Point(7, 15)); // /
		levelBuilder.addWall(new Point(12, 16), new Point(5, 23)); // /
		
		/* Around blue key. ------------------------ */
		// The lake
		levelBuilder.addWater(new Point(15, 14), new Point(23, 14));
		levelBuilder.addWater(new Point(15, 15), new Point(23, 15));
		levelBuilder.addWater(new Point(15, 16), new Point(23, 16));
		levelBuilder.addWater(new Point(15, 17), new Point(23, 17));
		levelBuilder.addWater(new Point(15, 18), new Point(23, 18));
		// Room
		levelBuilder.addWall(new Point(14, 21), new Point(24, 21)); // ------
		levelBuilder.addWall(new Point(17, 22), new Point(17, 23)); //   |
		
		/* Doors. ---------------------------------- */
		addDoor(new BlueDoor(new Point(0, 7), this, scale));
		addDoor(new RedDoor(new Point(0, 8), this, scale));
		// Guarding blue key.
		addDoor(new RedDoor(new Point(17, 24), this, scale));
		
		/* Keys. ----------------------------------- */
		addKey(new BlueKey(new Point(22, 23), scale));
		addKey(new RedKey(new Point(0, 20), scale));
		
		/* Chip Items. ----------------------------- */
		addChipItem(new ChipItem(new Point(4, 19), scale));
		addChipItem(new ChipItem(new Point(8, 19), scale));
		addChipItem(new ChipItem(new Point(24, 24), scale));
		addChipItem(new ChipItem(new Point(24, 15), scale));
		addChipItem(new ChipItem(new Point(24, 5), scale));
		addChipItem(new ChipItem(new Point(12, 22), scale));
		
	}
}
