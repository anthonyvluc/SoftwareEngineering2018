package edu.nd.se2018.homework.HWK5.model.vehicles;

import java.util.Observable;
import java.util.Observer;

import edu.nd.se2018.homework.HWK5.model.infrastructure.Direction;
import edu.nd.se2018.homework.HWK5.model.infrastructure.gate.CrossingGate;
import edu.nd.se2018.homework.HWK5.view.CarImageSelector;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

/**
 * Represents Car object
 * @author jane
 *
 */
public class Car extends Observable implements IVehicle, Observer {
	private ImageView ivCar;
	private double currentX = 0;
	private double currentY = 0;
	private double originalY = 0;
	private boolean gateDown = false;
	private double leadCarX = -1;
	private double leadCarY = -1;  // Current Y position of car directly infront of this one
	private double speed = 0.5;

	private Direction direction;
		
	/**
	 * Constructor
	 * @param x initial x coordinate of car
	 * @param y initial y coordinate of car
	 */
	public Car(int x, int y) {
		this.currentX = x;
		this.currentY = y;
		originalY = y;
		ivCar = new ImageView(CarImageSelector.getImage());
		ivCar.setX(getVehicleX());
		ivCar.setY(getVehicleY());
	}
		
	@Override
	public Node getImageView() {
		return ivCar;
	}
	
	public boolean gateIsClosed(){
		return gateDown;
	}
	
	public double getVehicleX() {
		return currentX;
	}
	public double getVehicleY() {
		return currentY;
	}
	
	public void move() {
		boolean canMove = true; 
		
		// First case.  Car is at the front of the stopping line.
		if (gateDown && getVehicleY() < 430 && getVehicleY()> 390) {
			canMove = false;
		}
		
		// Second case. Car is too close too other car.
		if (leadCarY != -1  && getDistanceToLeadCar() < 50) {
			canMove = false;
		}
		
		if (canMove) {
			switch(direction) {
				case NORTH:
					moveNorth();
					break;
				case SOUTH:
					moveSouth();
					break;
				case EAST:
					moveEast();
					break;
				case WEST:
					moveWest();
					break;
				default:
					break;
			}
			ivCar.setX(currentX);
			ivCar.setY(currentY);
		}
		setChanged();
		notifyObservers();
	}
	
	private void moveEast() {
		currentX+=speed;
	}

	private void moveWest() {
		currentX-=speed;
	}

	private void moveNorth() {
		currentY-=speed;
	}

	private void moveSouth() {
		currentY+=speed;
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public void setGateDownFlag(boolean gateDown) {
		this.gateDown = gateDown;
	}
	
	public boolean offScreen() {
		if (currentY > 1020 || currentX < -20)
			return true;
		else
			return false;			
	}
		
	public void reset() {
		currentY = originalY;
	}
	
	public double getDistanceToLeadCar() {
		return Math.hypot(leadCarX-getVehicleX(), leadCarY-getVehicleY());
	}
	
	public void removeLeadCar(){
		leadCarY = -1;
	}

	@Override
	public void update(Observable o, Object arg1) {
		if (o instanceof Car) {
			leadCarX = (((Car)o).getVehicleX());
			leadCarY = (((Car)o).getVehicleY());
			if (leadCarY > 1020 || leadCarX < -20) {
				leadCarY = -1;
			}
		}
			
		if (o instanceof CrossingGate) {
			CrossingGate gate = (CrossingGate)o;
			if(gate.getTrafficCommand() == "STOP") {				
				gateDown = true;
			} else {
				gateDown = false;				
			}
		}				
	}	
}
