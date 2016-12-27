package moves;
import definitions.PieceColor;
import definitions.MoveType;

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
