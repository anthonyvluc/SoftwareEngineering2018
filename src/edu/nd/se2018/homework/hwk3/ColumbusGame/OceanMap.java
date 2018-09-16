package edu.nd.se2018.homework.hwk3.ColumbusGame;

import java.awt.Point;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class OceanMap {
	private int dimensions;
	boolean[][] oceanGrid;
	private int scale;


	public OceanMap(int dimension, int scale) {
		this.dimensions = dimension;
		this.oceanGrid = new boolean[dimensions][dimensions];
		this.scale = scale;
	}
	
	public void drawMap(ObservableList<Node> root) {
		for(int x = 0; x < dimensions; ++x) {
			for (int y = 0; y < dimensions; ++y) {
				Rectangle rect = new Rectangle(x*scale, y*scale, scale, scale);
				rect.setStroke(Color.BLACK);		// Black outline
				rect.setFill(Color.PALETURQUOISE);	// Ocean color
				root.add(rect);
				oceanGrid[x][y] = false;
			}
		}
	}
	
	public int getDimensions() {
		return this.dimensions;
	}
}
