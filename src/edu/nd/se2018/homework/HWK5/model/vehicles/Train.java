package edu.nd.se2018.homework.HWK5.model.vehicles;

import java.util.Observable;

import edu.nd.se2018.homework.HWK5.model.infrastructure.Direction;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents the train entity object
 * @author janes
 *
 */
public class Train extends Observable implements IVehicle{	
	private double currentX = 0;
	private double currentY = 0;
	private double originalX = 0;
	private Image img;
	private ImageView imgView;
	private int trainLength = 35;
	private int speed;
	private Direction direction;
	
	public Train(int x, int y, int speed, Direction direction){
		this.currentX  = x;
		this.currentY  = y;
		this.speed     = speed;
		this.direction = direction;
		originalX = x;
		img = new Image("images/Train.PNG",120,trainLength,false,false);
		if (direction == Direction.WEST) {
			imgView = new ImageView(img);
		} else {
			// Assume it is EAST
			imgView = new ImageView(img);
			imgView.setScaleX(-1);
		}
		imgView.setX(currentX);
		imgView.setY(currentY);
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public double getVehicleX(){
		return currentX;
	}
	
	public double getVehicleY(){
		return currentY;
	}
	
	public void move(){
		if (direction == Direction.WEST) {
			currentX-=speed;
		} else {
			// Assume it is EAST
			currentX+=speed;
		}
		imgView.setX(currentX);
		setChanged();
		notifyObservers();
	}
	
	public boolean offScreen(){
		if (currentX < -200 || currentX > 1400)
			return true;
		else
			return false;				
	}
	
	public void reset(){
		currentX = originalX;
	}

	//@Override
	public Node getImageView() {
		return imgView;
	}
}
