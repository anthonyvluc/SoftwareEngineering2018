package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller;

import java.awt.Point;
import java.util.Observable;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Chip extends Observable {

	private int cellSize;
	private Point coordinates;
	private LevelMap levelMap;

	Image chipImage;
	ImageView chipImageView;
	
	
	public Chip(Point coordinates, LevelMap levelMap, int scale) {
		// TODO Auto-generated constructor stub
		this.coordinates = coordinates;
		this.levelMap = levelMap;
		this.cellSize = cellSize;
		
//		setChipImage(this);
		
		// Set image.
		chipImage = new Image("images/chip/textures/chipDown.png", scale, scale, true, true);
		chipImageView = new ImageView(chipImage);
		chipImageView.setX(coordinates.x * scale);
		chipImageView.setY(coordinates.y * scale);
	}

	/* To move to image in view*/
	private void setChipImage(Chip chip){
		Image chipImage = new Image("images/chip/textures/chipDown.png",100,100,true,true);	
		chipImageView = new ImageView(chipImage);
		chipImageView.setX(chip.getCoordinates().x);
		chipImageView.setY(chip.getCoordinates().y);
	}

	
	public Point getCoordinates() {
		return this.coordinates;
	}

	public Node getImageView() {
		return chipImageView;
	}
}
