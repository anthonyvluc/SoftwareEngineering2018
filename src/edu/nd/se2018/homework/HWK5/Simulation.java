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

				for (Train train: trains) {
					train.move();				
				}
				
				for(CrossingGate gate: mapBuilder.getAllGates()) {
					gate.operateGate();					
				}
				
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
	
	public static void main(String[] args){
		launch(args);
	}
}

