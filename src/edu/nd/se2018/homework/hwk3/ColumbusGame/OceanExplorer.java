package edu.nd.se2018.homework.hwk3.ColumbusGame;

import java.util.LinkedList;

import edu.nd.se2018.homework.hwk3.ColumbusGame.Ship.Direction;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class OceanExplorer extends Application {

	final private int 		cellSize 	= 30;
	final private int 		oceanSize 	= 25;
	private int				numIslands 	= 10;
	private int 			numPirates 	= 7;
	
	OceanMap 				oceanMap;
	Ship 	 				heroShip;
	LinkedList<PirateShip>	pirateShips;

	AnchorPane 				root;
	Scene 					scene;


	public static void main(String[] args) {
		launch(args);
	}


	@Override
	public void start(Stage oceanStage) throws Exception {

		// Initialize pane of application.
		root = new AnchorPane();

		// Generate the Ocean.
		oceanMap = new OceanMap(oceanSize, cellSize, numIslands);
		oceanMap.drawMap(root.getChildren());
		
		// Create and add ships and images to view.
		heroShip = new Ship(oceanMap.getInitialShipPosition(), oceanMap, cellSize, "images/ColumbusShip.png");
		root.getChildren().add(heroShip.getShipImageView());
		pirateShips = new LinkedList<PirateShip>();
		for (int i = 0; i < numPirates; ++i) {
			PirateShip pirateShip = new PirateShip(oceanMap.getInitialShipPosition(), oceanMap, cellSize, "images/pirateship.gif");
			pirateShips.add(pirateShip);
			root.getChildren().add(pirateShip.getShipImageView());
		}
		
		// Setup.
		scene = new Scene(root, oceanSize*cellSize, oceanSize*cellSize);
		oceanStage.setTitle("Christopher Columbus Sails the Ocean Blue");
		oceanStage.setScene(scene);
		oceanStage.show();
		
		// Start game.
		startSailing();
	}


	public void startSailing() {

		// Register pirates as observers of the hero ship.
		for (PirateShip pirateShip : pirateShips) {
			heroShip.addObserver(pirateShip);			
		}

		// Handle user keyboard inputs.
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent ke) {
				switch(ke.getCode()) {
					case RIGHT:
						heroShip.moveShip(Direction.EAST);
						break;
					case LEFT:
						heroShip.moveShip(Direction.WEST);
						break;
					case UP:
						heroShip.moveShip(Direction.NORTH);
						break;
					case DOWN:
						heroShip.moveShip(Direction.SOUTH);
						break;
					default:
						break;				
				}				
			}			
		});
	}
}
