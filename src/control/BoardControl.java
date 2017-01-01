package control;

import java.util.ArrayList;
import java.util.List;

import enums.File;
import enums.Rank;
import presentation.BoardPresentation;

public class BoardControl {
	public static final int numRanks = Rank.values().length;
	public static final int numFiles = File.values().length;
	public SpaceControl activeSpace = null;
	   
	
	volatile private SpaceControl startSpace = null;
	volatile private SpaceControl endSpace = null;
	
	
	private BoardPresentation pres;
	//private BoardAbstraction abs;
	
	private List<SpaceControl> spaces;
	
	public BoardControl(){
		spaces = new ArrayList<SpaceControl>(numRanks * numFiles);
		for(int i = numRanks - 1;  i >=0 ; i--)
			for(int j = 0; j < numFiles; j++)
				spaces.add(new SpaceControl(Rank.values()[i], File.values()[j], this));
		pres = new BoardPresentation(spaces.iterator());
		//abs = new BoardAbstraction();
	}
	
	/*
	 * Returns the Space on the Board at the given coordinates.
	 */
	public SpaceControl getSpace(Rank rank, File file){
		if(rank == null || file == null)
			return null;
		return spaces.get((numRanks - (rank.ordinal() + 1)) * numFiles + file.ordinal());
	}
	
	/*
	 * This function finds another space(the next space) on the board in relation to
	 * a given space (initialSpace) using the rankOffset and fileOffset. It returns 
	 * null if there is no such space. 
	 */
	public SpaceControl getNextSpace(int rankOffset, int fileOffset, SpaceControl initialSpace){
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
	
	public void setStartSpace(SpaceControl space){
		if(startSpace != null)
			startSpace.unarmSpace();
		if(space != null)
			space.armSpace();
		startSpace = space;
	}
	
	public SpaceControl getStartSpace(){
		return startSpace;
	}
	
	public void setEndSpace(SpaceControl space){
		if(endSpace != null)
			endSpace.unarmSpace();
		if(space != null)
			space.armSpace();
		endSpace = space;
	}

	public SpaceControl getEndSpace(){
		return endSpace;
	}	
	
	public BoardPresentation getPresentation(){
		return pres;
	}
}
