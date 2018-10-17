package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model;

public class Closed implements IDoorState {

	DoorState doorState;
	
	public Closed(DoorState doorState) {
		this.doorState = doorState;
	}

	@Override
	public void locking() {
		// Do nothing, already closed.
	}

	@Override
	public void unlocking() {
		doorState.setDoorState(doorState.getOpenState());
	}
}
