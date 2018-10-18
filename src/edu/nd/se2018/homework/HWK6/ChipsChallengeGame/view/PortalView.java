package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.view;

import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller.Portal;
import javafx.scene.image.Image;

public class PortalView {

	Portal		portal;
	Image 		portalImage;
	
	public PortalView(Portal portal, int scale) {
		
		// Initialize portal variables.
		this.portal 			= portal;
		this.portalImage		= new Image("images/chip/textures/portal.png", scale, scale, true, true);
	}

	public Image getImage() {
		return portalImage;
	}
}
