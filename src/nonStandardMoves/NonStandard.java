package nonStandardMoves;

import abstractClasses.ActualMove;
import enums.MoveType;
import enums.PieceColor;

public class NonStandard extends ActualMove {
	public NonStandard(PieceColor pieceColor){
		super(MoveType.NonStandard, pieceColor);
		
	}
}
