package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller;

import java.awt.Point;
import java.util.Observable;

import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.LevelMap.Tile;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Chip extends Observable {
	
	public enum Direction {
	    NORTH, SOUTH, EAST, WEST
	}
	
	private int scale;
	private Point coordinates;
	private LevelMap levelMap;

	Image chipImage;
	ImageView chipImageView;
	
	
	public Chip(Point coordinates, LevelMap levelMap, int scale) {
		this.coordinates = coordinates;
		this.levelMap = levelMap;
		this.scale = scale;
		
//		setChipImage(this);
		
		// Set image.
		chipImage = new Image("images/chip/textures/chipDown.png", scale, scale, true, true);
		chipImageView = new ImageView(chipImage);
		chipImageView.setX(coordinates.x * scale);
		chipImageView.setY(coordinates.y * scale);
	}

	/* To move to image in view*/
//	private void setChipImage(Chip chip){
//		Image chipImage = new Image("images/chip/textures/chipDown.png",100,100,true,true);	
//		chipImageView = new ImageView(chipImage);
//		chipImageView.setX(chip.getCoordinates().x);
//		chipImageView.setY(chip.getCoordinates().y);
//	}

	
	public Point getCoordinates() {
		return this.coordinates;
	}

	public Node getImageView() {
		return chipImageView;
	}
	
	public void move(Direction direction) {
		Point prevPosition = getCoordinates();

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

		// Update tile
		chipImageView.setX(getCoordinates().x*scale);
		chipImageView.setY(getCoordinates().y*scale);
		
		levelMap.levelGrid[prevPosition.x][prevPosition.y] = Tile.FLOOR; // TODO: update this and fix 
		levelMap.levelGrid[getCoordinates().x][getCoordinates().y] = Tile.CHIP;
	}
	
	private void goNorth() {
		Point newPosition = new Point(this.coordinates.x, this.coordinates.y - 1);
		if (levelMap.isValidChipPosition(newPosition)) {
			levelMap.levelGrid[coordinates.x][coordinates.y] = Tile.CHIP;
			coordinates.setLocation(newPosition);
		}
		
		// TODO: make this into a view
		Image northChipImage = new Image("images/chip/textures/chipUp.png", scale, scale, true, true);
		chipImageView.setImage(northChipImage);
	}


	private void goSouth() {
		Point newPosition = new Point(this.coordinates.x, this.coordinates.y + 1);
		if (levelMap.isValidChipPosition(newPosition)) {
			levelMap.levelGrid[coordinates.x][coordinates.y] = Tile.CHIP;
			coordinates.setLocation(newPosition);
		}
		
		// TODO: make this into a view
		Image southChipImage = new Image("images/chip/textures/chipDown.png", scale, scale, true, true);
		chipImageView.setImage(southChipImage);
	}


	private void goEast() {
		Point newPosition = new Point(this.coordinates.x + 1, this.coordinates.y);
		if (levelMap.isValidChipPosition(newPosition)) {
			levelMap.levelGrid[coordinates.x][coordinates.y] = Tile.CHIP;
			coordinates.setLocation(newPosition);
		}
		
		// TODO: make this into a view
		Image eastChipImage = new Image("images/chip/textures/chipRight.png", scale, scale, true, true);
		chipImageView.setImage(eastChipImage);
	}


	private void goWest() {
		Point newPosition = new Point(this.coordinates.x - 1, this.coordinates.y);
		if (levelMap.isValidChipPosition(newPosition)) {
			levelMap.levelGrid[coordinates.x][coordinates.y] = Tile.CHIP;
			coordinates.setLocation(newPosition);
		}
		
		// TODO: make this into a view
		Image westChipImage = new Image("images/chip/textures/chipLeft.png", scale, scale, true, true);
		chipImageView.setImage(westChipImage);
	}
}
