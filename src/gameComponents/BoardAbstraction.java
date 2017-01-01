package gameComponents;

import enums.File;
import enums.Rank;
import enums.SpaceColor;

public class BoardAbstraction {

	private SpacePresentation[][] spaces;
	public BoardAbstraction () {
		spaces = new SpacePresentation[Rank.values().length][File.values().length];
		for(Rank r : Rank.values())
			for(File f : File.values()){
				int ro = r.ordinal();
				int fo = f.ordinal();
				SpaceColor color = null;
				if((ro + fo ) % 2 == 0)
					color = SpaceColor.White;
				else
					color = SpaceColor.Gray;
				spaces[ro][fo] = new SpacePresentation(r,f, this, color, null);
			}
	}
	
	/*
	 * This function finds another space(the next space) on the board in relation to
	 * a given space (initialSpace) using the rankOffset and fileOffset. It returns 
	 * null if there is no such space. 
	 */
	public SpacePresentation getNextSpace(int rankOffset, int fileOffset, SpacePresentation initialSpace){
		if(initialSpace == null)
			return null;
		int newRank = initialSpace.getRank().ordinal() + rankOffset;
		int newFile = initialSpace.getFile().ordinal() + fileOffset;
		if(newRank >= 0 && newRank < Rank.values().length &&
		   newFile >= 0 && newFile < File.values().length)
			return spaces[newRank][newFile];
		else{
			// No such space exists!!!
			return null;
		}
	}
}
