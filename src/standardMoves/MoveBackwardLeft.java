package standardMoves;
import abstractClasses.ActualMove;
import enums.MoveType;
import enums.PieceColor;

/* MoveBackwardLeft.java
 * This is a Move decorator that represents a piece
 * moving one space diagonally both backwards and to 
 * the left, from it's players point of view.
 */
public class MoveBackwardLeft extends ActualMove{
	public MoveBackwardLeft(PieceColor color){
		super(MoveType.BackwardLeft, 
			  color);
	}
}
