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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ChipsChallengeUI extends Application implements Observer {

	final int 				cellSize 	= 30;
	final int 				levelSize 	= 25;
	final private double	uiPadding 	= 50.0;
	final int 				numLevels 	= 2;

	AnchorPane 				root;
	Stage 					gameStage;
	Scene 					gameScene = null;
	Text 					levelMessage, numChipMessage, inventoryMessage;

	Chip chip;
	LevelMap levelMap;	
	LevelMap[] levels;
	int currentLevel;
	
	public ChipsChallengeUI() {}

	@Override
	public void start(Stage gameStage) throws Exception {

		// Set stage.
		this.gameStage = gameStage;

		// Initialize pane of application.
		root = new AnchorPane();
		
		// Initialize levels.
		initializeLevels();
		
		// Start with initial values.
		restart();
	}

	public void initializeGame(int level) {		
		
		// Reset nodes.
		root.getChildren().clear();

		// Set current level.
		levelMap = levels[level];
		
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
			gameScene = new Scene(root, levelSize*cellSize, levelSize*cellSize + uiPadding);
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
					case R:
						restart();
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
					System.exit(0);
				} else {
					// Load next level.
					restart();
				}
			}
			
			// Chip went into water.
			if (levelMap.waters.contains(chip.getCoordinates())) {
				levelMap.splash(chip.getCoordinates(), root.getChildren());
				pause(700); // TODO: figure out how to slow it down to show the splash

				restart();	// Restart level.
			}
			
			// Chip picked up key.
			if (levelMap.keysSet.contains(chip.getCoordinates())) {
				// Add to inventory and remove from map.
				Point c = chip.getCoordinates();
				Item key = levelMap.getKey(c.x, c.y);
				chip.addItem(key);
				levelMap.removeKey(key, root.getChildren());				

				// Update text.
				inventoryMessage.setText("Current Inventory: " + (levelMap.getChip().getInventoryString()));
			}
			
			// Chip picked up chip item.
			if (levelMap.itemsSet.contains(chip.getCoordinates())) {				
				// Update level chip item count and remove from map.
				Point c = chip.getCoordinates();
				ChipItem chipItem = levelMap.getChipItem(c.x, c.y);
				levelMap.removeChipItem(chipItem, root.getChildren());
				
				// Update text.
				numChipMessage.setText("Chips remaining: " + (levelMap.getNumChipItems()));
			}
		}
		
		if (o instanceof Door) {
			// Door state changed, reload map.
			Door door = (Door)o;
			levelMap.removeDoor(door, root.getChildren());
		}
	}
	
	private void restart() {
		int progressLevel = currentLevel; // To help keep track of current progress before reset.
		initializeLevels();
		initializeGame(progressLevel);
		startChipsChallenge();	
		startUI();
		currentLevel = progressLevel;
	}

	public void pause(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void startUI() {
		double width = levelSize*cellSize;
		
		levelMessage = new Text();
		levelMessage.setX(10);
		levelMessage.setY(levelSize*cellSize + 30.0);
		levelMessage.setFont(new Font("Calibri", 14));
		
		numChipMessage = new Text();
		numChipMessage.setX(width*1/3 - 10);
		numChipMessage.setY(levelSize*cellSize + 30.0);
		numChipMessage.setFont(new Font("Calibri", 14));
		
		inventoryMessage = new Text();
		inventoryMessage.setX(width*2/3 - 10);
		inventoryMessage.setY(levelSize*cellSize + 30.0);
		inventoryMessage.setFont(new Font("Calibri", 14));

		// Set text
		levelMessage.setText("Current Level: " + (currentLevel + 1));
		numChipMessage.setText("Chips remaining: " + (levelMap.getNumChipItems()));
		inventoryMessage.setText("Current Inventory: " + (levelMap.getChip().getInventoryString()));
		
		// Add UI to pane.
		root.getChildren().add(levelMessage);
		root.getChildren().add(numChipMessage);
		root.getChildren().add(inventoryMessage);
		
	}	
	
	public static void main(String[] args) {
		launch(args);
	}
}
