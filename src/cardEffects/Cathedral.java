package cardEffects;

import java.util.ArrayList;

import constraints.DisabledMove;
import definitions.CardEffect;
import definitions.MoveType;
import definitions.PieceType;
import game.GameState;
import game.GameWindow;
import game.MoveBuilder;
import gameComponents.PlayerSet;
import models.BoardModel;
import pieces.Piece;
import utilityContainers.ErrorMessage;

public class Cathedral extends CardEffect {

	@Override
	public void initiateImmediateEffect(GameState gs) {
		
		BoardModel board = gs.getBoard().getModel();
		board.setStartSpace(null);
		board.setEndSpace(null);
		do{
			board.setStartSpace(null);
			GameWindow.globalGW.infoBox("Select one of your Rooks.", getName());
			while(board.getStartSpace() == null){
				try {
					Thread.sleep(100);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}while(board.getStartSpace().getPiece() == null ||
			   board.getStartSpace().getPiece().getType() != PieceType.Rook ||
			   board.getStartSpace().getPiece().getColor() != gs.getTurn().getColor() ||
			   board.getStartSpace().getPiece().getConstraints(MoveType.NonStandard).contains(DisabledMove.disabled));
		do{
			board.setEndSpace(null);
			GameWindow.globalGW.infoBox("Now select a bishop to trade it with!", getName());
			while(board.getEndSpace() == null){
				try {
					Thread.sleep(100);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}while(board.getEndSpace().getPiece() == null ||
			   board.getEndSpace().getPiece().getType() != PieceType.Bishop ||
			   board.getEndSpace().getPiece().getColor() != gs.getTurn().getColor()||
			   board.getEndSpace().getPiece().getConstraints(MoveType.NonStandard).contains(DisabledMove.disabled));
		Piece rook =  board.getStartSpace().getPiece();
		Piece bishop = board.getEndSpace().getPiece();
		
		board.getStartSpace().changePiece(bishop, true);
		board.getEndSpace().changePiece(rook, true);
		gs.getBoard().getView().repaint();
	}

	public boolean playable(GameState gs){
		PlayerSet set = gs.getPlayerSet(gs.getTurn());
		ArrayList<Piece> rooks = set.getPieces(PieceType.Rook);
		ArrayList<Piece> bishops = set.getPieces(PieceType.Bishop);
	
		boolean hasSwapableRook = false;
		for(Piece r : rooks){
			if(MoveBuilder.buildMoveObject(r.getSpace(), MoveType.NonStandard, 1, gs, new ErrorMessage())!= null){
				hasSwapableRook = true;
				break;
			}
		}
		if(!hasSwapableRook)
			return false;
		boolean hasSwapableBishop = false;
		for(Piece b : bishops){
			if(MoveBuilder.buildMoveObject(b.getSpace(), MoveType.NonStandard, 1, gs, new ErrorMessage())!= null){
				hasSwapableBishop = true;
				break;
			}
		}
		if(!hasSwapableBishop)
			return false;
		return true;
	}
}
