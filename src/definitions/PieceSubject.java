package definitions;

/*
 * A PieceSubject notifies the KingObserver(or KingObservers if there
 * is more than one per Knightmare chess rules) when ever it makes a move.
 * This mechanism is to notify the king that he should see if he is
 * in check.
 */
public interface PieceSubject {
	
	/*
	 * Should be called for each KingObserver of the same color 
	 * at the beginning of the game, or as it enters play.
	 */
	public void registerKingObserver(KingObserver o);
	
	/*
	 * Should be called on a KingObserver that leaves play.
	 */
	public void removeKingObserver(KingObserver o);
	
	/*
	 * Notify each KingObserver that this piece has moved, signaling 
	 * that it should see if it is in check.
	 */
	public boolean notifyKingObservers();
}
