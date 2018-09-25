package edu.nd.se2018.homework.HWK5;


import java.util.ArrayList;
import java.util.Collection;

import edu.nd.se2018.homework.HWK5.model.infrastructure.Direction;
import edu.nd.se2018.homework.HWK5.model.infrastructure.MapBuilder;
import edu.nd.se2018.homework.HWK5.model.infrastructure.RailwayTracks;
import edu.nd.se2018.homework.HWK5.model.infrastructure.Road;
import edu.nd.se2018.homework.HWK5.model.infrastructure.gate.CrossingGate;
import edu.nd.se2018.homework.HWK5.model.vehicles.Car;
import edu.nd.se2018.homework.HWK5.model.vehicles.Train;
import edu.nd.se2018.homework.HWK5.view.MapDisplay;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Simulation extends Application{
	
	private Pane root;
	private Scene scene;
	private MapBuilder mapBuilder;
	private MapDisplay mapDisplay;
	
	ArrayList<Train> trains = new ArrayList<Train>();
	ArrayList<Car> carsInMiddleRoad = new ArrayList<Car>();
	
	@Override  
	public void start(Stage stage) throws Exception {
		
		root = new Pane();
		
		// Build infrastructure
		mapBuilder = new MapBuilder();
		mapDisplay = new MapDisplay(root, mapBuilder.getRoads(), mapBuilder.getTracks(),mapBuilder.getAllGates());					
		mapDisplay.drawTracks();
		mapDisplay.drawRoad();
		mapDisplay.drawGate();

		scene = new Scene(root,1200,1000);
		stage.setTitle("Railways");
		stage.setScene(scene);
		stage.show();

		// Create Royal and Solaris Train
		CrossingGate gateOne = mapBuilder.getGate("Gate1");
		CrossingGate gateTwo = mapBuilder.getGate("Gate2");
		createTrain("Royal", 2, gateOne, gateTwo, Direction.WEST);
		createTrain("Solaris", 3, gateOne, gateTwo, Direction.EAST);

		// Sets up a repetitive loop i.e., in handle that runs the actual simulation
		new AnimationTimer(){

			@Override
			public void handle(long now) {
			
				createCar();

				// Handle car moving to from east road to west
				determineRoadChange();

				// Operate train and gates
				for (Train train: trains) {
					train.move();				
				}				
				for(CrossingGate gate: mapBuilder.getAllGates()) {
					gate.operateGate();					
				}
				
				// Handle off-screen objects
				for (Train train: trains) {
					if (train.offScreen())
						train.reset();					
				}						
				clearCars();				
			}
		}.start();
	}
	
	// Clears cars as they leave the simulation
	private void clearCars(){
		Collection<Road> roads = mapBuilder.getRoads();
		for(Road road: roads){			
			if (road.getCarFactory()!= null){
				ArrayList<Car> junkCars = road.getCarFactory().removeOffScreenCars();	
				mapDisplay.removeCarImages(junkCars);
			}
		}
	}
	
	private void createCar(){
		Collection<Road> roads = mapBuilder.getRoads();
		for(Road road: roads){
			if (road.getCarFactory() != null){
				if ((int)(Math.random() * 100) == 15){
					Car car = road.getCarFactory().buildCar();
					if (car != null){
						root.getChildren().add(car.getImageView());
					}
				}
			}
		}
	}

	private void createTrain(String trainName, int speed, CrossingGate gateOne, CrossingGate gateTwo, Direction direction) {
		RailwayTracks tracks = mapBuilder.getTrack(trainName);
		Train train;
		if (direction == Direction.WEST) {
			train = new Train(tracks.getEndX()+100,tracks.getEndY()-25, speed, direction);
		} else {
			// Assume direction is EAST
			train = new Train(tracks.getStartX()-100,tracks.getStartY()-25, speed, direction);
		}
		root.getChildren().add(train.getImageView());

		train.addObserver(gateOne);
		train.addObserver(gateTwo);
		
		trains.add(train);
	}
	
	private void determineRoadChange() {
		
		Road skywayRoad = mapBuilder.getRoad("Skyway");
		Road eastWestRoad = mapBuilder.getRoad("EastWest");
		Road westHighwayRoad = mapBuilder.getRoad("Western Highway");
		ArrayList<Car> skywayCarList = skywayRoad.getCarFactory().getCarList();
		ArrayList<Car> westHighwayCarList = westHighwayRoad.getCarFactory().getCarList();

		// Determine which car to move.
		Car carToMove = null;
		for (Car car: skywayCarList) {
			if (Math.abs(eastWestRoad.getEndY()-car.getVehicleY()-eastWestRoad.getRoadWidth())<=5) {
				// If the car is within range to turn onto the connecting road
				if (((int)(Math.random()*5)) == 3) {
					// 1/5 chance it decides to take road to western road.
					carToMove = car;
					carsInMiddleRoad.add(carToMove);
					break;
				}
			}
		}
		
		// Move car to other road.
		if (carToMove != null) {
			// Get index of car to move.
			int carToMoveIndex = skywayCarList.indexOf(carToMove);

			// Set movement direction of car.
			carToMove.setDirection(eastWestRoad.getDirection());
			
			/* Handle skyway car and observer list. */
			Car followingCar = null;
			Car leadingCar = null;
			try {
				// Get instances of previous and next cars
				followingCar = skywayCarList.get(carToMoveIndex+1);
				leadingCar = skywayCarList.get(carToMoveIndex-1);
			} catch (IndexOutOfBoundsException e) {
				// Do nothing, allow it to be null
			}
			
			if (followingCar != null) {
				// Make the next car no longer follow this car.
				followingCar.deleteObserver(carToMove);

				// Make the previous car observe the car ahead.				
				if (leadingCar != null) {
					leadingCar.addObserver(followingCar);	
				}
			}

			// Remove car from east road list.
			skywayCarList.remove(carToMove);
			

			/* Handle west highway car and observer list. */
			Car newFollowingCar = null;
			Car newLeadingCar = null;

			// Add car to front of west highway list
			int newIndex = 0;
			westHighwayCarList.add(newIndex, carToMove);

			try {
				// Get instances of previous and next cars
				newFollowingCar = westHighwayCarList.get(newIndex+1);
				newLeadingCar = westHighwayCarList.get(newIndex-1);
			} catch (IndexOutOfBoundsException e) {
				// Do nothing, allow it to be null
			}
			
			if (newFollowingCar != null) {
				// Make the next car no longer follow this car.
				newFollowingCar.deleteObserver(carToMove);

				// Make the previous car observe the car ahead.				
				if (newLeadingCar != null) {
					newLeadingCar.addObserver(newFollowingCar);	
				}
			}
		}
		
		// Update cars to turn onto western highway.
		for (Car car: carsInMiddleRoad) {
			if (Math.abs(eastWestRoad.getStartX()-car.getVehicleX()-eastWestRoad.getRoadWidth())<=5) {
				// If the car is within range to turn onto the connecting road
				// Set movement direction of car.
				car.setDirection(westHighwayRoad.getDirection());
			}
		}
	}
	
	public static void main(String[] args){
		launch(args);
	}
}

