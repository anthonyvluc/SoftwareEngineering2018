package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller;

import java.awt.Point;
import java.util.Observable;

import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.LevelMap.Tile;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model.ChipState;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.view.ChipView;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.view.Direction;
import javafx.scene.image.ImageView;

public class Chip extends Observable {

	ChipState 	chipState;
	ChipView 	chipView;
	
	public Chip(Point coordinates, LevelMap levelMap, int scale) {
		
		// Set state.
		this.chipState = new ChipState(coordinates, levelMap);
		
		// Set initial image.
		this.chipView = new ChipView(this, scale, Direction.SOUTH);
	}

	public ImageView getImageView() {
		return this.chipView.getImageView();
	}
	
	public Point getCoordinates() {
		return chipState.getCoordinates();
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
		chipState.setPosition(0, -1);
		this.chipView.setChipImageView(Direction.NORTH); // Update image.
	}


	private void goSouth() {
		chipState.setPosition(0, 1);
		this.chipView.setChipImageView(Direction.SOUTH); // Update image.
	}


	private void goEast() {
		chipState.setPosition(1, 0);
		this.chipView.setChipImageView(Direction.EAST); // Update image.
	}


	private void goWest() {
		chipState.setPosition(-1, 0);
		this.chipView.setChipImageView(Direction.WEST); // Update image.
	}
}
