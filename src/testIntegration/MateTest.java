package testIntegration;
import org.junit.Test;
import static org.junit.Assert.*;

import game.GameState;
import game.Play;
import test.TestIO;
import utilityContainers.ErrorMessage;

import java.io.IOException;

public class MateTest {
	
	@Test
	public void testScholarsMate(){
		GameState gs = new GameState();
		TestIO tio = new TestIO(gs, "e2 e4 e7 e5 f1 c4 b8 c6 d1 h5 a7 a5 h5 f7 q", test.Test.SHOW_DISPLAY);
		ErrorMessage message = new ErrorMessage();
		try {
			message = Play.playGame(tio, gs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(message.getMate());
	}
}
