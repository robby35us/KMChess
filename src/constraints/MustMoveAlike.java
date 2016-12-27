package constraints;

import moves.ActualMove;
import moves.Move;

/* 
 * Verifies that the "nextMove" has the same "MoveType" as 
 * the previous "move." For instance, when a bishop moves
 * 4 spaces, each component Move object must be the same type.
 * It can't move forward left and then forward right.
 */
public class MustMoveAlike implements MoveConstraint {

	@Override
	/*
	 * The meetsConstraint method looks at the lastMove object, which is often
	 * a "non-move" , such as Touch, but can be an ActualMove object, and checks 
	 * the next proposed ActualMove object to see if the proposed move meets the 
	 * conditions for this constraint. Space is the location of the piece that 
	 * is moving, or rather, the initial space of the proposed move.
	 */
	public boolean meetsConstraint(Move lastMove, ActualMove nextMove) {
		if(lastMove.getClass().getSuperclass().equals(Move.class))
			return true;
		else
			return lastMove.getClass().equals(nextMove.getClass());
	}

}
