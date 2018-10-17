package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model;

import java.awt.Point;

public class ChipState {

	Point chipCoordinates;
	IMovementState runningState;
	IMovementState slidingState;
	IMovementState currentState;
	private LevelMap levelMap;

	public ChipState(Point coordinates, LevelMap levelMap) {
		this.levelMap = levelMap;
		initializeStates();
		setCoordinates(coordinates);
	}

	private void initializeStates(){
		runningState = new Running(this);
		slidingState = new Sliding(this);
		currentState = runningState;
	}

	public Point getCoordinates(){
		return chipCoordinates;
	}
	
	public void setCoordinates(Point point){
		chipCoordinates = point;
	}

	public void setChipState(IMovementState chipState){
		currentState = chipState;		
	}
	
	public void setPosition(int dx, int dy) {
		Point currentPosition = getCoordinates();
		Point newPosition = new Point(currentPosition.x + dx, currentPosition.y + dy);
		if (levelMap.isValidChipPosition(newPosition)) {
			levelMap.levelGrid[currentPosition.x][currentPosition.y] = Tile.FLOOR;
			levelMap.levelGrid[newPosition.x][newPosition.y] = Tile.CHIP;
			chipCoordinates.setLocation(newPosition);
		}
	}

	public IMovementState getFlyingState() {
		return currentState;
	}

	public IMovementState getRunningState() {
		return runningState;
	}

	public IMovementState getSlidingState() {
		return slidingState;
	}
}
