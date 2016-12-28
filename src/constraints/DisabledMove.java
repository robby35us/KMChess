package constraints;

import moves.ActualMove;
import moves.Move;

public class DisabledMove implements MoveConstraint {

	@Override
	public boolean meetsConstraint(Move lastMove, ActualMove nextMove) {
		return false;
	}

}
