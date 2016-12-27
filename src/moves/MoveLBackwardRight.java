package moves;
import definitions.PieceColor;
import definitions.MoveType;

/* MoveLBackwardRight.java
 * This is a Move decorator that represents a piece
 * moving in an L-shaped pattern two spaces backwards
 * and one space to the right, from it's player's point
 * of view.
 */
public class MoveLBackwardRight extends ActualMove{
	public MoveLBackwardRight(PieceColor color){
		super(MoveType.LBackwardRight, 
			  color);
	}
}
