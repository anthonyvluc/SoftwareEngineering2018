package edu.nd.se2018.homework.HWK6.ChipsChallengeGame.controller;

import java.awt.Point;

import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model.LevelMap;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.model.PortalState;
import edu.nd.se2018.homework.HWK6.ChipsChallengeGame.view.PortalView;
import javafx.scene.image.Image;

public class Portal {

	PortalState	portalState;
	PortalView 	portalView;
	
	public Portal(Point coordinates, LevelMap levelMap, int scale) {
		// Set state.
		this.portalState = new PortalState(coordinates, levelMap);
		
		// Set initial image.
		this.portalView = new PortalView(this, scale);
	}

	public Image getImage() {
		return portalView.getImage();
	}
	
	public Point getCoordinates() {
		return portalState.getCoordinates();
	}
	
}
