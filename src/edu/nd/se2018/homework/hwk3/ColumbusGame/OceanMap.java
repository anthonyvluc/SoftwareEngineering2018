package edu.nd.se2018.homework.hwk3.ColumbusGame;

import java.awt.Point;
import java.util.Random;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class OceanMap {

	public enum Tile {
	    OCEAN, ISLAND, PIRATE
	}

	private int dimensions;
	Tile[][] 	oceanGrid;
	private int scale;
	
	private int numIslands;
	private int numPirates;

	public OceanMap(int dimension, int scale, int numIslands, int numPirates) {
		this.dimensions = dimension;
		this.oceanGrid 	= new Tile[dimensions][dimensions];
		this.numIslands = numIslands;
		this.numPirates = numPirates;
		this.scale 		= scale;
		
		generateOcean();
		generateIslands();
	}
	
	public void drawMap(ObservableList<Node> root) {
		for(int x = 0; x < dimensions; ++x) {
			for (int y = 0; y < dimensions; ++y) {
				Rectangle rect = new Rectangle(x*scale, y*scale, scale, scale);
				rect.setStroke(Color.BLACK);
				switch(oceanGrid[x][y]) {
					case OCEAN:
						rect.setFill(Color.PALETURQUOISE);
						break;
					case ISLAND:
						rect.setFill(Color.GREEN);
						break;
					case PIRATE:
						break;
					default:
						break;
				}
				root.add(rect);
			}
		}
	}

	public Point getInitialHeroPosition() {
		Point initialPosition = null;
		while (true) {
			Random rand = new Random();
			Point positionPoint = new Point(rand.nextInt(dimensions), rand.nextInt(dimensions));
			if (isValidShipPosition(positionPoint)) {
				initialPosition = positionPoint;
				break;
			}
		}
		return initialPosition;	
	}
	
	public Point getInitialPiratePosition() {
		Point initialPosition = null;
		while (true) {
			Random rand = new Random();
			Point positionPoint = new Point(rand.nextInt(dimensions), rand.nextInt(dimensions));
			if (isValidShipPosition(positionPoint)) {
				initialPosition = positionPoint;
				oceanGrid[positionPoint.x][positionPoint.y] = Tile.PIRATE;
				break;
			}
		}
		return initialPosition;
	}
	
	public boolean isValidShipPosition(Point position) {
		return	((position.x >= 0) && (position.x < dimensions) &&
				 (position.y >= 0) && (position.y < dimensions) &&
			     (oceanGrid[position.x][position.y] == Tile.OCEAN));
	}
	
	private void generateOcean() {
		for(int x = 0; x < dimensions; ++x) {
			for (int y = 0; y < dimensions; ++y) {
				oceanGrid[x][y] = Tile.OCEAN;
			}
		}
	}
	
	private void generateIslands() {
		int i = 0;
		while (i < numIslands) {
			Random rand = new Random();
			int x = rand.nextInt(dimensions);
			int y = rand.nextInt(dimensions);
			if (oceanGrid[x][y] == Tile.OCEAN) {
				oceanGrid[x][y] = Tile.ISLAND;
				++i;
			}
		}
	}
}
