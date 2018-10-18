package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.view;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.Chip;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.ChipItem;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.Door;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.Item;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model.LevelMap;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model.LevelOneMap;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model.LevelTwoMap;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ChipsChallengeUI extends Application implements Observer {

	final int cellSize = 30;
	final int levelSize = 25;
	final int numChips = 3;

	AnchorPane root;
	Stage gameStage;
	Scene gameScene = null;	
	
	Chip chip;
	LevelMap levelMap;	
	LevelMap[] levels;
	int currentLevel;
	final int numLevels = 2;
	
	
	public ChipsChallengeUI() {}

	@Override
	public void start(Stage gameStage) throws Exception {

		// Set stage.
		this.gameStage = gameStage;

		// Initialize pane of application.
		root = new AnchorPane();
		
		// Initialize levels.
		initializeLevels();
		
		// Initialize game.
		initializeGame();

		// TODO: Start UI.
//		startUI();
				
		// Start game.
		startChipsChallenge();
	}

	public void initializeGame() {		
		
		// Reset nodes.
		root.getChildren().removeAll(root.getChildren());		

		// Set current level.
		levelMap = levels[currentLevel];
		
		// Generate level.
		levelMap.generateLevel();
		levelMap.drawLevel(root.getChildren());
		
		// Create Chip and add to view.
		chip = levelMap.getChip();
		root.getChildren().add(chip.getImageView());

		// Add UI as observer to Chip.
		chip.addObserver(this);
		
		// Setup.
		if (gameScene == null) {
			gameScene = new Scene(root, levelSize*cellSize, levelSize*cellSize);			
		}
		gameStage.setTitle("Chips Challenge by Hai");
		gameStage.setScene(gameScene);
		gameStage.show();
	}
	
	public void initializeLevels() {
		levels = new LevelMap[numLevels];
		
		// Initialize level one.
		LevelMap level1Map = new LevelOneMap(levelSize, cellSize, root.getChildren(), this);
		levels[0] = level1Map;
		
		// Initialize level two.
		LevelMap level2Map = new LevelTwoMap(levelSize, cellSize, root.getChildren(), this);
		levels[1] = level2Map;
		
		// Set first level.
		currentLevel = 0;
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
			}		
		});
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Chip) {
			Chip chip = (Chip)o;
			chip.updateImageView(); // Update image view of chip
			
			// Check if Chip is at portal and all chips are collected.
			if ((chip.getCoordinates().equals(levelMap.getPortalCoordinates())) && (levelMap.getNumChipItems() == 0)) {
				currentLevel = currentLevel + 1;
				if (currentLevel >= numLevels) {
					// Finished last level.
					System.out.println("Finished game!");
				} else {
					// Load next level.
					System.out.println("Loading next level...");
					levelMap = levels[currentLevel];
					initializeGame();
					startChipsChallenge();											
				}
			}
			
			// Chip went into water.
			if (levelMap.waters.contains(chip.getCoordinates())) {
				System.out.println("splash!!!");

				levelMap.splash(chip.getCoordinates(), root.getChildren());
				pause(700); // TODO: figure out how to slow it down to show the splash
				
				// Reset level.
				initializeGame();
				startChipsChallenge(); // TODO: the input of user is still sent after game is reset.
				
				// TODO: figure out how to fix the reset not deleting...
			}
			
			// Chip picked up key.
			if (levelMap.keysSet.contains(chip.getCoordinates())) {
				System.out.println("picked up keys!!!");
				Point c = chip.getCoordinates();
				Item key = levelMap.getKey(c.x, c.y);
				
				// Add to inventory and remove from map.
				chip.addItem(key);
				levelMap.removeKey(key, root.getChildren());
			}
			
			// Chip picked up chip item.
			if (levelMap.itemsSet.contains(chip.getCoordinates())) {
				System.out.println("picked up chip!!!");
				Point c = chip.getCoordinates();
				ChipItem chipItem = levelMap.getChipItem(c.x, c.y);
				
				// Update level chip item count and remove from map.
				levelMap.removeChipItem(chipItem, root.getChildren());
			}
		}
		
		if (o instanceof Door) {
			// Door state changed, reload map.
			Door door = (Door)o;
			levelMap.removeDoor(door, root.getChildren());
		}
	}
	
	public void pause(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
