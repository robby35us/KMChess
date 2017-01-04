package control;

import java.util.ArrayList;
import java.util.List;

import abstraction.BoardModel;

import static constants.Constants.*;
import enums.File;
import enums.Rank;
import interfaces.Visitable;
import interfaces.Visitor;
import presentation.BoardView;

public class BoardController implements Visitable {
	
	public SpaceController activeSpace = null;
	   
	
	volatile private SpaceController startSpace = null;
	volatile private SpaceController endSpace = null;
	
	
	private BoardView view;
	private BoardModel model;
	
	public BoardController(){
		
		view = new BoardView(spaces.iterator());
		model = new BoardModel(view);
	}
	
	/*
	 * Returns the Space on the Board at the given coordinates.
	 */
	public SpaceController getSpace(Rank rank, File file){
		if(rank == null || file == null)
			return null;
		return spaces.get((numRanks - (rank.ordinal() + 1)) * numFiles + file.ordinal());
	}
	
	/*
	 * This function finds another space(the next space) on the board in relation to
	 * a given space (initialSpace) using the rankOffset and fileOffset. It returns 
	 * null if there is no such space. 
	 */
	public SpaceController getNextSpace(int rankOffset, int fileOffset, SpaceController initialSpace){
		if(initialSpace == null)
			return null;
		int newRank = initialSpace.getRank().ordinal() + rankOffset;
		int newFile = initialSpace.getFile().ordinal() + fileOffset;
		if(newRank >= 0 && newRank < Rank.values().length &&
		   newFile >= 0 && newFile < File.values().length)
			return getSpace(Rank.values()[newRank], 
							File.values()[newFile]);
		else
			return null;
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
	
	public BoardView getPresentation(){
		return view;
	}

	@Override
	public Object accept(Visitor visitor) {
		// TODO Auto-generated method stub
		return null;
	}
}
