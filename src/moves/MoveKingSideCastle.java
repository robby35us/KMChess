package moves;
import definitions.PieceColor;
import definitions.MoveType;

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
