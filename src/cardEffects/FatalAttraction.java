package cardEffects;

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

public class FatalAttraction extends ContEffect {

	private Piece magnet;
	private Space magnetSpace;
	private DisabledMove disabled;
	
	@Override
	public void initiateImmediateEffect(GameState gs) {
		// intentionally left empty
	}

	@Override
	public synchronized void startContEffect(GameState gs) {
		Board board = gs.getBoard();
		board.infoBox("Select a piece to become a magnet!", "Fatal Attraction");
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
		disabled = new DisabledMove();
		for( Piece p : adjacent){
			ArrayList<MoveType> types = p.getMoveTypes();
			if(p.getType() == PieceType.King)
				continue;
			for(MoveType type : types){
				p.getConstraints(type).add(disabled);
			}
		}
		board.setStartSpace(null);
		Space.activeSpace.setButtonState(Space.UNARMED);
		Space.activeSpace = null;
	}
		
	private ArrayList<Piece> getAdjacentPieces() {
		ArrayList<Piece> pieces = new ArrayList<Piece>();
		Rank mRank = magnetSpace.getRank();
		File mFile = magnetSpace.getFile();
		Space adjacentSpace;
		
		// get top row pieces
		if(mRank != Rank.Eight){
			//get top middle
			adjacentSpace = magnetSpace.getSpaceForward();
			if(adjacentSpace.getPiece() != null)
				pieces.add(adjacentSpace.getPiece());
			if(mFile != File.A){
				// get top left
				Space left = adjacentSpace.getSpaceLeft();
				if(left.getPiece() != null)
					pieces.add(left.getPiece());
			}
			if(mFile != File.H){
				// get top right
				Space right = adjacentSpace.getSpaceRight();
				if(right.getPiece() != null)
					pieces.add(right.getPiece());
			}
		}
		// get middle row pieces
		if(mFile != File.A){
			adjacentSpace = magnetSpace.getSpaceLeft();
			if(adjacentSpace.getPiece() != null)
				pieces.add(adjacentSpace.getPiece());
		}
		if(mFile != File.H) {
			adjacentSpace = magnetSpace.getSpaceRight();
			if(adjacentSpace.getPiece() != null)
				pieces.add(adjacentSpace.getPiece());
		}
		// get bottom row pieces
		if(mRank != Rank.One){
			//get top middle
			adjacentSpace = magnetSpace.getSpaceBackward();
			if(adjacentSpace.getPiece() != null)
				pieces.add(adjacentSpace.getPiece());
			if(mFile != File.A){
				// get top left
				Space left = adjacentSpace.getSpaceLeft();
				if(left.getPiece() != null)
					pieces.add(left.getPiece());
			}
			if(mFile != File.H){
				// get top right
				Space right = adjacentSpace.getSpaceRight();
				if(right.getPiece() != null)
					pieces.add(right.getPiece());
			}
		}
		return pieces;
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

}
