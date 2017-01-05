package consoleIO;
import java.util.Scanner;

import cards.CardController;
import enums.*;
import game.GameState;
import interfaces.IOFramework;
import utilityContainers.*;

/*
 * The IOFramework concrete class to use when playing chess when using the 
 * console for the input and output.
 */
public class ConsoleIO implements IOFramework {
	private GameState gs;
	private Scanner input = new Scanner(System.in);
	
	
	public ConsoleIO(GameState gs){
		this.gs = gs;
	}

	/*
	 * (non-Javadoc)
	 * @see definitions.IOFramework#displayBoard()
	 */
	@Override
	public void displayBoard() {
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
		ConsoleDisplay.displayMessage(message);
	}

	/*
	 * (non-Javadoc)
	 * @see definitions.IOFramework#promotePawnTo()
	 */
	@Override
	public PieceType promotePawnTo() {
		try{
			while(true){
				System.out.println("Choose type to promote pawn to - Q, R, B, or K:");
				PieceType promotionType = InputParser.getPawnPromotionType(input);	
				if(promotionType != null)
					return promotionType;
				
				else
					System.out.println("Please try again.");
			}
		}	
		catch(Exception e){
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see definitions.IOFramework#runGameIntro()
	 */
	@Override
	public void runGameIntro() {
		// TODO /* leaving blank for now */
		
	}

	/*
	 * (non-Javadoc)
	 * @see definitions.IOFramework#displayGetMoveInputText(definitions.Turn)
	 */
	@Override
	public void displayGetMoveInputText(Turn turn) {
		ConsoleDisplay.displayGetMoveInputText(turn);
	}

	@Override
	public void refreshHand() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CardController getExecutingCard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getAfterExecutingCard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoBox(String message, String title) {
		// TODO Auto-generated method stub
		
	}
}
