package standardMoves;
import definitions.ActualMove;
import definitions.MoveType;
import definitions.PieceColor;

/* MoveQueenSideCastle.java
 * This is a Move decorator that represents a piece
 * moving two spaces toward its queen-side rook.
 */
public class MoveQueenSideCastle extends ActualMove{
	public MoveQueenSideCastle(PieceColor color){
		super(MoveType.QueenSideCastle, 
		      color);
	}
}
