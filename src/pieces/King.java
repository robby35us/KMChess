package pieces;
import java.util.ArrayList;

import controllers.SpaceController;
import definitions.*;

/*
 * A king represents a King piece and, as such, extends the Piece class. 
 * This class only holds the implementations of the KingSubject and 
 * the KingObserver interfaces. These interfaces allow communication
 * from the pieces on one side to it's own king (through updateKing())
 * who in turn communicates with each piece on the opposing side
 * (through notifyOpposingPieceObservers()) that they should return 
 * whether or not they can check this king. Of course, each opposingPiece
 * must be registered and unregistered as necessary throughout the course
 * of the game. 
 */
public class King extends Piece implements KingSubject, KingObserver {

	private ArrayList<PieceObserver> opposingObservers;
	
	// Note: there could possibly be multiple types that count as kings
	public King(PieceType type, PieceType display, PieceColor color) {
		super(type, display, color);
		opposingObservers = new ArrayList<PieceObserver>();
	}

	@Override
	/*
	 * The only method in the KingObserver interface, this method is 
	 * called by each piece on this king's side whenever they are
	 * about to make a move. updateKing returns true if the move
	 * is "safe" and the king is not in check. Otherwise, the move
	 * is illegal and updateKing returns false.
	 */
	public boolean updateKing() {
		return notifyOpposingPieceObservers(this.space);
	}

	@Override
	/*
	 * Each piece on the other side needs to register with 
	 * with this king in order to guarantee that a player
	 * will not move into check. This method should be 
	 * called on this king every time a piece on the other 
	 * side gets added to the other player.
	 */
	public void registerOpposingPieceObserver(PieceObserver o) {
		opposingObservers.add(o);
	}

	@Override
	/*
	 * Each piece on the other side should be unregistered with
	 * with this king as it is removed from the game in order
	 * to guarantee correct functionality of the king class.
	 */
	public void removeOpposingPieceObserver(PieceObserver o) {
		int index = opposingObservers.indexOf(o);
		if(index >= 0)
			opposingObservers.remove(index);
	}

	@Override
	/*
	 * Notifies each piece on the opposing team to see if 
	 * this king has been placed in check. Returns true if
	 * no piece can attack the king, and false if the king is
	 * indeed in check.
	 */
	public boolean notifyOpposingPieceObservers(SpaceController dest) {
		
		// THIS CODE WAS CAUSING BUGS WITH THE PAWNS. 
		//I took it out removing the king and they went away. 
		//Keeping in case I find new related bugs.
		// not sure what I was trying to accomplish by removing the king in the first place
		
		// remove the king from the board
		//space.changePiece(null, false);
		
		boolean validMove = true;
		for(PieceObserver o : opposingObservers){
			if(!o.updateOpposingPiece(dest)){
				validMove = false;
				break; // we know the king is in check
			}
		}
		
		// see above!
		// put the king back
		//space.changePiece(this, false);
		return validMove;
	}
}
