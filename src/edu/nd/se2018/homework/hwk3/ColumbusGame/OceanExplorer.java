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
	
	OceanMap oceanMap;
	Ship 	ship;

	Pane 	root;
	Scene 	scene;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage oceanStage) throws Exception {

		
		oceanMap = new OceanMap(oceanSize, cellSize);
		
		ship = new Ship(new Point(0, 0), oceanMap);
		
		// Add ocean.
		root = new Pane();
		
		// Setup.
		scene = new Scene(root, oceanSize*cellSize, oceanSize*cellSize);
	
		oceanStage.setScene(scene);
		
		oceanStage.setTitle("Christopher Columbus Sails the Ocean Blue");
		
		oceanStage.show();
		
		oceanMap.drawMap(root.getChildren());
		
		
		startSailing();
		
	}
	
	public void startSailing() {
		
		// Load image: (1) file name, width, height, maintain ration(T/F), smoothing (T/F)
		Image shipImage = new Image("images/ColumbusShip.png", cellSize, cellSize, true, true);
		
		// Now instantiate and load the image View. Actually this probably needs to be
		// a class level variable so you would already have defined ImageView shipImageview
		ImageView shipImageView = new ImageView(shipImage);
		
		shipImageView.setX(ship.getShipLocation().x * cellSize);
		shipImageView.setY(ship.getShipLocation().y * cellSize);
		
		// Add ship to map.
		root.getChildren().add(shipImageView);
		
		// ----------------
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent ke) {
				switch(ke.getCode()) {
					case RIGHT:
						ship.goEast();
						break;
					case LEFT:
						ship.goWest();
						break;
					case UP:
						ship.goNorth();
						break;
					case DOWN:
						ship.goSouth();
						break;
					default:
						break;				
				}
				shipImageView.setX(ship.getShipLocation().x*cellSize);
				shipImageView.setY(ship.getShipLocation().y*cellSize);
			}
		});
	}

}
