package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.view;

import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.Item;
import javafx.scene.image.Image;

public class ItemView {

	Item 	item;
	Image	itemImage;
		
	public ItemView(Item item, int scale, String path) {
		this.item = item;
		
		itemImage = new Image(path, scale, scale, true, true);
	}
	
	public Image getImage() {
		return itemImage;
	}

}
