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

	final private int 	scale;
	private Point 		position;
	
	OceanMap 	oceanMap;
	Image		shipImage;
	ImageView	shipImageView;


	public Ship(Point position, OceanMap oceanMap, int scale, String imgPath) {
		this.oceanMap 	= oceanMap;
		this.scale 		= scale;
		this.position 	= position;
		
		// Set image.
		shipImage = new Image(imgPath, scale, scale, true, true);
		shipImageView = new ImageView(shipImage);
		shipImageView.setX(position.x * scale);
		shipImageView.setY(position.y * scale);
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

		// Update tile
		shipImageView.setX(getShipLocation().x*scale);
		shipImageView.setY(getShipLocation().y*scale);
		oceanMap.oceanGrid[getShipLocation().x][getShipLocation().y] = Tile.SHIP;

		setChanged();
		notifyObservers();
	}


	private void goNorth() {
		Point newPosition = new Point(this.position.x, this.position.y - 1);
		if (oceanMap.isValidShipPosition(newPosition)) {
			oceanMap.oceanGrid[position.x][position.y] = Tile.OCEAN;
			position.setLocation(newPosition);
		}
	}


	private void goSouth() {
		Point newPosition = new Point(this.position.x, this.position.y + 1);
		if (oceanMap.isValidShipPosition(newPosition)) {
			oceanMap.oceanGrid[position.x][position.y] = Tile.OCEAN;
			position.setLocation(newPosition);
		}
	}


	private void goEast() {
		Point newPosition = new Point(this.position.x + 1, this.position.y);
		if (oceanMap.isValidShipPosition(newPosition)) {
			oceanMap.oceanGrid[position.x][position.y] = Tile.OCEAN;
			position.setLocation(newPosition);
		}
	}


	private void goWest() {
		Point newPosition = new Point(this.position.x - 1, this.position.y);
		if (oceanMap.isValidShipPosition(newPosition)) {
			oceanMap.oceanGrid[position.x][position.y] = Tile.OCEAN;
			position.setLocation(newPosition);
		}
	}
}
