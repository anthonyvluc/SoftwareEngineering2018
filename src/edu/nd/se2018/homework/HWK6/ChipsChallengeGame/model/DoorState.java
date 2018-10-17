package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model;

import java.awt.Point;

public class DoorState {

	Point		doorCoordinates;
	IDoorState	openState;
	IDoorState	closedState;
	IDoorState	currentState;
	
	private LevelMap levelMap;
	
	public DoorState(Point coordinates, LevelMap levelMap) {
		this.levelMap = levelMap;
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
		levelMap.levelGrid[point.x][point.y] = Tile.DOOR;
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
