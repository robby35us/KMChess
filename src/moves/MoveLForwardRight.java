package moves;
import definitions.PieceColor;
import definitions.MoveType;

/* MoveLForwardRight.java
 * This is a Move decorator that represents a piece
 * moving in an L-shaped pattern two spaces forwards
 * and one space to the right, from it's player's
 * point of view.
 */
public class MoveLForwardRight extends ActualMove{
	public MoveLForwardRight(PieceColor color){
		super(MoveType.LForwardRight, 
			  color);
	}
}
