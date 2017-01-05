package testIntegration;
import test.TestUtility;

import org.junit.Test;

import definitions.Move;
import definitions.MoveType;
import definitions.PieceColor;
import standardMoves.MoveFactory;


public class MoveDecoratorTest {
	
	@Test
	public void TestMoveForward(){
		Move move;
		PieceColor[] colors = PieceColor.values();
		for(int i = 0; i < colors.length; i++){
			move = MoveFactory.makeMoveObject(MoveType.Forward,colors[i]);
			TestUtility.verifyMove(move, 1,0,colors[i]);
		}
	}
	
	@Test
	public void TestMoveForwardLeft(){
		Move move;
		PieceColor[] colors = PieceColor.values();
		for(int i = 0; i <colors.length; i++){
			move = MoveFactory.makeMoveObject(MoveType.ForwardLeft,colors[i]);
			TestUtility.verifyMove(move, 1, -1,colors[i]);
		}
	}
	
	@Test
	public void TestMoveForwardRight(){

		Move move;
		PieceColor[]colors =PieceColor.values();
		for(int i = 0; i <colors.length; i++){
			move = MoveFactory.makeMoveObject(MoveType.ForwardRight,colors[i]);
			TestUtility.verifyMove(move, 1, 1,colors[i]);
		}
	}
	
	@Test
	public void TestMoveForwardTwo(){
		Move move;
		PieceColor[]colors =PieceColor.values();
		for(int i = 0; i <colors.length; i++){
			move = MoveFactory.makeMoveObject(MoveType.ForwardTwo,colors[i]);
			TestUtility.verifyMove(move, 2, 0,colors[i]);
		}
	}
	
	@Test
	public void TestMoveLForwardLeft(){
		Move move;
		PieceColor[]colors =PieceColor.values();
		for(int i = 0; i <colors.length; i++){
			move = MoveFactory.makeMoveObject(MoveType.LForwardLeft,colors[i]);
			TestUtility.verifyMove(move, 2, -1,colors[i]);
		}
	}
	
	@Test
	public void TestMoveLForwardRight(){
		Move move;
		PieceColor[]colors =PieceColor.values();
		for(int i = 0; i <colors.length; i++){
			move = MoveFactory.makeMoveObject(MoveType.LForwardRight,colors[i]);
			TestUtility.verifyMove(move, 2, 1,colors[i]);
		}
	}
	
	@Test
	public void TestMoveLRightForward(){
		Move move;
		PieceColor[]colors =PieceColor.values();
		for(int i = 0; i <colors.length; i++){
			move = MoveFactory.makeMoveObject(MoveType.LRightForward,colors[i]);
			TestUtility.verifyMove(move, 1, 2,colors[i]);
		}
	}
	
	@Test
	public void TestMoveLRightBackward(){
		Move move;
		PieceColor[]colors =PieceColor.values();
		for(int i = 0; i <colors.length; i++){
			move = MoveFactory.makeMoveObject(MoveType.LRightBackward,colors[i]);
			TestUtility.verifyMove(move, -1, 2,colors[i]);
		}
	}
	
	@Test
	public void TestMoveLBackwardRight(){
		Move move;
		PieceColor[]colors =PieceColor.values();
		for(int i = 0; i <colors.length; i++){
			move = MoveFactory.makeMoveObject(MoveType.LBackwardRight,colors[i]);
			TestUtility.verifyMove(move, -2, 1,colors[i]);
		}
	}
	
	@Test
	public void TestMoveLBackwardLeft(){
		Move move;
		PieceColor[]colors =PieceColor.values();
		for(int i = 0; i <colors.length; i++){
			move = MoveFactory.makeMoveObject(MoveType.LBackwardLeft,colors[i]);
			TestUtility.verifyMove(move, -2, -1,colors[i]);
		}
	}
	
	@Test
	public void TestMoveLLeftBackward(){
		Move move;
		PieceColor[]colors =PieceColor.values();
		for(int i = 0; i <colors.length; i++){
			move = MoveFactory.makeMoveObject(MoveType.LLeftBackward,colors[i]);
			TestUtility.verifyMove(move, -1,-2,colors[i]);
		}
	}
	
