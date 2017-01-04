package control;

import java.util.ArrayList;
import java.util.List;

import abstractClasses.AppEvent;
import abstraction.BoardModel;
import abstraction.SpaceModel;

import static constants.Constants.*;
import enums.File;
import enums.Rank;
import interfaces.Visitable;
import interfaces.Visitor;
import presentation.BoardView;
import presentation.SpaceView;

public class BoardController extends Controller implements Visitable {
	
	public SpaceController activeSpace = null;
	   
	
	volatile private SpaceController startSpace = null;
	volatile private SpaceController endSpace = null;
	
	private Rank rank;
	private File file;
	private SpaceController currentChild = null;
	
	private BoardView view;
	private BoardModel model;
	
	public BoardController(){
		childControllers = new ArrayList<Controller>(numRanks * numFiles);
		for(int i = numRanks - 1;  i >=0 ; i--)
			for(int j = 0; j < numFiles; j++){
				createChildTriad();
				childControllers.add(currentChild);
			}
		view = new BoardView(childControllers.iterator());
		model = new BoardModel(view);
	}
	
	/*
	 * Returns the Space on the Board at the given coordinates.
	 */
	public SpaceController getSpace(Rank rank, File file){
		if(rank == null || file == null)
			return null;
		return (SpaceController) childControllers.get((numRanks - (rank.ordinal() + 1)) * numFiles + file.ordinal());
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
	public void accept(AppEvent e) {
		e.setData(model);
	}

	@Override
	public void createChildTriad() {
		SpaceView sView = new SpaceView();
		SpaceModel sModel = new SpaceModel(rank, file, sView);
		currentChild = new SpaceController(sView, sModel, this);
	}

	@Override
	public void handleEvent(AppEvent e) {
		
	}
}
