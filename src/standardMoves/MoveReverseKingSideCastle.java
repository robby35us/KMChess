package standardMoves;
import definitions.ActualMove;
import definitions.MoveType;
import definitions.PieceColor;

/* MoveReverseKingSideCastle.java
 * This is a Move decorator that represents a king-side
 * rook moving two spaces toward and passing its king.
 */
public class MoveReverseKingSideCastle extends ActualMove{
	public MoveReverseKingSideCastle(PieceColor color){
		super(MoveType.ReverseKingSideCastle, 
			  color);
	}
}