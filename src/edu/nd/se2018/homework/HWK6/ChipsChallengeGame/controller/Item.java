package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller;

import java.awt.Point;
import java.util.Observable;

import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model.ItemState;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.view.ItemView;
import javafx.scene.image.Image;

public abstract class Item extends Observable {
	
	ItemState 	itemState;
	ItemView    itemView;
	Point 		coordinates;

	public Item(Point coordinates, int scale) {
		this.coordinates = coordinates;
		
		// Set initial state.
		itemState = new ItemState(coordinates);
	}

	public Image getImage() {
		return itemView.getImage();
	}
	
	public Point getCoordinates() {
		return coordinates;
	}
}
