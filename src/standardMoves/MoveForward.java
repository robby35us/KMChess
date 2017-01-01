package standardMoves;
import abstractClasses.ActualMove;
import enums.MoveType;
import enums.PieceColor;

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
