package edu.nd.se2018.homework.hwk2;

import org.junit.Test;

public class RaceTest {

	private Race 	horseRace;
	private Horse	fastHorse;
	private Horse	slowHorse;
	private Horse	mediumHorse;
	private Horse	slowerHorse;
	private Horse	sprinterHorse;

	@Test
	// Verify race accepts only five horses.
	public void test0() {		
		resetTest();
		
		this.horseRace.enrollHorse(this.fastHorse);
		this.horseRace.enrollHorse(this.slowHorse);
		
		// Premature start.
		this.horseRace.start();
		assert(this.horseRace.getWinner() == null);
		
		this.horseRace.enrollHorse(this.mediumHorse);
		this.horseRace.enrollHorse(this.slowerHorse);
		this.horseRace.enrollHorse(this.sprinterHorse);
		
		// All five horses.
		String[] names = {"Fast", "Slow", "Medium", "Slower", "Sprinter"};
		Horse[] horses =  this.horseRace.getHorses();
		for (int i = 0; i < 5; ++i) {
			assert(horses[i].getName() == names[i]);
		}

		// Attempt to add sixth horse.
		this.horseRace.enrollHorse(new Horse(0.0, "Test", 00, 0.0, new SteadyRunStrategy()));
		assert(horses[4].getName() == "Sprinter");
	}

	@Test
	// Test scenario with.
	public void test1() {
		resetTest();
	
		this.horseRace.enrollHorse(new Horse(0.0, "Amy", 00, 0.50, new EarlySprintStrategy()));
		this.horseRace.enrollHorse(new Horse(0.0, "Brian", 00, 0.50, new EarlySprintStrategy()));
		this.horseRace.enrollHorse(new Horse(0.0, "Carly", 00, 0.50, new SteadyRunStrategy()));
		this.horseRace.enrollHorse(new Horse(0.0, "Dan", 00, 0.50, new SlowStartStrategy()));
		this.horseRace.enrollHorse(new Horse(0.0, "Edmond", 00, 0.50, new SlowStartStrategy()));

		this.horseRace.start();

		assert(this.horseRace.getWinner().getName() == "Carly");
	}
	
	@Test
	// Test expected scenario.
	public void test2() {
		resetTest();
		
		this.horseRace.enrollHorse(this.fastHorse);
		this.horseRace.enrollHorse(this.sprinterHorse);
		// Filler horses.
		this.horseRace.enrollHorse(this.slowHorse);
		this.horseRace.enrollHorse(this.slowHorse);
		this.horseRace.enrollHorse(this.slowHorse);
	
		this.horseRace.start();
		
		assert(this.horseRace.getWinner().getName() == "Fast");
	}

	@Test
	// Test a tie situation.
	public void test3() {
		resetTest();

		this.horseRace.enrollHorse(this.fastHorse);
		Horse otherFastHorse = new Horse(0.0, "OtherFast", 00, 1.0, new SteadyRunStrategy());
		this.horseRace.enrollHorse(otherFastHorse);
		// Filler horses.
		this.horseRace.enrollHorse(this.slowHorse);
		this.horseRace.enrollHorse(this.slowHorse);
		this.horseRace.enrollHorse(this.slowHorse);

		this.horseRace.start();
		
		assert(this.horseRace.getWinner().getName() == "Fast");
	}
	
	@Test
	// Verify race forces horses to start at 0.0 mile.
	public void test4() {
		resetTest();

		this.horseRace.enrollHorse(new Horse(0.0, "Slow1", 00, 0.1, new SteadyRunStrategy()));
		this.horseRace.enrollHorse(new Horse(10.0, "Slow2", 00, 0.1, new SteadyRunStrategy()));
		this.horseRace.enrollHorse(new Horse(10.0, "Slow3", 00, 0.1, new SteadyRunStrategy()));
		this.horseRace.enrollHorse(new Horse(10.0, "Slow4", 00, 0.1, new SteadyRunStrategy()));
		this.horseRace.enrollHorse(new Horse(10.0, "Slow5", 00, 0.1, new SteadyRunStrategy()));

		this.horseRace.start();

		assert(this.horseRace.getWinner().getName() == "Slow1");
	}
	
	// Helper function to reset test variables.
	private void resetTest() {
		this.horseRace 		= new Race(true); // To avoid thread sleep.
		this.fastHorse 		= new Horse(0.0, "Fast", 00, 1.0, new SteadyRunStrategy());
		this.slowHorse 		= new Horse(0.0, "Slow", 00, 0.1, new SteadyRunStrategy());
		this.mediumHorse 	= new Horse(0.0, "Medium", 00, 0.5, new SteadyRunStrategy());
		this.slowerHorse 	= new Horse(0.0, "Slower", 00, 0.5, new SlowStartStrategy());
		this.sprinterHorse	= new Horse(0.0, "Sprinter", 00, 1.0, new EarlySprintStrategy());
	}
}
