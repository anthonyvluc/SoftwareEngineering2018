package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller;

import java.awt.Point;
import java.util.Observable;

import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.LevelMap.Tile;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.view.ChipView;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.view.Direction;
import javafx.scene.image.ImageView;

public class Chip extends Observable {
	
	private Point coordinates;
	private LevelMap levelMap;

	ChipView chipView;
	
	public Chip(Point coordinates, LevelMap levelMap, int scale) {
		this.coordinates = coordinates;
		this.levelMap = levelMap;

		// Set initial image.
		this.chipView = new ChipView(this, scale, Direction.SOUTH);
	}

	
	public Point getCoordinates() {
		return this.coordinates;
	}

	public ImageView getImageView() {
		return this.chipView.getImageView();
	}
	
	public void move(Direction direction) {
		switch(direction) {
		case NORTH:
			goNorth();
			break;
		case SOUTH:
			goSouth();
			break;
		case EAST:
			goEast();
			break;
		case WEST:
			goWest();
			break;
		default:
			break;
		}
	}
	
	private void goNorth() {
		Point prevPosition = getCoordinates();
		Point newPosition = new Point(this.coordinates.x, this.coordinates.y - 1);
		if (levelMap.isValidChipPosition(newPosition)) {
			levelMap.levelGrid[prevPosition.x][prevPosition.y] = Tile.FLOOR;
			levelMap.levelGrid[newPosition.x][newPosition.y] = Tile.CHIP;
			coordinates.setLocation(newPosition);
		}
		this.chipView.setChipImageView(Direction.NORTH); // Update image.
	}


	private void goSouth() {
		Point prevPosition = getCoordinates();
		Point newPosition = new Point(this.coordinates.x, this.coordinates.y + 1);
		if (levelMap.isValidChipPosition(newPosition)) {
			levelMap.levelGrid[prevPosition.x][prevPosition.y] = Tile.FLOOR;
			levelMap.levelGrid[newPosition.x][newPosition.y] = Tile.CHIP;
			coordinates.setLocation(newPosition);
		}
		this.chipView.setChipImageView(Direction.SOUTH); // Update image.
	}


	private void goEast() {
		Point prevPosition = getCoordinates();
		Point newPosition = new Point(this.coordinates.x + 1, this.coordinates.y);
		if (levelMap.isValidChipPosition(newPosition)) {
			levelMap.levelGrid[prevPosition.x][prevPosition.y] = Tile.FLOOR;
			levelMap.levelGrid[newPosition.x][newPosition.y] = Tile.CHIP;
			coordinates.setLocation(newPosition);
		}
		this.chipView.setChipImageView(Direction.EAST); // Update image.
	}


	private void goWest() {
		Point prevPosition = getCoordinates();
		Point newPosition = new Point(this.coordinates.x - 1, this.coordinates.y);
		if (levelMap.isValidChipPosition(newPosition)) {
			levelMap.levelGrid[prevPosition.x][prevPosition.y] = Tile.FLOOR;
			levelMap.levelGrid[newPosition.x][newPosition.y] = Tile.CHIP;
			coordinates.setLocation(newPosition);
		}
		this.chipView.setChipImageView(Direction.WEST); // Update image.
	}
}
