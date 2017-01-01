package interfaces;
import cards.KMCard;
import enums.PieceColor;
import enums.PieceType;
import enums.Turn;
import utilityContainers.ErrorMessage;
import utilityContainers.MoveInput;

/*
 * This interface is intended allow the encapsulation
 * of the and IO API from the chess game play logic. 
 * Currently, however, there are functions that go 
 * beyond this intention and should eventually be
 * removed from this interface.
 */
public interface IOFramework {
	
	/*
	 * Refresh the board display.
	 */
	public void displayBoard();
	
	/*
	 * Return a MoveInput object that represents the next move
	 * as provided by the underlying input mechanism.
	 */
	public MoveInput getMoveInput(PieceColor color, ErrorMessage message);
	
	/*
	 * Run game setup using the appropriate display.
	 */
	public void runGameIntro();

	
	/*
	 * Display the provided ErrorMessage object via the appropriate display.
	 */
	public void displayMessage(ErrorMessage message);
	
	/*
	 * Called when a pawn needs promoting.  Returns selected
	 *  PieceType that the pawn should be promoted to.
	 */
	public PieceType promotePawnTo();

	/*
	 * Displays the prompt to get the next move input via
	 * the appropriate display method.
	 */
	public void displayGetMoveInputText(Turn turn);

	public void refreshHand();

	public KMCard getExecutingCard();

	public void getAfterExecutingCard();
}
