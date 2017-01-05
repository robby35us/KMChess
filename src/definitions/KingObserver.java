package definitions;

/*
 * A KingObserver registers with a PieceSubject. The purpose of 
 * this mechanism is to allow a king to know when a piece of it's
 * own color is testing whether it can move.
 */
public interface KingObserver {
	
	/*
	 * The only method in the KingObserver interface, this method is 
	 * called by each piece on this king's side whenever it is testing
	 * whether it can move legally. updateKing returns true if the move
	 * is "safe" and the king is not in check. Otherwise, the move
	 * is illegal and updateKing returns false.
	 */
	public boolean updateKing();
}
