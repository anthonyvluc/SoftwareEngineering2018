package edu.nd.se2018.homework.hwk3.ColumbusGame;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import edu.nd.se2018.homework.hwk3.ColumbusGame.OceanMap.Tile;

public class PirateShip extends Ship implements Observer {

	public PirateShip(Point position, OceanMap oceanMap, Tile tile, int scale, String imgPath) {
		super(position, oceanMap, tile, scale, imgPath);
	}

	public void chaseHero(Point heroPosition) {
		if (heroPosition.x - this.getShipLocation().x > 0)
			moveShip(Direction.EAST);
		else
			moveShip(Direction.WEST);
		
		if (heroPosition.y - this.getShipLocation().y > 0)
			moveShip(Direction.SOUTH);
		else
			moveShip(Direction.NORTH);
	}
	
	@Override
	public void update(Observable s, Object arg) {
		if (s instanceof Ship){
			Point heroPosition = ((Ship)s).getShipLocation();
			chaseHero(heroPosition);			
		}
	}
}
