package definitions;
import components.Space;


/*
 * A KingSubject notifies all opposing pieces when it once to 
 * see if it is in check. Each piece on the opposing side is
 * registered with the KingSubject, and returns a boolean to
 * the KingSubject if it can check it.
 */
public interface KingSubject {
	/*
	 * Should be called by for each piece on the opposing side
	 * at the beginning of the game, or as it enters play.
	 */
	public void registerOpposingPieceObserver(PieceObserver o);
	
	/*
	 * Should be called by each piece on the opposing side as
	 * it enters play.
	 */
	public void removeOpposingPieceObserver(PieceObserver o);
	
	/*
	 * Called by the KingSubject when it wants to know if it has
	 * been placed in check, provided the king were to be placed
	 * at the given destination.
	 */
	public boolean notifyOpposingPieceObservers(Space dest);
}
