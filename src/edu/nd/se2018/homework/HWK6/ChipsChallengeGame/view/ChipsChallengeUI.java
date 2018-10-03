package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.view;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.Chip;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.LevelMap;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
		
		// Create Chip and add to view.
		chip = new Chip(new Point(12, 12), levelMap, cellSize);
		root.getChildren().add(chip.getImageView());
		
		
		// Setup.
		gameScene = new Scene(root, levelSize*cellSize, levelSize*cellSize);
		gameStage.setTitle("Chips Challenge by Hai");
		gameStage.setScene(gameScene);
		gameStage.show();
	}

	
	protected void startChipsChallenge() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
