package moves;
import definitions.PieceColor;
import definitions.MoveType;

/* MoveLeft.java
 * This is a Move decorator that represents a piece
 * moving one space to the left, from it's player's 
 * point of view.
 */
public class MoveLeft extends ActualMove{
	public MoveLeft(PieceColor color){
		super(MoveType.Left, 
			  color);
	}
}
