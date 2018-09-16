package edu.nd.se2018.homework.hwk3.ColumbusGame;

import java.util.LinkedList;

import edu.nd.se2018.homework.hwk3.ColumbusGame.Ship.Direction;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class OceanExplorer extends Application {

	final private int 		cellSize 	= 30;
	final private int 		oceanSize 	= 25;
	final private int		numIslands 	= 10;
	final private int 		numPirates 	= 7;
	final private double	uiPadding 	= 50.0;
	
	OceanMap 				oceanMap;
	Ship 	 				heroShip;
	LinkedList<PirateShip>	pirateShips;

	AnchorPane 				root;
	Button					resetButton;
	Scene 					scene;
	Stage 					oceanStage;


	public static void main(String[] args) {
		launch(args);
	}


	@Override
	public void start(Stage oceanStage) throws Exception {

		// Set stage.
		this.oceanStage = oceanStage;
		
		// Initialize game.
		initializeGame();
		
		// Start UI.
		startUI();
		
		// Start game.
		startSailing();
	}

	
	public void initializeGame() {

		// Initialize pane of application.
		root = new AnchorPane();

		// Generate the Ocean.
		oceanMap = new OceanMap(oceanSize, cellSize, numIslands, root.getChildren());
		// oceanMap.drawMap(root.getChildren());
		
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
		scene = new Scene(root, oceanSize*cellSize, oceanSize*cellSize + uiPadding);
		oceanStage.setTitle("Christopher Columbus Sails the Ocean Blue");
		oceanStage.setScene(scene);
		oceanStage.show();
	}


	public void startUI() {
		
		// Create reset button.
		resetButton = new Button("Reset Game");
		resetButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	initializeGame();
		    	startUI();
		        startSailing();
		    }
	    });

		AnchorPane.setTopAnchor(resetButton, oceanSize*cellSize + 10.0);
		AnchorPane.setLeftAnchor(resetButton,  10.0);
		AnchorPane.setRightAnchor(resetButton,  10.0);
		AnchorPane.setBottomAnchor(resetButton,  10.0);
		
		// Add UI to pane.
		root.getChildren().add(resetButton);
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
