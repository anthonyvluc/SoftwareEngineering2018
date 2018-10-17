package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.view;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.Chip;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model.LevelMap;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ChipsChallengeUI extends Application implements Observer {

	/* TODO: Move to explorer class */
	final int cellSize = 30;
	final int levelSize = 25;
	final int numChips = 3;

	AnchorPane root;
	Stage gameStage;
	Scene gameScene;	
	
	Chip chip;
	LevelMap levelMap;	

	
	public ChipsChallengeUI() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start(Stage gameStage) throws Exception {
		
		// Set stage.
		this.gameStage = gameStage;
		
		// Initialize game.
		initializeGame();

		// TODO: Start UI.
//		startUI();
				
		// Start game.
		startChipsChallenge();
	}

	public void initializeGame() {
		
		// Initialize pane of application.
		root = new AnchorPane();

		// Generate the level.
		levelMap = new LevelMap(levelSize, cellSize, root.getChildren());
		levelMap.drawLevel(root.getChildren());
		
		// Create Chip and add to view.
		chip = new Chip(new Point(12, 12), levelMap, cellSize);
		root.getChildren().add(chip.getImageView());

		// Add UI as observer to Chip.
		chip.addObserver(this);
		
		// Setup.
		gameScene = new Scene(root, levelSize*cellSize, levelSize*cellSize);
		gameStage.setTitle("Chips Challenge by Hai");
		gameStage.setScene(gameScene);
		gameStage.show();
	}

	protected void startChipsChallenge() {
		// Handle user keyboard inputs.
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent ke) {
				switch(ke.getCode()) {
					case RIGHT:
						chip.move(Direction.EAST);
						break;
					case LEFT:
						chip.move(Direction.WEST);
						break;
					case UP:
						chip.move(Direction.NORTH);
						break;
					case DOWN:
						chip.move(Direction.SOUTH);
						break;
					default:
						break;				
				}
				
				// Check if Chip is at portal.
				if (chip.getCoordinates().equals(levelMap.getPortalCoordinates())) {
					System.out.println("complete!!!");
				}
			}		
		});
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Chip){
			Chip chip = (Chip)o;
			chip.updateImageView(); // Update image view of chip
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
