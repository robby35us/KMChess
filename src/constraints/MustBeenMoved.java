package constraints;
import abstractClasses.ActualMove;
import abstractClasses.Move;
import interfaces.MoveConstraint;

/*
 * Verifies that the moving piece has been moved prior to this one.
 */
public class MustBeenMoved implements MoveConstraint {

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
		return !isFirstMove || lastMove.getInitialSpace().getPiece().beenMoved();
	}

}
