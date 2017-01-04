package constraints;
import abstractClasses.ActualMove;
import abstractClasses.Move;
import control.SpaceController;
import enums.*;
import game.GameState;
import interfaces.MoveConstraint;
import pieces.Piece;
import standardMoves.*;

/*
 * Verifies that a piece(pawn) can move En Passant to the Left
 * (from it's player's point of view) and capture the pawn to 
 * it's left that just moved.
 */
public class CanCaptureEnPassantLeft implements MoveConstraint {

	GameState gs;
	
	public CanCaptureEnPassantLeft(GameState gs){
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
		
		// verify piece is a pawn
		if(moving.getType() != PieceType.Pawn)
			return false;
		PieceColor color = moving.getColor();
		
		// verify the piece is in the correct rank
		if((color == PieceColor.White && initial.getRank() != Rank.Five) || 
		   (color == PieceColor.Black && initial.getRank() != Rank.Four))
			return false;
		
		// verify that the move in question is MoveEnPassantLeft
		if(nextMove.getClass() != MoveEnPassantLeft.class){
			return false;
		}
		Move prevMove = gs.getPreviousMove();
		//System.out.println(prevMove);
		SpaceController toLeft = prevMove.getDestinationSpace();
		
		// verify that the adjacent space is the one that was just moved to
		if((color == PieceColor.White && toLeft != initial.getSpaceLeft()) ||
		   (color == PieceColor.Black && toLeft != initial.getSpaceRight())) 
			return false;
		Piece toCapture = toLeft.getPiece();
		
		// verify that the adjacent piece is a pawn and that it just moved
		// "forward two"
		if(toCapture.getType() != PieceType.Pawn || prevMove.getClass() != MoveForwardTwo.class)
			return false;
		
		// meets all criteria
		return true;
	}
}