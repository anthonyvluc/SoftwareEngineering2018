package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model;

import java.awt.Point;

public class PortalState {
	
	Point coordinates;
	private LevelMap levelMap;

	public PortalState(Point coordinates, LevelMap levelMap) {
		this.levelMap = levelMap;
		this.coordinates = new Point(0, 0);
		setCoordinates(coordinates);
	}

	public Point getCoordinates() {
		return coordinates;
	}
	
	public void setCoordinates(Point point) {
		levelMap.levelGrid[coordinates.x][coordinates.y] = Tile.FLOOR;
		coordinates = point;
		levelMap.levelGrid[coordinates.x][coordinates.y] = Tile.PORTAL;
	}
	
}
