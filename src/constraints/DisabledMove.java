package constraints;

import definitions.ActualMove;
import definitions.Move;
import definitions.MoveConstraint;

public class DisabledMove implements MoveConstraint {

	public static final DisabledMove disabled = new DisabledMove(); 
	@Override
	public boolean meetsConstraint(Move lastMove, ActualMove nextMove) {
		return false;
	}

}
