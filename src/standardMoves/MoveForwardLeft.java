package standardMoves;
import definitions.ActualMove;
import definitions.MoveType;
import definitions.PieceColor;

/* MoveForwardLeft.java
 * This is a Move decorator that represents a piece
 * moving one space diagonally both forward and to 
 * the left, from it's player's point of view.
 */
public class MoveForwardLeft extends ActualMove{
	public MoveForwardLeft(PieceColor color){
		super(MoveType.ForwardLeft, 
		      color);
	}
}
