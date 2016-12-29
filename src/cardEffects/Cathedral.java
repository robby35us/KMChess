package cardEffects;

import components.Board;
import components.Piece;
import constraints.DisabledMove;
import definitions.MoveType;
import definitions.PieceType;
import game.GameState;

public class Cathedral extends CardEffect {

	@Override
	public void initiateImmediateEffect(GameState gs) {
		Board board = gs.getBoard();
		board.setStartSpace(null);
		board.setEndSpace(null);
		do{
			board.setStartSpace(null);
			board.infoBox("Select one of your Rooks.", cardName);
			while(board.getStartSpace() == null){
				try {
					Thread.sleep(100);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}while(board.getStartSpace().getPiece() == null ||
			   board.getStartSpace().getPiece().getType() != PieceType.Rook ||
			   board.getStartSpace().getPiece().getColor() != gs.getBoard().getTurn().getColor() ||
			   board.getStartSpace().getPiece().getConstraints(MoveType.NonStandard).contains(DisabledMove.disabled));
		do{
			board.setEndSpace(null);
			board.infoBox("Now select a bishop to trade it with!", cardName);
			while(board.getEndSpace() == null){
				try {
					Thread.sleep(100);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}while(board.getEndSpace().getPiece() == null ||
			   board.getEndSpace().getPiece().getType() != PieceType.Bishop ||
			   board.getEndSpace().getPiece().getColor() != gs.getBoard().getTurn().getColor()||
			   board.getEndSpace().getPiece().getConstraints(MoveType.NonStandard).contains(DisabledMove.disabled));
		Piece rook =  board.getStartSpace().getPiece();
		Piece bishop = board.getEndSpace().getPiece();
		
		board.getStartSpace().changePiece(bishop, true);
		board.getEndSpace().changePiece(rook, true);
		board.repaint();
	}

}
