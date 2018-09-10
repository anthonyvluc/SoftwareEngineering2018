package edu.nd.se2018.homework.hwk2;

public class Horse {

	private double 			currentDistance;
	private String 			name;
	private int 			number;
	private double			maxSpeed;
	private RaceStrategy 	raceStrategy;
	
	Horse(double currentDistance, 
		  String name,
		  int number,
		  double maxSpeed,
		  RaceStrategy raceStrategy) {
		// Initialize variables.
		setCurrentDistance(currentDistance);
		setName(name);
		setNumber(number);
		setMaxSpeed(maxSpeed);
		setRaceStrategy(raceStrategy);
	}
	
	void setCurrentDistance(double currentDistance) {
		this.currentDistance = currentDistance;
	}
	
	void setName(String name) {
		this.name = name;
	}
	
	void setNumber(int number) {
		this.number = number;	
	}
	
	void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	
	void setRaceStrategy(RaceStrategy raceStrategy) {
		this.raceStrategy = raceStrategy;
	}
	
	String getName() {
		return this.name;
	}
	
	double getCurrentDistance() {
		return this.currentDistance;
	}
	
	int getNumber() {
		return this.number;
	}
	
	void move() {
		// TODO
	}
}
