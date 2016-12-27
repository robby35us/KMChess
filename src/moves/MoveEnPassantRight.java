package moves;

import definitions.PieceColor;
import definitions.MoveType;

/*
 * This is a Move decorator that moves a piece(pawn)
 * forward and to the right, from it's player's point 
 * of view, capturing the adjacent piece(also a pawn).
 */
public class MoveEnPassantRight extends ActualMove {
	public MoveEnPassantRight(PieceColor color){
		super(MoveType.EnPassantRight, 
			      color);
	}
}
