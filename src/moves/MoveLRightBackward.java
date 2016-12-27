package moves;
import definitions.PieceColor;
import definitions.MoveType;

/* MoveLRightBackward.Java
 * This is a Move decorator that represents a piece
 * moving in an L-shaped pattern two spaces to the 
 * right and one space backwards, from it's player's 
 * point of view.
 */
public class MoveLRightBackward extends ActualMove{
	public MoveLRightBackward(PieceColor color){
		super(MoveType.LRightBackward, 
			  color);
	}
}
