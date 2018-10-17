package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.view;

import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.Portal;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PortalView {

	Portal		portal;
	Image 		portalImage;
//	ImageView 	currentImageView;
	
	public PortalView(Portal portal, int scale) {
		
		// Initialize portal variables.
		this.portal 			= portal;
//		this.currentImageView 	= new ImageView();
		this.portalImage		= new Image("images/chip/textures/portal.png", scale, scale, true, true);
		
		// Set image.
//		setImageView();
	}

	public Image getImage() {
		return portalImage;
	}
	
//	public ImageView getImageView() {
//		return currentImageView;
//	}
	
//	public void setImageView() {
//		
//	}
}
