package standardMoves;
import abstractClasses.ActualMove;
import enums.MoveType;
import enums.PieceColor;

/* MoveReverseQueenSideCastle.java
 * This is a Move decorator that represents a queen-side
 * rook moving three spaces towards and passed its king.
 */
public class MoveReverseQueenSideCastle extends ActualMove{
	public MoveReverseQueenSideCastle(PieceColor color){
		super(MoveType.ReverseQueenSideCastle, 
			  color);
	}
}