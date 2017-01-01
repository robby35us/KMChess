package standardMoves;
import abstractClasses.ActualMove;
import enums.MoveType;
import enums.PieceColor;

/* MoveLBackwardLeft.java
 * This is a Move decorator that represents a piece
 * moving in an L-shaped pattern two spaces backwards
 * and one space to the left, from it's player's point
 * of view.
 */
public class MoveLBackwardLeft extends ActualMove{
	public MoveLBackwardLeft(PieceColor color){
		super(MoveType.LBackwardLeft, 
			  color);
	}
}
