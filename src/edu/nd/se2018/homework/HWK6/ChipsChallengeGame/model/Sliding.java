package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model;

public class Sliding implements IChipState {

	ChipState chipState;
	
	public Sliding(ChipState chipState) {
		this.chipState = chipState;
	}

	@Override
	public void onFloorTile() {
		chipState.setChipState(chipState.getRunningState());
	}

	@Override
	public void onSlideTile() {
		// Do nothing, already sliding.
	}

}
