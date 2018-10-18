package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller;

import java.awt.Point;
import java.util.Collection;
import java.util.Observable;

import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model.ChipState;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model.LevelMap;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.view.ChipView;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.view.Direction;
import javafx.scene.image.ImageView;

public class Chip extends Observable {

	ChipState 	chipState;
	ChipView 	chipView;
	Direction	currentDirection;
	
	public Chip(Point coordinates, LevelMap levelMap, int scale) {
		
		// Set state.
		this.chipState = new ChipState(coordinates, levelMap);
		
		// Set initial image.
		this.currentDirection = Direction.SOUTH;
		this.chipView = new ChipView(this, scale, currentDirection);
		
	}

	public ImageView getImageView() {
		return chipView.getImageView();
	}
	
	public void updateImageView() {
		chipView.setImageView(currentDirection);
	}
	
	public Point getCoordinates() {
		return chipState.getCoordinates();
	}
	
	public void addItem(Item item) {
		chipState.addItem(item);
	}
	
	private Collection<Item> getInventory() {
		return chipState.getInventory();
	}
	
	public boolean hasBlueKey() {
		boolean bool = false;
		for (Item i: getInventory()) {
			if (i instanceof BlueKey) {
				bool = true;
			}
		}
		return bool;
	}
	
	public boolean hasRedKey() {
		boolean bool = false;
		for (Item i: getInventory()) {
			if (i instanceof RedKey) {
				bool = true;
			}
		}
		return bool;
	}
	
	/* Movement methods. ------------------- */
	public void move(Direction direction) {
		currentDirection = direction;

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

		setChanged();
		notifyObservers();
	}
	
	private void goNorth() {
		chipState.setPosition(0, -1);
	}


	private void goSouth() {
		chipState.setPosition(0, 1);
	}


	private void goEast() {
		chipState.setPosition(1, 0);
	}


	private void goWest() {
		chipState.setPosition(-1, 0);
	}
}
