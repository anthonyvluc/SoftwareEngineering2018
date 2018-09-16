package edu.nd.se2018.homework.hwk3.ColumbusGame;

import java.awt.Point;

public class Ship {

	private Point position;
	
	OceanMap oceanMap;
	
	public Ship(Point position, OceanMap oceanMap) {
		this.setPosition(position);
		this.oceanMap = oceanMap;
	}

	public void setPosition(Point position) {
		this.position = position;
	}
	
	public Point getShipLocation(){
		return position;
	}
	
	public void goNorth() {
		Point newPosition = new Point(this.position.x, this.position.y - 1);
		if (oceanMap.isValidHeroPosition(newPosition)) {
			this.position.setLocation(newPosition);
		}
	}

	public void goSouth() {
		Point newPosition = new Point(this.position.x, this.position.y + 1);
		if (oceanMap.isValidHeroPosition(newPosition)) {
			this.position.setLocation(newPosition);
		}
	}

	public void goEast() {
		Point newPosition = new Point(this.position.x + 1, this.position.y);
		if (oceanMap.isValidHeroPosition(newPosition)) {
			this.position.setLocation(newPosition);
		}
	}

	public void goWest() {
		Point newPosition = new Point(this.position.x - 1, this.position.y);
		if (oceanMap.isValidHeroPosition(newPosition)) {
			this.position.setLocation(newPosition);
		}
	}
}
