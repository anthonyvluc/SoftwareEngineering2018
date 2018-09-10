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
		
		run();
	}
	
	void run() {
		boolean isDone = false;
		while(!isDone) {
			for (Horse horse : horses) {
				if (horse.getCurrentDistance() >= this.totalMiles) {
					setWinner(horse);
					isDone = true;
				}
				horse.move();
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
		System.out.println("The winner is: " + this.winner.getName() + " #" + this.winner.getNumber() + "!");
	}

	public void setWinner(Horse winner) {
		this.winner = winner;
	}
	
	public void printStatus() {
		for (Horse horse : horses) {
			if (horse != null) {
				System.out.println(horse.getName() + " has run " + horse.getCurrentDistance() + " miles");
			}
		}
	}
	
}
