package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model;

import java.awt.Point;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public abstract class LevelMap {

	final int dimension;
	final int scale;
	
	protected Point portalCoordinates;

	public Tile[][] levelGrid;

	ObservableList<Node> root;

	
	public LevelMap(int dimension, int scale, ObservableList<Node> root) {
		this.dimension = dimension;
		this.scale = scale;
		this.levelGrid = new Tile[dimension][dimension];
		
		this.root = root;
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
		if (levelGrid[position.x][position.y] != Tile.FLOOR) {
			bool = false;
		}
		return bool;
	}
	
	public Point getPortalCoordinates() {
		return portalCoordinates;
	}

	protected void setPortalCoordinates(Point point) {
		this.portalCoordinates = point;
	}	
}
