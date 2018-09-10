package edu.nd.se2018.homework.hwk2;

public class Race {

	private Horse[] horses;
	private Horse 	winner;
	private double 	distance 	= 10.0;
	private int 	numHorses 	= 0;
	private int 	limit 		= 5;	// Exactly five horses
	
	Race() {
		this.horses = new Horse[this.limit];
	}
	
	void begin() {
		
	}
	
	void run() {
		
	}
	
	void enrollHorse(Horse horse) {
		if (this.numHorses < this.limit) {
			this.horses[this.numHorses] = horse;
			this.numHorses += this.numHorses;			
		}
	}
	
	void announceWinner() {
		System.out.println("The winner is: " + this.winner.getName() + " #" + this.winner.getNumber() + "!");
	}

	void setWinner(Horse winner) {
		this.winner = winner;
	}
	
	void printStatus() {
		for (Horse horse : horses) {
			if (horse != null) {
				System.out.println(horse.getName() + " has run " + horse.getCurrentDistance() + " miles");
			}
		}
	}
	
}
