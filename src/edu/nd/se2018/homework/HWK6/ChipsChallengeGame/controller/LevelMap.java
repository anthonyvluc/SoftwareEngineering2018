package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller;

import javafx.collections.ObservableList;
import javafx.scene.Node;

public class LevelMap {

	public enum Tile {
	    CHIP, FLOOR, WALL, DOOR
	}
	
	final int dimension;
	final int scale;
	
	public Tile[][] levelGrid;

	ObservableList<Node> root;
	
	public LevelMap(int dimension, int scale, ObservableList<Node> root) {
		// TODO Auto-generated constructor stub
		this.dimension = dimension;
		this.scale = scale;
		this.levelGrid = new Tile[dimension][dimension];
		
		this.root = root;
	}

	
}
