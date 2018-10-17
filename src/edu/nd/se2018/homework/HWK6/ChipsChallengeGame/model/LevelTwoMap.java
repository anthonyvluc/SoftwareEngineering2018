package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model;

import java.awt.Point;

import javafx.collections.ObservableList;
import javafx.scene.Node;

public class LevelTwoMap extends LevelMap {

	public LevelTwoMap(int dimension, int scale, ObservableList<Node> root) {
		super(dimension, scale, root);

		// TODO: only set point of portal, dont call load
		setPortalCoordinates(new Point(12, 10));
		loadInitialMap();
	}
}
