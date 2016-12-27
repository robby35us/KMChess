package moves;

import definitions.PieceColor;
import definitions.MoveType;

/*
 * This is a Move decorator that moves a piece(pawn)
 * forward and to the left, from it's player's point 
 * of view, capturing the adjacent piece(also a pawn).
 */
public class MoveEnPassantLeft extends ActualMove {
	public MoveEnPassantLeft(PieceColor color){
		super(MoveType.EnPassantLeft, 
			      color);
	}
}
