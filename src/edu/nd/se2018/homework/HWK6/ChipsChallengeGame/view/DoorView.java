package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.view;

import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.Door;
import javafx.scene.image.Image;

public class DoorView {

	Door 	door;
	Image	doorImage;
		
	public DoorView(Door door, int scale, String path) {
		this.door = door;
		
		doorImage = new Image(path, scale, scale, true, true);
	}
	
	public Image getImage() {
		return doorImage;
	}
	
	public void setImage(Image i) {
		doorImage = i;
	}

}
