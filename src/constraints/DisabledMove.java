package constraints;

import moves.ActualMove;
import moves.Move;

public class DisabledMove implements MoveConstraint {

	public static final DisabledMove disabled = new DisabledMove(); 
	@Override
	public boolean meetsConstraint(Move lastMove, ActualMove nextMove) {
		return false;
	}

}
