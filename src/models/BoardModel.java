package models;

import controllers.SpaceController;
import views.BoardView;


public class BoardModel {

	volatile private SpaceController startSpace = null;
	volatile private SpaceController endSpace = null;
	public SpaceController activeSpace = null;

	public BoardModel(BoardView view){
	}
	
	public void setStartSpace(SpaceController space){
		if(startSpace != null)
			startSpace.unarmSpace();
		if(space != null)
			space.armSpace();
		startSpace = space;
	}
	
	public SpaceController getStartSpace(){
		return startSpace;
	}
	
	public void setEndSpace(SpaceController space){
		if(endSpace != null)
			endSpace.unarmSpace();
		if(space != null)
			space.armSpace();
		endSpace = space;
	}

	public SpaceController getEndSpace(){
		return endSpace;
	}	
}