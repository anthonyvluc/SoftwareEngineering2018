package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller;

import java.awt.Point;
import java.util.Observable;

import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model.KeyState;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.view.KeyView;
import javafx.scene.image.Image;

public abstract class Key extends Observable {
	
	KeyState 	keyState;
	KeyView     keyView;
	Point 		coordinates;

	public Key(Point coordinates, int scale) {
		this.coordinates = coordinates;
		
		// Set initial state.
		keyState = new KeyState(coordinates);
	}

	public Image getImage() {
		return keyView.getImage();
	}
	
	public Point getCoordinates() {
		return coordinates;
	}
}
