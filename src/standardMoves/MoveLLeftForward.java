package standardMoves;
import abstractClasses.ActualMove;
import enums.MoveType;
import enums.PieceColor;

/* MoveLLeftForward.java
 * This is a Move decorator that represents a piece
 * moving in an L-shaped pattern two spaces to the
 * left and one space forward, from it's player's
 * point of view.
 */
public class MoveLLeftForward extends ActualMove{
	public MoveLLeftForward(PieceColor color){
		super(MoveType.LLeftForward, 
			  color);
	}
}
