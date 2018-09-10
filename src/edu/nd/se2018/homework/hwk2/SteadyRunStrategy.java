package edu.nd.se2018.homework.hwk2;

public class SteadyRunStrategy implements RaceStrategy {

	@Override
	public double calculateDistance(Horse horse) {
		double nextDistance = 0;
		nextDistance = horse.getMaxSpeed() * 0.8;
		return nextDistance;
	}

}
