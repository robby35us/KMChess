package test;
import moves.Move;
import testIntegration.PieceInfo;

import org.junit.Assert;

import components.Board;
import components.Piece;
import components.Space;
import definitions.PieceColor;
import definitions.File;
import definitions.Rank;
import factory.PieceFactory;
import game.GameState;

public class TestUtility {
	
	public static Board makeBoard(PieceInfo[] info, GameState gs){
		Board board = gs.getBoard();
		PieceFactory factory = new PieceFactory(board, gs);
		for(int i = 0; i < info.length; i++){
			Space space = board.getSpace(info[i].getRank(), info[i].getFile());
			if(space.getPiece() == null) // verify space is empty
				space.changePiece(factory.makePiece(info[i].getType(), info[i].getColor()), true);
			else
				return null;
		}
		return board;
	}
	
	public static void verifyBoardState(Board board, PieceInfo[] state){
		PieceInfo[][] expectedState = new PieceInfo[8][8];
		for(int i = 0; i < state.length; i++){
			expectedState[state[i].getRank().ordinal()][state[i].getFile().ordinal()] = state[i];
		}
		Space head = board.getSpace(Rank.One, File.A);
		Space current = head;
		for(int rankValue = 0; rankValue < 8; rankValue++){
			for(int fileValue = 0; fileValue < 8; fileValue++){
				if(current.getPiece() == null){
					Assert.assertNull(expectedState[rankValue][fileValue]);
				}
				else{
					Piece piece = current.getPiece();
					PieceInfo expectedPieceInfo = expectedState[rankValue][fileValue];
					Assert.assertNotNull(expectedPieceInfo);
					Assert.assertEquals(expectedPieceInfo.getType().ordinal(), piece.getType().ordinal());
					Assert.assertEquals(expectedPieceInfo.getColor().ordinal(), piece.getColor().ordinal());
				}
				current = current.getSpaceRight();
			}
			
			head = head.getSpaceForward();
			current = head;
		}
	}

	public static void verifyMove(Move move, int rankOffset, int fileOffset, PieceColor color) {
		Assert.assertEquals(color == PieceColor.White ? rankOffset : -rankOffset, move.getRankOffset());
		Assert.assertEquals(color == PieceColor.White ? fileOffset : -fileOffset, move.getFileOffset());
	}
	
}
