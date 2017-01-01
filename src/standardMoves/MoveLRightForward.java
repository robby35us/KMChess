package standardMoves;
import abstractClasses.ActualMove;
import enums.MoveType;
import enums.PieceColor;

/* MoveLRightForward.Java
 * This is a Move decorator that represents a piece
 * moving in an L-shaped pattern two spaces to the
 * right and one space forward, from it's player's
 * point of view.
 */
public class MoveLRightForward extends ActualMove{
	public MoveLRightForward(PieceColor color){
		super(MoveType.LRightForward, 
			  color);
	}
}
