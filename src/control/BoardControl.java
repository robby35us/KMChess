package control;

import java.util.ArrayList;
import java.util.List;

import enums.File;
import enums.Rank;
import presentation.BoardPresentation;

public class BoardControl {
	public static final int numRanks = Rank.values().length;
	public static final int numFiles = File.values().length;
	public SpaceController activeSpace = null;
	   
	
	volatile private SpaceController startSpace = null;
	volatile private SpaceController endSpace = null;
	
	
	private BoardPresentation pres;
	//private BoardAbstraction abs;
	
	private List<SpaceController> spaces;
	
	public BoardControl(){
		spaces = new ArrayList<SpaceController>(numRanks * numFiles);
		for(int i = numRanks - 1;  i >=0 ; i--)
			for(int j = 0; j < numFiles; j++)
				spaces.add(new SpaceController(Rank.values()[i], File.values()[j], this));
		pres = new BoardPresentation(spaces.iterator());
		//abs = new BoardAbstraction();
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
	
	public BoardPresentation getPresentation(){
		return pres;
	}
}
