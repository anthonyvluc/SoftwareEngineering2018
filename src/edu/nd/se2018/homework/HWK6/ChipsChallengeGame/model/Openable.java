package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model;

public class Openable implements IDoorState {

	DoorState doorState;
	
	public Openable(DoorState doorState) {
		this.doorState = doorState;
	}

	@Override
	public void locking() {
		doorState.setDoorState(doorState.getClosedState());
	}

	@Override
	public void unlocking() {
		// Do nothing, already opened
	}

}
