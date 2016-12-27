package utility;
import game.GameState;
import game.Start;
import io.TestIO;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

import org.junit.Test;

public class MoveTest {
	
	@Test
	public void CastleTest1(){
		GameState gs = new GameState();
		TestIO tio = new TestIO(gs, "e2 e4 e7 e5 d2 d3 g7 g6 c1 h6 f8 e7 f1 e2 g8 f6 g1 f3 e8 g8 g6 g5 h6 g5 e8 g8 e1 g1 q", test.Test.SHOW_DISPLAY);
		ErrorMessage message = new ErrorMessage();
		try {
			message = Start.playGame(tio, gs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<ErrorMessage> messages = gs.getMessages();
		assertFalse(message.hasError());
		assertEquals(1, messages.size());
		assertTrue(messages.get(0).getConstraintNotMet());
	}
}
