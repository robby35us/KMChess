package constraints;
import moves.ActualMove;
import moves.Move;

/*
 * A MoveConstraint is simply a restriction on a move. Each constraint class 
 * must implement the meetsConstraint method, which returns true if the
 * constraint conditions are satisfied, and false otherwise.
 */
public interface MoveConstraint {
	
	/*
	 * The meetsConstraint method looks at the lastMove object, which is often
	 * a "non-move" , such as Touch, but can be an ActualMove object, and checks 
	 * the next proposed ActualMove object to see if the proposed move meets the 
	 * conditions for this constraint.
	 */
	public boolean meetsConstraint(Move lastMove, ActualMove nextMove);
}
