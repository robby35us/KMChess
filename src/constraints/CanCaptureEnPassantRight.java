package constraints;
import abstractClasses.ActualMove;
import abstractClasses.Move;
import controllers.SpaceController;
import enums.*;
import game.GameState;

import interfaces.MoveConstraint;
import pieces.Piece;
import standardMoves.*;

/*
 * Verifies that a piece(pawn) can move EnPassant to the right
 * (from his player's point of view) and capture the pawn to 
 * it's right that just moved.
 */
public class CanCaptureEnPassantRight implements MoveConstraint {

	GameState gs;
	
	public CanCaptureEnPassantRight(GameState gs){
		this.gs = gs;
	}
	
	@Override
	/*
	 * The meetsConstraint method looks at the lastMove object, which is often
	 * a "non-move" , such as Touch, but can be an ActualMove object, and checks 
	 * the next proposed ActualMove object to see if the proposed move meets the 
	 * conditions for this constraint. Space is the location of the piece that 
	 * is moving, or rather, the initial space of the proposed move.
	 */
	public boolean meetsConstraint(Move lastMove, ActualMove nextMove) {
		SpaceController initial = lastMove.getInitialSpace();
		Piece moving = initial.getPiece();
		
		// verify that the piece is a pawn
		if(moving.getType() != PieceType.Pawn)
			return false;
		PieceColor color = moving.getColor();
		
		// verify that the piece is in the correct rank
		if((color == PieceColor.White && initial.getRank() != Rank.Five) || 
		   (color == PieceColor.Black && initial.getRank() != Rank.Four))
			return false;
		
		// verify that the move in question is MoveEnPassantRight
		if(nextMove.getClass() != MoveEnPassantRight.class){
			return false;
		}
		Move prevMove = gs.getPreviousMove();
		SpaceController toRight = prevMove.getDestinationSpace();
		
		// verify that the adjacent space is the space last moved to
		if((color == PieceColor.White && toRight != initial.getSpaceRight()) ||
		   (color == PieceColor.Black && toRight != initial.getSpaceLeft())) 
			return false;
		Piece toCapture = toRight.getPiece();
		
		// verify that the adjacent piece is a pawn and that the last move
		// was "Forward Two"
		if(toCapture.getType() != PieceType.Pawn || prevMove.getClass() != MoveForwardTwo.class)
			return false;
		
		// meets all conditions
		return true;
	}

}
