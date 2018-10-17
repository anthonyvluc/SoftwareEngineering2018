package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model;

public class Running implements IChipState {

	ChipState chipState;
	
	public Running(ChipState chipState) {
		this.chipState = chipState;
	}

	@Override
	public void onFloorTile() {
		// Do nothing, already running
	}

	@Override
	public void onSlideTile() {
		chipState.setChipState(chipState.getFlyingState());	
	}

}
