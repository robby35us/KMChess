package standardMoves;
import definitions.ActualMove;
import definitions.MoveType;
import definitions.PieceColor;

/* MoveForwardTwo.java
 * This is a Move decorator that represents a piece
 * moving exactly two space forwards, from it's player's 
 * point of view.
 */
public class MoveForwardTwo extends ActualMove{
	public MoveForwardTwo(PieceColor color){
		super(MoveType.ForwardTwo, 
			  color);
	}
}