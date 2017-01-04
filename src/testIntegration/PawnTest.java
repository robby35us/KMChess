package testIntegration;

import org.junit.Test;

import controllers.BoardController;

import java.io.IOException;

import enums.File;
import enums.PieceColor;
import enums.PieceType;
import enums.Rank;
import game.*;
import test.TestIO;
import test.TestUtility;



public class PawnTest {
	
	@Test
	public void testPawnMoveAndCapture() {
		PieceInfo[] beginningState = new PieceInfo[6];
		beginningState[0] = new PieceInfo(PieceType.Pawn, PieceColor.White, Rank.Two, File.A);
		beginningState[1] = new PieceInfo(PieceType.Pawn, PieceColor.White, Rank.Two, File.B);
		beginningState[2] = new PieceInfo(PieceType.Pawn, PieceColor.White, Rank.Two, File.H);
		beginningState[3] = new PieceInfo(PieceType.Pawn, PieceColor.Black, Rank.Seven, File.A);
		beginningState[4] = new PieceInfo(PieceType.Pawn, PieceColor.Black, Rank.Seven, File.G);
		beginningState[5] = new PieceInfo(PieceType.Pawn, PieceColor.Black, Rank.Seven, File.H);
		GameState gs = new GameState(new BoardController());
		BoardController board = TestUtility.makeBoard(beginningState, gs);

		TestIO tio = new TestIO(gs, "h2 h4 g7 g5 h4 g5 h7 h6 g5 h6 a7 a5 b2 b4 a5 b4 a2 a3 b4 a3 q", test.Test.SHOW_DISPLAY);
		try {
			Play.playGame(tio, gs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		PieceInfo[] endState = new PieceInfo[2];
		endState[0] = new PieceInfo(PieceType.Pawn, PieceColor.White, Rank.Six, File.H);
		endState[1] = new PieceInfo(PieceType.Pawn, PieceColor.Black, Rank.Three, File.A);
		TestUtility.verifyBoardState(board, endState);
	}
}
