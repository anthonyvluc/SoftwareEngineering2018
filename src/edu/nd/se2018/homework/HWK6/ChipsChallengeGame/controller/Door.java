package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model.DoorState;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model.LevelMap;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.view.DoorView;
import javafx.scene.image.Image;

public abstract class Door extends Observable implements Observer {
	
	DoorState 	doorState;
	DoorView    doorView;
	Point 		coordinates;
	protected LevelMap levelMap;	
	protected final int scale;
	
	public Image openedDoor;

	public Door(Point coordinates, LevelMap levelMap, int scale) {
		this.coordinates = coordinates;
		this.levelMap = levelMap;
		this.scale = scale;
		
		openedDoor = new Image("images/chip/textures/BlankTile.png", scale, scale, true, true);
		
		// Set initial state.
		doorState = new DoorState(coordinates, levelMap);
	}
	
	public Image getImage() {
		return doorView.getImage();
	}
	
	public void setImage(Image i) {
		doorView.setImage(i);
	}
	
	public Point getCoordinates() {
		return coordinates;
	}
}
