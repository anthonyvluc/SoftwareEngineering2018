package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model;

import java.awt.Point;

public class ItemState {

	Point		coordinates;
	
	public ItemState(Point coordinates) {
		setCoordinates(coordinates);
	}
	
	public void setCoordinates(Point point){
		coordinates = point;
	}
	
}
