package constraints;

import abstractClasses.ActualMove;
import abstractClasses.Move;
import controllers.BoardController;
import controllers.SpaceController;
import enums.PieceColor;
import interfaces.MoveConstraint;

/*
 * Verifies that a piece(typically a pawn when it tries to move
 * "ForwardTwo") will not have a piece behind it from it's player's
 * point of view after the move.
 */
public class NoPieceBehind implements MoveConstraint {
	private BoardController board;
	
	public NoPieceBehind(BoardController board){
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
		SpaceController destination = board.getNextSpace(lastMove.getRankOffset() + nextMove.getRankOffset(), 
				                          lastMove.getFileOffset() + nextMove.getFileOffset(), 
				                          lastMove.getInitialSpace());
		SpaceController behind = lastMove.getInitialSpace().getPiece().getColor() == PieceColor.White ? destination.getSpaceBackward()
				                                                    : destination.getSpaceForward();
		return !behind.hasPiece();
	}

}
