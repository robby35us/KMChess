package constraints;

import moves.ActualMove;
import moves.Move;

/*
 * Verifies that the Move object is not a subclass of move. This is to
 * insure that the move is not a combination of moves.
 */
public class SingleMove implements MoveConstraint {

	@Override
	/*
	 * The meetsConstraint method looks at the lastMove object, which is often
	 * a "non-move" , such as Touch, but can be an ActualMove object, and checks 
	 * the next proposed ActualMove object to see if the proposed move meets the 
	 * conditions for this constraint. Space is the location of the piece that 
	 * is moving, or rather, the initial space of the proposed move.
	 */
	public boolean meetsConstraint(Move lastMove, ActualMove nextMove) {
		return lastMove.getClass().getSuperclass().equals(Move.class);
	}
}
