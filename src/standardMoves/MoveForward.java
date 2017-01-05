package standardMoves;
import definitions.ActualMove;
import definitions.MoveType;
import definitions.PieceColor;

/* MoveForward.java
 * This is a Move decorator that represents a piece
 * moving one space forwards, from it's player's point
 * of view.
 */
public class MoveForward extends ActualMove{
	public MoveForward(PieceColor color){
		super(MoveType.Forward, 
			  color);
	}
}
