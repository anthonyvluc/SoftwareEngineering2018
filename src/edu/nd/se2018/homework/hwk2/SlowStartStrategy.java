package edu.nd.se2018.homework.hwk2;

public class SlowStartStrategy implements RaceStrategy {

	@Override
	public double calculateDistance(Horse horse) {
		double nextDistance = 0;
		if (horse.getCurrentDistance() < 6.0) {
			nextDistance = horse.getMaxSpeed() * 0.75;
		} else if (horse.getCurrentDistance() < 9.0) {
			nextDistance = horse.getMaxSpeed() * 0.9;
		} else {
			nextDistance = horse.getMaxSpeed();			
		}
		return nextDistance;
	}

}
