package cardEffects;

import java.awt.Color;
import java.util.ArrayList;

import abstractClasses.ContEffect;
import constraints.DisabledMove;
import controllers.SpaceController;
import enums.File;
import enums.MoveType;
import enums.PieceType;
import enums.Rank;
import game.GameState;
import game.GameWindow;
import graphics.SpaceBorder;
import models.BoardModel;
import pieces.Piece;
import static constants.Constants.*;

public class Fatal_Attraction extends ContEffect {

	private Piece magnet;
	private SpaceController magnetSpace;
	private DisabledMove disabled;
	

	@Override
	public synchronized void startContEffect(GameState gs) {
		BoardModel board = gs.getBoard().getModel();
		GameWindow.globalGW.infoBox("Select a piece to become a magnet!", getName());
		board.setStartSpace(null);
		board.setEndSpace(null);
		while(board.getStartSpace() == null){
			try {
				Thread.sleep(100);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		magnetSpace = board.getStartSpace();
		magnet = magnetSpace.getPiece();
		ArrayList<Piece> adjacent = getAdjacentPieces();
		disabled = DisabledMove.disabled;
		for( Piece p : adjacent){
			ArrayList<MoveType> types = p.getMoveTypes();
			if(p.getType() == PieceType.King)
				continue;
			for(MoveType type : types){
				p.getConstraints(type).add(disabled);
			}
		}
		//board.infoBox("The " + magnet.getType() + " at space " + magnetSpace.getFile() + magnetSpace.getRank() + 
		//				" is now a magnet!." +
		//				"\nNo piece (except a king) that is currently in or enters the eight " + 
		//				"\nadjacent squares can move until the magnet moves or is captured.", contEffectName);
		
		board.setStartSpace(null);
		board.activeSpace.unarmSpace();
		board.activeSpace = null;
	}
		
	private ArrayList<Piece> getAdjacentPieces() {
		ArrayList<Piece> pieces = new ArrayList<Piece>();
		ArrayList<SpaceController> spaces = getAdjacentSpaces();
		for(SpaceController s : spaces){
			if(s.getPiece() != null)
				pieces.add(s.getPiece());
		}
		return pieces;
	}

	private ArrayList<SpaceController> getAdjacentSpaces() {
		ArrayList<SpaceController> spaces = new ArrayList<SpaceController>();
		Rank mRank = magnetSpace.getRank();
		File mFile = magnetSpace.getFile();
		SpaceController adjacentSpace;
		
		// get top row pieces
		if(mRank != Rank.Eight){
			//get top middle
			adjacentSpace = magnetSpace.getSpaceForward();
			spaces.add(adjacentSpace);
			if(mFile != File.A){
				// get top left
				SpaceController left = adjacentSpace.getSpaceLeft();
				spaces.add(left);
			}
			if(mFile != File.H){
				// get top right
				SpaceController right = adjacentSpace.getSpaceRight();
				spaces.add(right);
			}
		}
		// get middle row pieces
		if(mFile != File.A){
			adjacentSpace = magnetSpace.getSpaceLeft();
			spaces.add(adjacentSpace);
		}
		if(mFile != File.H) {
			adjacentSpace = magnetSpace.getSpaceRight();
			spaces.add(adjacentSpace);
		}
		// get bottom row pieces
		if(mRank != Rank.One){
			//get top middle
			adjacentSpace = magnetSpace.getSpaceBackward();
			
			spaces.add(adjacentSpace);
			if(mFile != File.A){
				// get top left
				SpaceController left = adjacentSpace.getSpaceLeft();
				spaces.add(left);
			}
			if(mFile != File.H){
				// get top right
				SpaceController right = adjacentSpace.getSpaceRight();
				spaces.add(right);
			}
		}
		return spaces;
	}

	@Override
	public boolean endCondMet(GameState gs) {	
		return magnetSpace.getPiece() != magnet;
	}

	@Override
	public void endContEffect(GameState gs) {
		ArrayList<Piece> adjacent = getAdjacentPieces();
		for( Piece p : adjacent){
			ArrayList<MoveType> types = p.getMoveTypes();
			if(p.getType() == PieceType.King)
				continue;
			for(MoveType type : types){
				p.getConstraints(type).remove(disabled);
			}
		}
		GameWindow.globalGW.infoBox("The magnet at space " + magnetSpace.getFile() + magnetSpace.getRank() + 
						  " has been moved or captured." +
						  "\nThe pieces in the adjacent spaces are now freed."
						  , getName());
	}

	@Override
	public void updateContEffect(GameState gs) {
		ArrayList<Piece> adjacent = getAdjacentPieces();
		for( Piece p : adjacent){
			ArrayList<MoveType> types = p.getMoveTypes();
			if(p.getType() == PieceType.King)
				continue;
			for(MoveType type : types){
				if(!p.getConstraints(type).contains(disabled))
					p.getConstraints(type).add(disabled);
			}
		}		
	}

	@Override
	public void highlightChange(GameState gs) {
		SpaceBorder magnetBorder = new SpaceBorder(Color.RED);
		//magnetSpace.getSpaceView().setArmedBorder(magnetBorder);
		magnetSpace.getSpaceView().setUnarmedBorder(magnetBorder);
		magnetSpace.getSpaceView().repaint();
		GameWindow.globalGW.infoBox("The " + magnet.getType() + " at the red space " + 
							  "\n" + magnetSpace.getFile() + 
							  magnetSpace.getRank() + " is the magenet."
							  , getName());
		ArrayList<SpaceController> adjacentSpaces = getAdjacentSpaces();
		SpaceBorder adjacentBorder = new SpaceBorder(Color.YELLOW);
		for(SpaceController as: adjacentSpaces){
			//as.getSpaceView().setArmedBorder(adjacentBorder);
			as.getSpaceView().setUnarmedBorder(adjacentBorder);
			as.getSpaceView().repaint();
		}
		GameWindow.globalGW.infoBox("The pieces in the adjacent yellow spaces " +
							   "\n(except for kings) are stuck until the " + 
							   "\nmagnet moves or is captured.", getName());
	}

	@Override
	public void endHighlightChange(GameState gs) {
		//magnetSpace.getSpaceView().setArmedBorder(defaultArmedBorder);
		magnetSpace.getSpaceView().setUnarmedBorder(defaultUnarmedBorder);
		magnetSpace.getSpaceView().repaint();
		ArrayList<SpaceController> adjacentSpaces = getAdjacentSpaces();
		for(SpaceController as: adjacentSpaces) {
			//as.getSpaceView().setArmedBorder(efaultArmedBorder);
			as.getSpaceView().setUnarmedBorder(defaultUnarmedBorder);
			as.getSpaceView().repaint();
		}
	}


	@Override
	public void updateHighlighting(GameState gs) {
		//intentionally left blank
	}


	@Override
	public boolean playable(GameState gs) {
		return true;
	}
}
