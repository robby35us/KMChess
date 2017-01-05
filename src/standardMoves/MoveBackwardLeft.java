package standardMoves;
import definitions.ActualMove;
import definitions.MoveType;
import definitions.PieceColor;

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
