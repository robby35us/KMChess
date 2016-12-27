package moves;
import definitions.*;

/* MoveBackward.java
 * This is a Move decorator that represents a piece
 * moving one space backwards, from it's player's 
 * point of view.
 */

public class MoveBackward extends ActualMove{
	public MoveBackward(PieceColor color){
		super(MoveType.Backward, 
			  color);
	}
}
