package testConstraints;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import constraints.CanCaptureEnPassantLeft;
import constraints.CanCaptureEnPassantRight;
import constraints.CanKingCastle;

@RunWith(Suite.class)
@SuiteClasses({CanCaptureEnPassantLeftTest.class, 
			   CanCaptureEnPassantRightTest.class,
			   CanKingCastleTest.class, 
			   CannotPassPieceTest.class,
			   CanQueenCastleTest.class,
			   DoubleMoveTest.class,
			   MustBeenMovedTest.class,
			   MustCaptureTest.class,
			   MustMoveAlikeTest.class,
			   NoPieceBehindTest.class,
			   NotBeenMovedTest.class,
			   SingleMoveTest.class})
public class TestSuiteConstraints {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
