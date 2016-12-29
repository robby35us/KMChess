package game;
import java.util.ArrayList;

import constraints.MoveConstraint;
import moves.ActualMove;
import moves.Move;
import utility.ErrorMessage;

/*
 * This class is used to composite two moves by wrapping one around the other.
 * lastMove is the Move to be wrapped and nextMove is the wrapping ActualMove. Before 
 * it does that, however, it verifies that all the constraints for the combination 
 * are met, using the list provided by the Piece at lastMove.getInitialSpace() for 
 * the nextMove.
 */
public class MoveCompositor {
	
	/*
	 * lastMove is the previous move to wrap, nextMove is the wrapping move, and message is
	 * ErrorMessage object used for reporting any errors. Returns the composited move, or null
	 * if the constraints are not met.
	 */
	public static ActualMove compositeMoves(Move lastMove, ActualMove nextMove, ErrorMessage message){
		ArrayList<MoveConstraint> constraints = lastMove.getInitialSpace().getPiece().getConstraints(nextMove.getMoveType());
		
		//System.out.println(nextMove.getMoveType());
		// verify that the move is a legal movement type for this piece
		if(constraints == null){
			message.setIllegalPattern();
			return null;
		}
		
		// for each constraint....
		for(MoveConstraint c : constraints)
			// verify that the constraint is met
			if(!c.meetsConstraint(lastMove, nextMove)){
				//System.out.println("The constraint " + c.toString() + " was not met!");
				message.setConstraintNotMet();
				return null;
			}
		
		//System.out.println("All constraints for " + nextMove + " for piece " + lastMove.getInitialSpace().getPiece().getType() 
		//		+ " for the " + lastMove.getInitialSpace().getPiece().getColor() + " player were met.");
		// returns the nextMove wrapped around the lastMove
		return nextMove.setLastMove(lastMove);
	}
}
