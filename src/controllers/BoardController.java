package controllers;

import static definitions.Constants.*;

import java.util.ArrayList;

import definitions.Controller;
import definitions.File;
import definitions.Rank;
import definitions.Visitable;
import definitions.Visitor;
import models.BoardModel;
import models.SpaceModel;
import views.BoardView;
import views.SpaceView;

public class BoardController extends Controller implements Visitable {
	
	   
	
	
	private Rank rank;
	private File file;
	private SpaceController currentChild = null;
	
	private BoardView view;
	private BoardModel model;
	
	public BoardController(){

		childControllers = new ArrayList<Controller>(numRanks * numFiles);
		for(int i = numRanks - 1;  i >=0 ; i--){
			rank = Rank.values()[i];
			for(int j = 0; j < numFiles; j++){
				file = File.values()[j];
				createChildTriad();
				childControllers.add(currentChild);
			}
		}
		view = new BoardView(childControllers.iterator());
		model = new BoardModel(view);		
		for(Controller sc : childControllers){
			((SpaceController) sc).setParentModel();
		}
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
	

	
	public BoardView getView(){
		return view;
	}
	
	public BoardModel getModel(){
		return model;
	}

	@Override
	public Object accept(Visitor e) {
		return model;
	}

	@Override
	public void createChildTriad() {
		SpaceView sView = new SpaceView();
		SpaceModel sModel = new SpaceModel(rank, file, sView);
		currentChild = new SpaceController(sView, sModel, this);
	}
}
