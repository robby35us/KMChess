package cardEffects;

import java.util.ArrayList;

import components.Board;
import components.Piece;
import components.Space;
import constraints.CannotCapture;
import constraints.MoveConstraint;
import definitions.MoveType;
import definitions.PieceType;
import factory.PieceFactory;
import game.GameState;
import game.MoveBuilder;
import utility.ErrorMessage;

public class Masquerade extends CardEffect {

	public Masquerade () {
		cardName = "Masquerade";
	}
	
	@Override
	public void initiateImmediateEffect(GameState gs) {
		Board board = gs.getBoard();	
		ErrorMessage message = new ErrorMessage();
		Space startSpace = null;
		Piece selected = null;
		do{
			if(message.hasError())
				board.infoBox(message.toString(), cardName);
			board.infoBox("Select a piece (not a pawn) to move as if it were a queen.", cardName);
			if(startSpace != null){
				startSpace.changePiece(selected, true);
			}
			board.setStartSpace(null);
			board.setEndSpace(null);
			while(board.getStartSpace() == null || 
				  board.getStartSpace().getPiece() == null ||
				  board.getStartSpace().getPiece().getType() == PieceType.Pawn){
				try {
					Thread.sleep(100);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			
			startSpace = board.getStartSpace();
			selected = startSpace.getPiece();
			Piece modifiedQueen = (new PieceFactory(board, gs)).makePiece(PieceType.Queen, selected.getType(), board.getTurn().getColor());
			ArrayList<MoveType> moves = modifiedQueen.getMoveTypes();
			for(MoveType mt : moves){
				ArrayList<MoveConstraint> constraints = modifiedQueen.getConstraints(mt);
				constraints.add(new CannotCapture(board));
			}
			startSpace.changePiece(modifiedQueen, false);
			message = new ErrorMessage();
			board.infoBox("Now choose a space to move the selected " +
					  selected.getType() + " to." +
					  "\nThis move cannot make a capture.", cardName);
			while(board.getEndSpace() == null || 
				  board.getEndSpace().getPiece() != null){
				if(board.getEndSpace() != null && board.getEndSpace().getPiece() != null){ 
					board.infoBox("This move cannot make a capture. Try again!", cardName);
					board.setEndSpace(null);
					board.setStartSpace(null);
				}
				if(board.getStartSpace() == null) {
					startSpace.changePiece(selected, true);
					startSpace = null;
					selected = null;
					break;
				}
				//board.setEndSpace(null);
				try {
					Thread.sleep(100);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			
		}while(startSpace == null || MoveBuilder.buildMoveObject(startSpace, board.getEndSpace(), gs, message) == null || message.hasError());
		
		startSpace.changePiece(null, true);
		board.getEndSpace().changePiece(selected, true);
	}

	@Override
	public boolean playable(GameState gs) {
		return true;
	}	

}
