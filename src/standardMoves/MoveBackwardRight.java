package standardMoves;
import abstractClasses.ActualMove;
import enums.MoveType;
import enums.PieceColor;

/* MoveBackwardRight.java
 * This is a Move decorator that represents a piece
 * moving one space diagonally both backwards and to 
 * the right, from it's players point of view.
 */
public class MoveBackwardRight extends ActualMove{
	public MoveBackwardRight(PieceColor color){
		super(MoveType.BackwardRight, 
			  color);
	}
}
