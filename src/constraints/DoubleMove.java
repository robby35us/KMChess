package constraints;
import moves.ActualMove;
import moves.Move;

// NOTE: This constraint does not currently guarantee that
// two moves were made. Changes need to be made to 
// ensure that the class works as advertised.
public class DoubleMove implements MoveConstraint {

	@Override
	/*
	 * The meetsConstraint method looks at the lastMove object, which is often
	 * a "non-move" , such as Touch, but can be an ActualMove object, and checks 
	 * the next proposed ActualMove object to see if the proposed move meets the 
	 * conditions for this constraint. Space is the location of the piece that 
	 * is moving, or rather, the initial space of the proposed move.
	 */
	public boolean meetsConstraint(Move lastMove, ActualMove nextMove) {
		boolean isFirstMove = lastMove.getClass().getSuperclass() == Move.class;
		if(isFirstMove)
			return true;
		else {
			ActualMove lastActual = (ActualMove) lastMove;
			if(lastActual.getLastMove().getClass().getSuperclass() == Move.class)
				return true;
			else
				return false;
		}
	}
}
