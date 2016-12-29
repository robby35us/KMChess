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
		board.infoBox("Select a piece (not a pawn) to move as if it were a queen.", cardName);
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
		
		Space startSpace = board.getStartSpace();
		Piece selected = startSpace.getPiece();
		Piece modifiedQueen = (new PieceFactory(board, gs)).makePiece(PieceType.Queen, board.getTurn().getColor());
		ArrayList<MoveType> moves = modifiedQueen.getMoveTypes();
		for(MoveType mt : moves){
			ArrayList<MoveConstraint> constraints = modifiedQueen.getConstraints(mt);
			constraints.add(new CannotCapture(board));
		}
		startSpace.changePiece(modifiedQueen, false);
		ErrorMessage message;
		do {
			message = new ErrorMessage();
			board.infoBox("Now choose a space to move the selected " +
					  selected.getType() + " to." +
					  "\nThis move cannot make a capture.", cardName);
			while(board.getEndSpace() == null || 
				  board.getEndSpace().getPiece() != null){
				try {
					Thread.sleep(100);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			
		}while(MoveBuilder.buildMoveObject(startSpace, board.getEndSpace(), gs, message) == null || message.hasError());
		startSpace.changePiece(null, true);
		board.getEndSpace().changePiece(selected, true);
	}	

}