	@Test
	public void TestMoveLLeftForward(){
		Move move;
		PieceColor[]colors =PieceColor.values();
		for(int i = 0; i <colors.length; i++){
			move = MoveFactory.makeMoveObject(MoveType.LLeftForward,colors[i]);
			TestUtility.verifyMove(move, 1, -2,colors[i]);
		}
	}
	
	@Test
	public void TestMoveBackwardRight(){
		Move move;
		PieceColor[]colors =PieceColor.values();
		for(int i = 0; i <colors.length; i++){
			move = MoveFactory.makeMoveObject(MoveType.BackwardRight,colors[i]);
			TestUtility.verifyMove(move, -1, 1,colors[i]);
		}
	} 
	
	@Test
	public void TestMoveBackwardLeft(){
		Move move;
		PieceColor[]colors =PieceColor.values();
		for(int i = 0; i <colors.length; i++){
			move = MoveFactory.makeMoveObject(MoveType.BackwardLeft,colors[i]);
			TestUtility.verifyMove(move, -1, -1,colors[i]);
		}
	}
	
	@Test
	public void TestMoveRight(){
		Move move;
		PieceColor[]colors =PieceColor.values();
		for(int i = 0; i <colors.length; i++){
			move = MoveFactory.makeMoveObject(MoveType.Right,colors[i]);
			TestUtility.verifyMove(move, 0, 1,colors[i]);
		}
	}
	
	@Test
	public void TestMoveBackward(){
		Move move;
		PieceColor[]colors =PieceColor.values();
		for(int i = 0; i <colors.length; i++){
			move = MoveFactory.makeMoveObject(MoveType.Backward,colors[i]);
			TestUtility.verifyMove(move, -1,0,colors[i]);
		}
	}
	
	@Test
	public void TestMoveLeft(){
		Move move;
		PieceColor[]colors =PieceColor.values();
		for(int i = 0; i <colors.length; i++){
			move = MoveFactory.makeMoveObject(MoveType.Left,colors[i]);
			TestUtility.verifyMove(move, 0, -1,colors[i]);
		}
	}
	
	@Test
	public void TestMoveReverseKingSideCastle(){
		Move move;
		move = MoveFactory.makeMoveObject(MoveType.ReverseKingSideCastle,PieceColor.White);
		TestUtility.verifyMove(move, 0, -2,PieceColor.White);
		move = MoveFactory.makeMoveObject(MoveType.ReverseKingSideCastle,PieceColor.Black);
		TestUtility.verifyMove(move, 0, 2,PieceColor.Black);
	}
	
	@Test
	public void TestMoveReverseQueenSideCastle(){
		Move move;
		move = MoveFactory.makeMoveObject(MoveType.ReverseQueenSideCastle,PieceColor.White);
		TestUtility.verifyMove(move, 0, 3,PieceColor.White);
		move = MoveFactory.makeMoveObject(MoveType.ReverseQueenSideCastle,PieceColor.Black);
		TestUtility.verifyMove(move, 0, -3,PieceColor.Black);
	}
	
	@Test
	public void TestMoveKingSideCastle(){
		Move move;
		move = MoveFactory.makeMoveObject(MoveType.KingSideCastle,PieceColor.White);
		TestUtility.verifyMove(move, 0, 2,PieceColor.White);
		move = MoveFactory.makeMoveObject(MoveType.KingSideCastle,PieceColor.Black);
		TestUtility.verifyMove(move, 0, -2, PieceColor.Black);
	}
	
	@Test
	public void TestMoveQueenSideCastle(){
		Move move;
		move = MoveFactory.makeMoveObject(MoveType.QueenSideCastle,PieceColor.White);
		TestUtility.verifyMove(move, 0, -2,PieceColor.White);
		move = MoveFactory.makeMoveObject(MoveType.QueenSideCastle,PieceColor.Black);
		TestUtility.verifyMove(move, 0, 2,PieceColor.Black);
	}
}
