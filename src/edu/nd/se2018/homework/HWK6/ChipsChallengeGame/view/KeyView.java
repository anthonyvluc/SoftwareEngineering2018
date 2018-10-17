package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.view;

import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.Key;
import javafx.scene.image.Image;

public class KeyView {

	Key 	key;
	Image	keyImage;
		
	public KeyView(Key key, int scale, String path) {
		this.key = key;
		
		keyImage = new Image(path, scale, scale, true, true);
	}
	
	public Image getImage() {
		return keyImage;
	}

}
