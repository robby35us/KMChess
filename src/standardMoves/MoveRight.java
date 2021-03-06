package standardMoves;
import definitions.ActualMove;
import definitions.MoveType;
import definitions.PieceColor;

/* MoveRight.java
 * This is a Move decorator that represents a piece
 * moving one space to the right, from it's player's
 * point of view.
 */
public class MoveRight extends ActualMove{
	public MoveRight(PieceColor color){
		super(MoveType.Right, 
			  color);
	}
}
