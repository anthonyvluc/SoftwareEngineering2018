package edu.nd.se2018.homework.hwk3.ColumbusGame;

import java.awt.Point;
import java.util.Observable;

import edu.nd.se2018.homework.hwk3.ColumbusGame.OceanMap.Tile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ship extends Observable {

	public enum Direction {
	    NORTH, SOUTH, EAST, WEST
	}
	
	final private int scale;

	private Point position;
	
	OceanMap 	oceanMap;
	Image		shipImage;
	ImageView	shipImageView;
	Tile		tile;
	
	public Ship(Point position, OceanMap oceanMap, Tile tile, int scale, String imgPath) {
		this.oceanMap = oceanMap;
		this.setPosition(position);
		this.scale = scale;
		this.tile = tile;
		
		// Set image.
		shipImage = new Image(imgPath, scale, scale, true, true);
		shipImageView = new ImageView(shipImage);
		shipImageView.setX(position.x * scale);
		shipImageView.setY(position.y * scale);
	}

	public void setPosition(Point position) {
		this.position = position;
	}
	
	public Point getShipLocation(){
		return position;
	}
	
	public ImageView getShipImageView() {
		return shipImageView;
	}
	
	public void moveShip(Direction direction) {

		switch(direction) {
			case NORTH:
				goNorth();
				break;
			case SOUTH:
				goSouth();
				break;
			case EAST:
				goEast();
				break;
			case WEST:
				goWest();
				break;
			default:
				break;
		}

		shipImageView.setX(getShipLocation().x*scale);
		shipImageView.setY(getShipLocation().y*scale);
		
		oceanMap.oceanGrid[getShipLocation().x][getShipLocation().y] = tile;
		
		setChanged();
		notifyObservers();
	}
	
	public void goNorth() {
		Point newPosition = new Point(this.position.x, this.position.y - 1);
		if (oceanMap.isValidShipPosition(newPosition)) {
			this.position.setLocation(newPosition);
		}
	}

	public void goSouth() {
		Point newPosition = new Point(this.position.x, this.position.y + 1);
		if (oceanMap.isValidShipPosition(newPosition)) {
			this.position.setLocation(newPosition);
		}
	}

	public void goEast() {
		Point newPosition = new Point(this.position.x + 1, this.position.y);
		if (oceanMap.isValidShipPosition(newPosition)) {
			this.position.setLocation(newPosition);
		}
	}

	public void goWest() {
		Point newPosition = new Point(this.position.x - 1, this.position.y);
		if (oceanMap.isValidShipPosition(newPosition)) {
			this.position.setLocation(newPosition);
		}
	}
}
