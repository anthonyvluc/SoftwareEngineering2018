package edu.nd.se2018.homework.hwk2;

public class EarlySprintStrategy implements RaceStrategy {

	@Override
	public double calculateDistance(Horse horse) {
		// TODO Auto-generated method stub
		double nextDistance = 0;
		if (horse.getCurrentDistance() < 2.0) {
			nextDistance = horse.getMaxSpeed();
		} else {
			nextDistance = horse.getMaxSpeed() * 0.75;			
		}
		return nextDistance;
	}

}
