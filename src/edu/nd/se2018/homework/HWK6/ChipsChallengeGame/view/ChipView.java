package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.view;

import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.Chip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ChipView {

	Chip 		chip;
	Image 		chipUpImage, chipDownImage, chipLeftImage, chipRightImage;
	ImageView 	currentImageView;
	
	private final int scale;
	
	public ChipView(Chip chip, int scale, Direction direction) {
		this.chip = chip;
		this.scale = scale;
		
		// Initialize all images.
		this.chipUpImage	= new Image("images/chip/textures/chipUp.png", scale, scale, true, true);
		this.chipDownImage  = new Image("images/chip/textures/chipDown.png", scale, scale, true, true);
		this.chipLeftImage  = new Image("images/chip/textures/chipLeft.png", scale, scale, true, true);
		this.chipRightImage = new Image("images/chip/textures/chipRight.png", scale, scale, true, true);

		this.currentImageView = new ImageView();
		
		// Set initial direction.
		setImageView(direction);
	}

	public ImageView getImageView() {
		return currentImageView;
	}
	
	public void setImageView(Direction direction) {
		switch(direction) {
		case NORTH:
			currentImageView.setImage(chipUpImage);
			break;
		case SOUTH:
			currentImageView.setImage(chipDownImage);
			break;
		case EAST:
			currentImageView.setImage(chipRightImage);
			break;
		case WEST:
			currentImageView.setImage(chipLeftImage);
			break;
		default:
			// Default down...
			currentImageView.setImage(chipDownImage);
			break;
		}
		
		currentImageView.setX(chip.getCoordinates().x*scale);
		currentImageView.setY(chip.getCoordinates().y*scale);
	}
}
