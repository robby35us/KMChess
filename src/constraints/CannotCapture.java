package constraints;
import abstractClasses.ActualMove;
import abstractClasses.Move;
import gameComponents.Board;
import gameComponents.Space;
import interfaces.MoveConstraint;
import pieces.Piece;

/*
 * Verifies that a the move will not result in a capture.
 */
public class CannotCapture implements MoveConstraint {
	private Board board;
	
	// This constraint requires the Board object.
	public CannotCapture(Board board){
		this.board = board;
	}
	
	@Override
	/*
	 * The meetsConstraint method looks at the lastMove object, which is often
	 * a "non-move" , such as Touch, but can be an ActualMove object, and checks 
	 * the next proposed ActualMove object to see if the proposed move meets the 
	 * conditions for this constraint. Space is the location of the piece that 
	 * is moving, or rather, the initial space of the proposed move.
	 */
	public boolean meetsConstraint(Move lastMove, ActualMove nextMove) {
		Space nextSpace = board.getNextSpace(lastMove.getRankOffset() + nextMove.getRankOffset(), 
                lastMove.getFileOffset() + nextMove.getFileOffset(), lastMove.getInitialSpace());
		Piece defender = nextSpace.getPiece();
		return defender == null;
	}

}
