package edu.nd.se2018.homework.hwk3.ColumbusGame;

import java.util.LinkedList;

import edu.nd.se2018.examples.observer.catmouse.Cat;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class OceanExplorer extends Application {

	final private int 		cellSize 	= 30;
	final private int 		oceanSize 	= 25;
	private int				numIslands 	= 10;
	private int 			numPirates 	= 10;
	
	OceanMap 				oceanMap;
	Ship 	 				heroShip;
	LinkedList<PirateShip>	pirateShips;

	Image					heroShipImage, pirateShipImage;
	ImageView 				heroShipImageView, pirateShipImageView;
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
		oceanMap = new OceanMap(oceanSize, cellSize, numIslands, numPirates);
		oceanMap.drawMap(root.getChildren());
		
		// Create and add ships and images to view.
		heroShip = new Ship(oceanMap.getInitialHeroPosition(), oceanMap);
		pirateShips = new LinkedList<PirateShip>();
		for (int i = 0; i < numPirates; ++i) {
			pirateShips.add(new PirateShip(oceanMap.getInitialPiratePosition(), oceanMap));
		}
		loadImages();
		
		// Setup.
		scene = new Scene(root, oceanSize*cellSize, oceanSize*cellSize);
		oceanStage.setTitle("Christopher Columbus Sails the Ocean Blue");
		oceanStage.setScene(scene);
		oceanStage.show();
		
		// Start game.
		startSailing();
	}
	
	public void startSailing() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent ke) {
				switch(ke.getCode()) {
					case RIGHT:
						heroShip.goEast();
						break;
					case LEFT:
						heroShip.goWest();
						break;
					case UP:
						heroShip.goNorth();
						break;
					case DOWN:
						heroShip.goSouth();
						break;
					default:
						break;				
				}
				heroShipImageView.setX(heroShip.getShipLocation().x*cellSize);
				heroShipImageView.setY(heroShip.getShipLocation().y*cellSize);
			}
		});
	}
	
	public void loadImages() {
		// Add hero ship image to map.
		heroShipImage = new Image("images/ColumbusShip.png", cellSize, cellSize, true, true);
		heroShipImageView = new ImageView(heroShipImage);
		heroShipImageView.setX(heroShip.getShipLocation().x * cellSize);
		heroShipImageView.setY(heroShip.getShipLocation().y * cellSize);
		root.getChildren().add(heroShipImageView);
		
		// Add pirate ship images to map.
		pirateShipImage = new Image("images/pirateship.gif", cellSize, cellSize, true, true);
		for (PirateShip pirateShip : pirateShips) {
			pirateShipImageView = new ImageView(pirateShipImage);
			pirateShipImageView.setX(pirateShip.getShipLocation().x * cellSize);
			pirateShipImageView.setY(pirateShip.getShipLocation().y * cellSize);
			root.getChildren().add(pirateShipImageView);
		}
	}
}
