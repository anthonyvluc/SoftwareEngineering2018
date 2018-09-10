package edu.nd.se2018.homework.hwk2;

public class Main {

	public static void main(String[] args) {
		// Declare a Race class.
		Race race = new Race();
		
		// Create five horses.
		Horse blitz = new Horse(0.0, "Blitz", 101, 0.5, new EarlySprintStrategy());
		Horse pharoah = new Horse(0.0, "Pharoah", 201, 1.0, new SteadyRunStrategy());
		Horse otto = new Horse(0.0, "Otto", 301, 0.7, new SlowStartStrategy());
		Horse princess = new Horse(0.0, "Princess", 401, 0.6, new EarlySprintStrategy());
		Horse ahri = new Horse(0.0, "Ahri", 501, 0.4, new SlowStartStrategy());
		
		// Enroll horses to race.
		race.enrollHorse(blitz);
		race.enrollHorse(pharoah);
		race.enrollHorse(otto);
		race.enrollHorse(princess);
		race.enrollHorse(ahri);

		// Start race.
		race.start();
		
		// Announce winner,
		race.announceWinner();
	}

}
