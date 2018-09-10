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
	
	@Test
	// Verify the ability to change race strategies during the race.
	public void test5() {
		resetTest();
		
		Horse 	winner = null;
		boolean isDone = false;
		
		Horse slow1 = new Horse(0.0, "Slow1", 00, 0.1, new SteadyRunStrategy());
		Horse slow2 = new Horse(0.0, "Slow2", 00, 0.1, new SteadyRunStrategy());
		Horse slow3 = new Horse(0.0, "Slow3", 00, 0.1, new SteadyRunStrategy());
		Horse slow4 = new Horse(0.0, "Slow4", 00, 0.1, new SteadyRunStrategy());
		Horse slow5 = new Horse(0.0, "Slow5", 00, 0.1, new SteadyRunStrategy());
		this.horseRace.enrollHorse(slow1);
		this.horseRace.enrollHorse(slow2);
		this.horseRace.enrollHorse(slow3);
		this.horseRace.enrollHorse(slow4);
		this.horseRace.enrollHorse(slow5);

		// Loop for race.
		int i = 0;
		while(!isDone) {
			// Move horses.
			for (Horse horse : this.horseRace.getHorses()) {
				if (horse.getCurrentDistance() >= 10.0) {
					winner = horse;
					isDone = true;
					break;
				}
				if (i == 0) {
					slow3.setRaceStrategy(new EarlySprintStrategy());
				} else if (i == 20) {
					slow3.setRaceStrategy(new SteadyRunStrategy());					
				}
				
				horse.move();
				
				i = i + 1;
			}
		}
		
		assert(winner.getName() == "Slow3");		
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
