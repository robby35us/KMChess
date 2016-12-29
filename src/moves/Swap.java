package moves;

import definitions.MoveType;
import definitions.PieceColor;

public class Swap extends ActualMove {
	public Swap(PieceColor pieceColor){
		super(MoveType.Swap, pieceColor);
		
	}
}
