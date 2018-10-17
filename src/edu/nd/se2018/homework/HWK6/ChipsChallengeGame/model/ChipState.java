package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model;

import java.awt.Point;

public class ChipState {

	Point chipCoordinates;
	IChipState runningState;
	IChipState slidingState;
	IChipState currentState;

	public ChipState() {
		initializeStates();
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

	
	public void setChipState(IChipState chipState){
		currentState = chipState;		
	}

	public IChipState getFlyingState() {
		return currentState;
	}

	public IChipState getRunningState() {
		return runningState;
	}

	public IChipState getSlidingState() {
		return slidingState;
	}
}
