package standardMoves;
import abstractClasses.ActualMove;
import enums.MoveType;
import enums.PieceColor;

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