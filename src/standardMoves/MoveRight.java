package standardMoves;
import abstractClasses.ActualMove;
import enums.MoveType;
import enums.PieceColor;

/* MoveRight.java
 * This is a Move decorator that represents a piece
 * moving one space to the right, from it's player's
 * point of view.
 */
public class MoveRight extends ActualMove{
	public MoveRight(PieceColor color){
		super(MoveType.Right, 
			  color);
	}
}