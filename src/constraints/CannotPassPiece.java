package constraints;

import moves.ActualMove;
import moves.Move;

/*
 * Verifies that this move will not "pass through" another 
 * piece on the board. For instance, if the pawn is moving
 * forward two on it's first move, there can't be a piece 
 * between it and it's destination.
 */
public class CannotPassPiece implements MoveConstraint {

	@Override
	/*
	 * The meetsConstraint method looks at the lastMove object, which is often
	 * a "non-move" , such as Touch, but can be an ActualMove object, and checks 
	 * the next proposed ActualMove object to see if the proposed move meets the 
	 * conditions for this constraint. Space is the location of the piece that 
	 * is moving, or rather, the initial space of the proposed move.
	 */
	public boolean meetsConstraint(Move lastMove, ActualMove nextMove) {
		return lastMove.getDestinationSpace().getPiece() == null ||
		       lastMove.getDestinationSpace().getPiece() == lastMove.getInitialSpace().getPiece();
	}

}
