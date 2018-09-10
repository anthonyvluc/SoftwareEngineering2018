package edu.nd.se2018.homework.hwk2;

public class Race {

	private Horse[] horses;
	private Horse 	winner		= null;
	private double 	totalMiles 	= 10.0;
	private int 	limit 		= 5;	// Exactly five horses
	private int 	numHorses 	= 0;
	private boolean test;
	
	Race(boolean test) {
		this.test = test;
		this.horses = new Horse[this.limit];
	}
	
	public void start() {
		if (this.numHorses != this.limit) {
			if (!test) {
				System.out.println("Please enroll more horses before starting the race.");	
			}
		} else {
			// Ensure all horses start at beginning of race.
			for (Horse horse : horses) {
				if (horse != null) {
					horse.setCurrentDistance(0.0);
				}
			}
			
			if (!test) {
				System.out.println("Starting race in");

				for (int i = 3; i > 0; --i) {
					System.out.println(i + "...");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				System.out.println("GO!!!");
			}

			// Run race.
			run();				
		}
	}
	
	private void run() {
		boolean isDone = false;

		// Loop for race.
		while(!isDone) {
			// Move horses.
			for (Horse horse : horses) {
				if (horse.getCurrentDistance() >= this.totalMiles) {
					setWinner(horse);
					isDone = true;
					break;
				}
				horse.move();				
			}

			if (!isDone) {
				// Sleep.
				if (!test) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					// Print race status.
					printStatus();					
				}
			}
		}
	}
	
	public void enrollHorse(Horse horse) {
		if (this.numHorses < this.limit) {
			this.horses[this.numHorses] = horse;
			this.numHorses = this.numHorses + 1;
		}
	}
	
	public void announceWinner() {
		if (winner != null) {
			System.out.println("\n~#~#~#~#~#~#~#~#~#~#~#~#~#~#~");
			System.out.println("The winner is: " + this.winner.getName() + " #" + this.winner.getNumber() + "!");			
		} else {
			System.out.println("There is no winner yet!");			
		}
	}

	public void setWinner(Horse winner) {
		this.winner = winner;
	}
	
	public void printStatus() {
		System.out.println("#############################");
		for (Horse horse : horses) {
			if (horse != null) {
				System.out.format("%s has run %.2f miles\n", horse.getName(), horse.getCurrentDistance());
			}
		}
	}
	
	public Horse[] getHorses() {
		return this.horses;
	}
	
	public Horse getWinner() {
		return this.winner;
	}
}
