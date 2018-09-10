package edu.nd.se2018.homework.hwk2;

public class Main {

	public static void main(String[] args) {
		// Declare a Race class.
		Race race = new Race();
		
		// Create five horses.
		Horse blitz = new Horse(0.0, "Blitz", 101, 0.5, new EarlySprintStrategy());
		
		// Enroll horses to race.
		race.enrollHorse(blitz);


		race.printStatus();

		// Add horses to race.
//		race.enrollHorse();
	}

}
