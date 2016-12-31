package cardEffects;

import java.awt.Color;
import java.util.ArrayList;

import components.Board;
import components.Piece;
import components.Space;
import constraints.DisabledMove;
import definitions.File;
import definitions.MoveType;
import definitions.PieceType;
import definitions.Rank;
import game.GameState;
import graphics.SpaceBorder;

public class Fatal_Attraction extends ContEffect {

	private Piece magnet;
	private Space magnetSpace;
	private DisabledMove disabled;
	

	@Override
	public synchronized void startContEffect(GameState gs) {
		Board board = gs.getBoard();
		board.infoBox("Select a piece to become a magnet!", getName());
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
		Space.activeSpace.setButtonState(Space.UNARMED);
		Space.activeSpace = null;
	}
		
	private ArrayList<Piece> getAdjacentPieces() {
		ArrayList<Piece> pieces = new ArrayList<Piece>();
		ArrayList<Space> spaces = getAdjacentSpaces();
		for(Space s : spaces){
			if(s.getPiece() != null)
				pieces.add(s.getPiece());
		}
		return pieces;
	}

	private ArrayList<Space> getAdjacentSpaces() {
		ArrayList<Space> spaces = new ArrayList<Space>();
		Rank mRank = magnetSpace.getRank();
		File mFile = magnetSpace.getFile();
		Space adjacentSpace;
		
		// get top row pieces
		if(mRank != Rank.Eight){
			//get top middle
			adjacentSpace = magnetSpace.getSpaceForward();
			spaces.add(adjacentSpace);
			if(mFile != File.A){
				// get top left
				Space left = adjacentSpace.getSpaceLeft();
				spaces.add(left);
			}
			if(mFile != File.H){
				// get top right
				Space right = adjacentSpace.getSpaceRight();
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
				Space left = adjacentSpace.getSpaceLeft();
				spaces.add(left);
			}
			if(mFile != File.H){
				// get top right
				Space right = adjacentSpace.getSpaceRight();
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
		gs.getBoard().infoBox("The magnet at space " + magnetSpace.getFile() + magnetSpace.getRank() + 
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
		magnetSpace.setArmedBorder(magnetBorder);
		magnetSpace.setUnarmedBorder(magnetBorder);
		magnetSpace.repaint();
		gs.getBoard().infoBox("The " + magnet.getType() + " at the red space " + 
							  "\n" + magnetSpace.getFile() + 
							  magnetSpace.getRank() + " is the magenet."
							  , getName());
		ArrayList<Space> adjacentSpaces = getAdjacentSpaces();
		SpaceBorder adjacentBorder = new SpaceBorder(Color.YELLOW);
		for(Space as: adjacentSpaces){
			as.setArmedBorder(adjacentBorder);
			as.setUnarmedBorder(adjacentBorder);
			as.repaint();
		}
		gs.getBoard().infoBox("The pieces in the adjacent yellow spaces " +
							   "\n(except for kings) are stuck until the " + 
							   "\nmagnet moves or is captured.", getName());
	}

	@Override
	public void endHighlightChange(GameState gs) {
		magnetSpace.setArmedBorder(Space.defaultArmedBorder);
		magnetSpace.setUnarmedBorder(Space.defaultUnarmedBorder);
		magnetSpace.repaint();
		ArrayList<Space> adjacentSpaces = getAdjacentSpaces();
		for(Space as: adjacentSpaces) {
			as.setArmedBorder(Space.defaultArmedBorder);
			as.setUnarmedBorder(Space.defaultUnarmedBorder);
			as.repaint();
		}
	}


	@Override
	public void updateHighlighting(GameState gs) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean playable(GameState gs) {
		return true;
	}
}
