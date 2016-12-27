package definitions;
import components.Space;

/*
 * A PieceObserver alerts the king on the opposing side if
 * it can attack that king.
 */
public interface PieceObserver {
	
	/*
	 * Called by the opposing KingSubject when it want's to 
	 * know if this PieceObserver can attack the given destination, 
	 * which would mean that the king is or would be in check.
	 */
	public boolean updateOpposingPiece(Space destination);
}
