package standardMoves;
import definitions.ActualMove;
import definitions.MoveType;
import definitions.PieceColor;

/* MoveKingSideCastle.java
 * This is a Move decorator that represents a piece
 * moving two spaces toward its king side rook.
 */
public class MoveKingSideCastle extends ActualMove{
	public MoveKingSideCastle(PieceColor color){
		super(MoveType.KingSideCastle, 
		      color);
	}
}
