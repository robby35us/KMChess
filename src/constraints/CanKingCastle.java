package constraints;
import abstractClasses.ActualMove;
import abstractClasses.Move;
import enums.PieceType;
import gameComponents.SpacePresentation;
import interfaces.MoveConstraint;
import pieces.King;
import pieces.Piece;

/*
 * Verifies that the piece(king) can castle to the king side of the board.
 * There must be no pieces between the king and the king side rook, and neither
 * the king or that rook can have moved previously. Also, the king cannot move
 * through or into check.
 */
public class CanKingCastle implements MoveConstraint{
	
	@Override
	/*
	 * The meetsConstraint method looks at the lastMove object, which is often
	 * a "non-move" , such as Touch, but can be an ActualMove object, and checks 
	 * the next proposed ActualMove object to see if the proposed move meets the 
	 * conditions for this constraint. Space is the location of the piece that 
	 * is moving, or rather, the initial space of the proposed move.
	 */
	public boolean meetsConstraint(Move lastMove, ActualMove nextMove) {
		SpacePresentation initial = lastMove.getInitialSpace();
		
		// verify that the initial piece is, in fact, a King object
		Piece piece = initial.getPiece();
		if(piece.getType() != PieceType.King)
			return false;
		King king = (King) initial.getPiece();
		
		// check if the king has been moved
		if(king.beenMoved())
			return false;
		
		// get the three spaces to the right of this one( from the white players perspective)
		SpacePresentation oneToRight = initial.getSpaceRight();
		SpacePresentation twoToRight = oneToRight.getSpaceRight();
		SpacePresentation threeToRight = twoToRight.getSpaceRight();
		
		// verify that the fist two spaces are empty, that the 3rd space has a rook, and that 
		// the rook hasn't been moved. Also check that the king isn't moving through/into check.
		boolean result = oneToRight.getPiece() == null && 
			   king.notifyOpposingPieceObservers(oneToRight) &&
			   twoToRight.getPiece() == null &&
			   king.notifyOpposingPieceObservers(twoToRight) &&
			   threeToRight.getPiece() != null &&
			   threeToRight.getPiece().getType() == PieceType.Rook &&
			   !threeToRight.getPiece().beenMoved();
//		System.out.println( oneToRight.getPiece());
//		System.out.println(king.notifyOpposingPieceObservers(oneToRight));
//		System.out.println(twoToRight.getPiece());
//		System.out.println(king.notifyOpposingPieceObservers(twoToRight));
//		System.out.println(threeToRight.getPiece());
		return result;
	}
}
