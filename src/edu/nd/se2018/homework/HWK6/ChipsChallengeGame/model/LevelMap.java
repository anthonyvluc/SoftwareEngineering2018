package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.Chip;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.ChipItem;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.Door;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.Item;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.LevelBuilder;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.Portal;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.view.ChipsChallengeUI;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public abstract class LevelMap {

	Chip 	chip;
	Portal 	portal;

	LevelBuilder levelBuilder;
	
	final int dimension;
	final int scale;

	public Tile[][] levelGrid;
	private List<Door> doors;
	private List<Item> items;
	private List<Item> keys;
	public Collection<Point> itemsSet;
	public Collection<Point> keysSet;
	public Collection<Point> waters;

	protected int numChipItems;
	
	ObservableList<Node> root;
	ChipsChallengeUI challengeUI;

	
	public LevelMap(int dimension, int scale, ObservableList<Node> root, ChipsChallengeUI chipsChallengeUI) {
		this.dimension = dimension;
		this.root = root;
		this.scale = scale;
		this.challengeUI = chipsChallengeUI;
		
		// Initialize variables.
		this.levelGrid = new Tile[dimension][dimension];
		doors = new ArrayList<Door>();
		keys = new ArrayList<Item>();
		keysSet = new HashSet<Point>();
		items = new ArrayList<Item>();
		itemsSet = new HashSet<Point>();
		waters = new HashSet<Point>();
		numChipItems = 0;
		
		loadInitialMap(); // Load initial as all floors.
		levelBuilder = new LevelBuilder(this);
	}

	public void drawLevel(ObservableList<Node> root) {
		for (int i = 0; i < dimension; ++i) {
			for (int j = 0; j < dimension; ++j) {
				Rectangle rect = new Rectangle(i*scale, j*scale, scale, scale);
				switch(levelGrid[i][j]) {
					case CHIP:
//						root.add();
						break;
					case DOOR:
						Door door = getDoor(i, j);
						Image doorImage = door.getImage();
						rect.setFill(new ImagePattern(doorImage));
						root.add(rect);
						break;
					case ITEM:
						ChipItem item = getChipItem(i, j);
						Image itemImage = item.getImage();
						rect.setFill(new ImagePattern(itemImage));
						root.add(rect);
						break;
					case KEY:
						Item key = getKey(i, j);
						Image keyImage = key.getImage();
						rect.setFill(new ImagePattern(keyImage));
						root.add(rect);
						break;
					case FLOOR:
						Image tileImage = new Image("images/chip/textures/BlankTile.png", scale, scale, true, true);
						rect.setFill(new ImagePattern(tileImage));
						root.add(rect);
						break;
					case PORTAL:
						Image portalImage = portal.getImage();
						rect.setFill(new ImagePattern(portalImage));
						root.add(rect);
						break;
					case WALL:
						Image wallImage = new Image("images/chip/textures/grayWall.png", scale, scale, true, true);
						rect.setFill(new ImagePattern(wallImage));
						root.add(rect);
						break;
					case WATER:
						Image waterImage = new Image("images/chip/textures/water.png", scale, scale, true, true);
						rect.setFill(new ImagePattern(waterImage));
						root.add(rect);
						break;
					default:
						break;
				}
			}
		}
	}

	protected void loadInitialMap() {
		for (int i = 0; i < dimension; ++i) {
			for (int j = 0; j < dimension; ++j) {
				levelGrid[i][j] = Tile.FLOOR;
			}
		}
	}
	
	protected boolean isValidChipPosition(Point position) {
		boolean bool = true;
		Tile t;

		try {
			t = levelGrid[position.x][position.y];
		} catch (ArrayIndexOutOfBoundsException exception) {
			// Make out of bounds a wall
			t = Tile.WALL;
		}
		
		if (t != Tile.PORTAL && (t == Tile.WALL || t == Tile.DOOR)) {
			bool = false;
		}
		return bool;
	}
	
	public Chip getChip() {
		return chip;
	}
	
	public int getNumChipItems() {
		return numChipItems;
	}
	
	public Point getPortalCoordinates() {
		return portal.getCoordinates();
	}
	
	protected void addDoor(Door d) {
		Point c = d.getCoordinates();

		doors.add(d);
		levelGrid[c.x][c.y] = Tile.DOOR;
		
		chip.addObserver(d);
		d.addObserver(challengeUI);
	}
	
	private Door getDoor(int x, int y) {
		Door door = null;
		for (Door d: doors) {
			Point coordinates = d.getCoordinates();
			if (coordinates.x == x && coordinates.y == y) {
				door = d;
			}
		}
		return door;
	}

	public void removeDoor(Door d, ObservableList<Node> root) {
		Point c = d.getCoordinates();
		int index = c.x*dimension + c.y;
		Node n = root.get(index);
		
		Image doorImage = new Image("images/chip/textures/BlankTile.png", scale, scale, true, true);
		((Rectangle) n).setFill(new ImagePattern(doorImage));
		
		levelGrid[c.x][c.y] = Tile.FLOOR;
		
		doors.remove(d);
	}
	
	/* Key ----------------------------------- */
	protected void addKey(Item k) {
		Point c = k.getCoordinates();
		keys.add(k);
		keysSet.add(c);
		levelGrid[c.x][c.y] = Tile.KEY;
	}
	
	public Item getKey(int x, int y) {
		Item key = null;
		for (Item k: keys) {
			Point coordinates = k.getCoordinates();
			if (coordinates.x == x && coordinates.y == y) {
				key = k;
			}
		}
		return key;
	}
	
	public void removeKey(Item k, ObservableList<Node> root) {
		Point c = k.getCoordinates();
		int index = c.x*dimension + c.y;
		Node n = root.get(index);
		
		Image keyImage = new Image("images/chip/textures/BlankTile.png", scale, scale, true, true);
		((Rectangle) n).setFill(new ImagePattern(keyImage));
		
		levelGrid[c.x][c.y] = Tile.FLOOR;
		
		keys.remove(k);
		keysSet.remove(c);
	}
	
	/* Chip Item ----------------------------- */
	public void addChipItem(ChipItem c) {
		Point p = c.getCoordinates();

		items.add(c);
		itemsSet.add(p);
		levelGrid[p.x][p.y] = Tile.ITEM;

		numChipItems++;
	}
	
	public ChipItem getChipItem(int x, int y) {
		ChipItem chipItem = null;
		for (Item i: items) {
			Point coordinates = i.getCoordinates();
			if (coordinates.x == x && coordinates.y == y) {
				chipItem = (ChipItem)i;
			}
		}
		return chipItem;
	}
	
	public void removeChipItem(ChipItem c, ObservableList<Node> root) {
		Point p = c.getCoordinates();
		int index = p.x*dimension + p.y;
		Node n = root.get(index);
		
		Image keyImage = new Image("images/chip/textures/BlankTile.png", scale, scale, true, true);
		((Rectangle) n).setFill(new ImagePattern(keyImage));
		
		levelGrid[p.x][p.y] = Tile.FLOOR;
		
		items.remove(c);
		itemsSet.remove(p);

		numChipItems--;
	}
	
	/* Splash -------------------------------- */
	public void splash(Point p, ObservableList<Node> root) {
		Rectangle rect = new Rectangle(p.x*scale, p.y*scale, scale, scale);
		Image splashImage = new Image("images/chip/textures/waterSplash.png", scale, scale, true, true);
		rect.setFill(new ImagePattern(splashImage));
		root.add(rect);
	}

	/* Abstract methods. --------------------- */
	public abstract void generateLevel();
}
