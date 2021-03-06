package constraints;

import controllers.SpaceController;
import definitions.ActualMove;
import definitions.Move;
import definitions.MoveConstraint;
import pieces.Piece;


/*
 * Verifies that the move will result in a capture.
 * NOTE: CURRENTLY CANNOT BE USED FOR EN PASSABT WILL
 * CHANGE IF NEEDED
 */
public class MustCapture implements MoveConstraint {

	
	@Override
	/*
	 * The meetsConstraint method looks at the lastMove object, which is often
	 * a "non-move" , such as Touch, but can be an ActualMove object, and checks 
	 * the next proposed ActualMove object to see if the proposed move meets the 
	 * conditions for this constraint. Space is the location of the piece that 
	 * is moving, or rather, the initial space of the proposed move.
	 */
	public boolean meetsConstraint(Move lastMove, ActualMove nextMove) {
		SpaceController initialSpace = lastMove.getInitialSpace();
		SpaceController finalDestSpace = lastMove.getFinalDestSpace();
		Piece defender = finalDestSpace.getPiece();
		return defender != null && defender.getColor() != initialSpace.getPiece().getColor();
	}

}
