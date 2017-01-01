package standardMoves;
import abstractClasses.ActualMove;
import enums.MoveType;
import enums.PieceColor;

/* MoveLForwardLeft.java
 * This is a Move decorator that represents a piece
 * moving in an L-shaped pattern two spaces forwards
 * and one space to the left, from it's player's
 * point of view.
 */
public class MoveLForwardLeft extends ActualMove{
	public MoveLForwardLeft(PieceColor color){
		super(MoveType.LForwardLeft, 
			  color);
	}
}
