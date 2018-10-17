package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller;

import java.awt.Point;
import java.util.Observable;

import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model.LevelMap;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model.Tile;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.view.DoorView;

public class RedDoor extends Door {

	public RedDoor(Point coordinates, LevelMap levelMap, int scale) {
		super(coordinates, levelMap, scale);
		
		// Set initial image.
		doorView = new DoorView(this, scale, "images/chip/textures/redKeyWall.png");
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Chip) {
			Chip chip = (Chip)o;
			if (chip.hasRedKey()) {
				doorState.getDoorState().unlocking();
				levelMap.levelGrid[coordinates.x][coordinates.y] = Tile.FLOOR;
			}
			
			if (getCoordinates().equals(chip.getCoordinates())) {
				System.out.println("on door");
				this.setImage(this.openedDoor);
				setChanged();
				notifyObservers();
			}
		}	
	}

}
