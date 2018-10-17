package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller;

import java.awt.Point;
import java.util.List;

import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model.LevelMap;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model.Tile;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.utils.Bresenham;

public class LevelBuilder {

	LevelMap levelMap;
	
	public LevelBuilder(LevelMap levelMap) {
		this.levelMap = levelMap;
	}

	public void addWall(Point p1, Point p2) {
		List<Point> squares = Bresenham.findLine(levelMap.levelGrid, p1.x, p1.y, p2.x, p2.y);
		
		for (Point p: squares) {
			levelMap.levelGrid[p.x][p.y] = Tile.WALL;
		}
	}

	public void addWater(Point p1, Point p2) {
		List<Point> squares = Bresenham.findLine(levelMap.levelGrid, p1.x, p1.y, p2.x, p2.y);
		
		for (Point p: squares) {
			levelMap.levelGrid[p.x][p.y] = Tile.WATER;
		}
	}
}
