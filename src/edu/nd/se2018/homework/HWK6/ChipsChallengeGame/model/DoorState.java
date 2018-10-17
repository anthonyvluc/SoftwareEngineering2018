package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model;

import java.awt.Point;

public class DoorState {

	Point		doorCoordinates;
	IDoorState	openState;
	IDoorState	closedState;
	IDoorState	currentState;
	
	public DoorState(Point coordinates) {
		initializeStates();
		setCoordinates(coordinates);
	}
	
	private void initializeStates(){
		openState    = new Open(this);
		closedState  = new Closed(this);
		currentState = closedState;
	}
	
	public void setCoordinates(Point point){
		doorCoordinates = point;
	}
	
	public void setDoorState(IDoorState doorState){
		currentState = doorState;		
	}

	public IDoorState getClosedState() {
		return closedState;
	}
	
	public IDoorState getOpenState() {
		return openState;
	}
}
