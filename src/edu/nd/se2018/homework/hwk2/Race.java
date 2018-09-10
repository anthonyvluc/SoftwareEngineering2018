package edu.nd.se2018.homework.hwk2;

public class Race {

	private Horse[] horses;
	private Horse 	winner;
	private double 	totalMiles 	= 10.0;
	private int 	numHorses 	= 0;
	private int 	limit 		= 5;	// Exactly five horses
	
	Race() {
		this.horses = new Horse[this.limit];
	}
	
	public void start() {
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

		run();
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
	
	public void enrollHorse(Horse horse) {
		if (this.numHorses < this.limit) {
			this.horses[this.numHorses] = horse;
			this.numHorses = this.numHorses + 1;
		}
	}
	
	public void announceWinner() {
		System.out.println("\n~#~#~#~#~#~#~#~#~#~#~#~#~#~#~");
		System.out.println("The winner is: " + this.winner.getName() + " #" + this.winner.getNumber() + "!");
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
	
}
