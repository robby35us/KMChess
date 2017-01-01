package constraints;

import abstractClasses.ActualMove;
import abstractClasses.Move;
import interfaces.MoveConstraint;

public class DisabledMove implements MoveConstraint {

	public static final DisabledMove disabled = new DisabledMove(); 
	@Override
	public boolean meetsConstraint(Move lastMove, ActualMove nextMove) {
		return false;
	}

}
