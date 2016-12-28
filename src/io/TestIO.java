package io;
import java.util.Scanner;
import utility.ErrorMessage;
import utility.MoveInput;
import definitions.PieceColor;
import definitions.IOFramework;
import definitions.PieceType;
import definitions.Turn;
import game.GameState;
import knightmare.KMCard;

/*
 * The Test input output framework. Takes a sting in place of user input and 
 * only prints to the console if showDisplay is set to true. 
 */
public class TestIO implements IOFramework {
	private GameState gs;
	private Scanner input;
	private boolean showDisplay;
	
	public TestIO(GameState gs, String str,  boolean showDisplay){
		this.gs = gs;
		input = new Scanner(str);
		this.showDisplay = showDisplay;
	}

	/*
	 * (non-Javadoc)
	 * @see definitions.IOFramework#displayBoard()
	 */
	@Override
	public void displayBoard() {
		if(showDisplay)
			ConsoleDisplay.displayBoard(gs.getBoard());
	}
	
	/*
	 * (non-Javadoc)
	 * @see definitions.IOFramework#getMoveInput(definitions.Color, utility.ErrorMessage)
	 */
	@Override
	public MoveInput getMoveInput(PieceColor color, ErrorMessage message) {
		return InputParser.getMoveInput(color, input, gs.getBoard(), message);
	}

	/*
	 * (non-Javadoc)
	 * @see definitions.IOFramework#displayMessage(utility.ErrorMessage)
	 */
	@Override
	public void displayMessage(ErrorMessage message) {
		if(showDisplay)
			ConsoleDisplay.displayMessage(message);
	}

	/*
	 * (non-Javadoc)
	 * @see definitions.IOFramework#promotePawnTo()
	 */
	@Override
	public PieceType promotePawnTo(){
		return InputParser.getPawnPromotionType(input);
	}

	/*
	 * (non-Javadoc)
	 * @see definitions.IOFramework#runGameIntro()
	 */
	@Override
	public void runGameIntro() {
		// TODO Auto-generated method stub
		
	}

	/*
	 * (non-Javadoc)
	 * @see definitions.IOFramework#displayGetMoveInputText(definitions.Turn)
	 */
	@Override
	public void displayGetMoveInputText(Turn turn) {
		if(showDisplay)
			ConsoleDisplay.displayGetMoveInputText(turn);
	}

	@Override
	public void refreshHand() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public KMCard getExecutingCard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getAfterExecutingCard() {
		// TODO Auto-generated method stub
		
	}

}
