package constraints;
import abstractClasses.ActualMove;
import abstractClasses.Move;
import controllers.SpaceController;
import enums.PieceType;
import interfaces.MoveConstraint;
import pieces.King;
import pieces.Piece;

/*
 * Verifies that the piece(king) can castle to the king side of the board.
 * There must be no pieces between the king and the king side rook, and neither
 * the king or that rook can have moved previously. Also, the king cannot move
 * through or into check.
 */
public class CanQueenCastle implements MoveConstraint {

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
		
		// verify that the initial piece is, in fact, a King object
		Piece piece = initial.getPiece();
		if(piece.getType() != PieceType.King)
			return false;
		King king = (King) initial.getPiece();
		
		//check if the king has been moved
		if(king.beenMoved())
			return false;
		
		// get the four spaces to the left of this one( from the white players perspective
		SpaceController oneToLeft = initial.getSpaceLeft();
		SpaceController twoToLeft = oneToLeft.getSpaceLeft();
		SpaceController threeToLeft = twoToLeft.getSpaceLeft();
		SpaceController fourToLeft = threeToLeft.getSpaceLeft();
		
		// verify that the fist three spaces are empty, that the 4rd space has a rook, and that 
		// the rook hasn't been moved. Also check that the king isn't moving through/into check.
		return oneToLeft.getPiece() == null &&
			   king.notifyOpposingPieceObservers(oneToLeft) && 
			   twoToLeft.getPiece() == null &&
			   king.notifyOpposingPieceObservers(twoToLeft) &&
			   threeToLeft.getPiece() == null &&
			   fourToLeft.getPiece() != null &&
			   fourToLeft.getPiece().getType() == PieceType.Rook &&
			   !fourToLeft.getPiece().beenMoved();
	}
}
