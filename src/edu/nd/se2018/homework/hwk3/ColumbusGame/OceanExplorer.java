package edu.nd.se2018.homework.hwk3.ColumbusGame;

import java.awt.Point;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OceanExplorer extends Application {

	final private int cellSize 	= 30;
	final private int oceanSize = 25;
	
	OceanMap 	oceanMap;
	Ship 	 	heroShip;

	Image		heroShipImage;
	ImageView 	heroShipImageView;
	Pane 		root;
	Scene 		scene;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage oceanStage) throws Exception {

		root = new Pane();

		// Generate the Ocean.
		oceanMap = new OceanMap(oceanSize, cellSize);
		oceanMap.drawMap(root.getChildren());
		
		// Create the ships.
		heroShip = new Ship(new Point(0, 0), oceanMap); 	// TODO: change start point?
		// TODO: Pirates...
		
		// Add ocean, ships, and images to view.
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
		// Load image: (1) file name, width, height, maintain ration(T/F), smoothing (T/F)
		heroShipImage = new Image("images/ColumbusShip.png", cellSize, cellSize, true, true);
		
		heroShipImageView = new ImageView(heroShipImage);
		heroShipImageView.setX(heroShip.getShipLocation().x * cellSize);
		heroShipImageView.setY(heroShip.getShipLocation().y * cellSize);
		
		// Add ship to map.
		root.getChildren().add(heroShipImageView);
	}

}
