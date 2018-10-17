package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model;

import java.awt.Point;

public class KeyState {

	Point		keyCoordinates;
	IDoorState	openState;
	IDoorState	closedState;
	IDoorState	currentState;
	
	public KeyState(Point coordinates) {
		setCoordinates(coordinates);
	}
	
	public void setCoordinates(Point point){
		keyCoordinates = point;
	}
	
}
