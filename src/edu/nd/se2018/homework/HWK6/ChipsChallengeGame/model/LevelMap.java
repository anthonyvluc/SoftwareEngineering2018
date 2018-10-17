package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model;

import java.awt.Point;

import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.Chip;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.LevelBuilder;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.Portal;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public abstract class LevelMap {

	Chip 	chip;
	Portal 	portal;

	LevelBuilder levelBuilder;
	
	final int dimension;
	final int scale;

	public Tile[][] levelGrid;

	ObservableList<Node> root;

	
	public LevelMap(int dimension, int scale, ObservableList<Node> root) {
		this.dimension = dimension;
		this.scale = scale;
		this.levelGrid = new Tile[dimension][dimension];
		
		this.root = root;
		
		loadInitialMap(); // Load initial as all floors.
		
		levelBuilder = new LevelBuilder(this);
	}

	public void drawLevel(ObservableList<Node> root) {
		for (int i = 0; i < dimension; ++i) {
			for (int j = 0; j < dimension; ++j) {
				Rectangle rect = new Rectangle(i*scale, j*scale, scale, scale);
				switch(levelGrid[i][j]) {
					case CHIP:
//						root.add();
						break;
					case FLOOR:
						Image tileImage = new Image("images/chip/textures/BlankTile.png", scale, scale, true, true);
						rect.setFill(new ImagePattern(tileImage));
						root.add(rect);
						break;
					case PORTAL:
						Image portalImage = portal.getImage();
						rect.setFill(new ImagePattern(portalImage));
						root.add(rect);
						break;
					case WALL:
						Image wallImage = new Image("images/chip/textures/grayWall.png", scale, scale, true, true);
						rect.setFill(new ImagePattern(wallImage));
						root.add(rect);
						break;
					default:
						break;
				}
			}
		}
	}

	protected void loadInitialMap() {
		for (int i = 0; i < dimension; ++i) {
			for (int j = 0; j < dimension; ++j) {
				levelGrid[i][j] = Tile.FLOOR;
			}
		}
	}
	
	protected boolean isValidChipPosition(Point position) {
		boolean bool = true;
		Tile t;

		try {
			t = levelGrid[position.x][position.y];
		} catch (ArrayIndexOutOfBoundsException exception) {
			// Make out of bounds a wall
			t = Tile.WALL;
		}
		
		if (t != Tile.PORTAL && t != Tile.FLOOR) {
			bool = false;
		}
		return bool;
	}
	
	public Chip getChip() {
		return chip;
	}
	
	public Point getPortalCoordinates() {
		return portal.getCoordinates();
	}

	public abstract void generateLevel();
}
