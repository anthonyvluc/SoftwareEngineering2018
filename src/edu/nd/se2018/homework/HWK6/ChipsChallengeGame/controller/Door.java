package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller;

import java.awt.Point;
import java.util.Observable;

import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model.DoorState;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model.LevelMap;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.view.DoorView;
import javafx.scene.image.Image;

public abstract class Door extends Observable {
	
	DoorState 	doorState;
	DoorView    doorView;
	Point 		coordinates;

	public Door(Point coordinates, LevelMap levelMap, int scale) {
		this.coordinates = coordinates;
		
		// Set initial state.
		doorState = new DoorState(coordinates, levelMap);
	}

	public Image getImage() {
		return doorView.getImage();
	}
	
	public Point getCoordinates() {
		return coordinates;
	}
}
