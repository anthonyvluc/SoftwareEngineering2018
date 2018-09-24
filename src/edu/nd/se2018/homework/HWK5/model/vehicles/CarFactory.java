package edu.nd.se2018.homework.HWK5.model.vehicles;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;

import edu.nd.se2018.homework.HWK5.model.infrastructure.Direction;
import edu.nd.se2018.homework.HWK5.model.infrastructure.gate.CrossingGate;


/**
 * Very basic car factory.  Creates the car and registers it with the crossing gate and the car infront of it.
 * @author jane
 *
 */
public class CarFactory {
	
	private Collection<CrossingGate> gates = null;
	private ArrayList<Car> cars = new ArrayList<Car>();
	Direction direction;
	Point location;
	
	public CarFactory() {}
	
	public CarFactory(Direction direction, Point location, Collection<CrossingGate> gates){
		this.direction 	= direction;
		this.location 	= location;
		this.gates 		= gates;
	}
	
	
	// Most code here is to create random speeds
	public Car buildCar() {
		Car car = null;
		Car previousCar = null;
		
		// Get the index of the last car in the array list.
		int numCars = cars.size();
		
		// There exists a previous car.
		if (numCars >= 1) {
			previousCar = cars.get(numCars-1);
		}
		
		if (numCars == 0 || location.y < previousCar.getVehicleY()-100) {
			// Initialize car
			car = new Car(location.x,location.y);	
			double speedVariable = (Math.random() * 10)/10;
			car.setSpeed((2-speedVariable)*1.5); 
			car.setDirection(direction);
			
			// All cars created by this factory must be aware of crossing gates in the road
			for(CrossingGate gate: gates){
				gate.addObserver(car);
				if(gate != null && gate.getTrafficCommand()=="STOP") {
					car.setGateDownFlag(true);					
				}
			}
			
			// Each car must observe the car in front of it so it doesn't collide with it.
			if (previousCar != null) {
				previousCar.addObserver(car);				
			}
			
			cars.add(car);
		}

		return car;
	}

	// We will get a concurrency error if we try to delete cars whilst iterating through the array list
	// so we perform this in two stages.
	// 1.  Loop through the list and identify which cars are off the screen.  Add them to 'toDelete' array.
	// 2.  Iterate through toDelete and remove the cars from the original arrayList.
	public ArrayList<Car> removeOffScreenCars() {
		// Removing cars from the array list.
		ArrayList<Car> toDelete = new ArrayList<Car>();
		for (Car car: cars) {
			car.move();					
			if (car.offScreen()) {
				toDelete.add(car);				
			}
		}   
		for (Car car: toDelete) {
			cars.remove(car);			
		}
		return toDelete;
	}
	
	public ArrayList<Car> getCarList() {
		return cars;
	}
}
