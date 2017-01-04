package consoleIO;

import java.util.Scanner;

import controllers.BoardController;
import controllers.SpaceController;
import enums.File;
import enums.PieceColor;
import enums.PieceType;
import enums.Rank;
import utilityContainers.ErrorMessage;
import utilityContainers.MoveInput;

/*
 * Contains the logic to parse string or console input into objects in the game.
 */
public class InputParser {
	/*
	 * Returns a MoveInput object, which returns the initial and destination spaces of the 
	 * inputed move on the board. Returns null if either of the entered codes is invalid, or
	 * if the exit condition is inputed as the fist result.
	 */
	public static MoveInput getMoveInput(PieceColor color, Scanner in, BoardController board, ErrorMessage message) {
		String input = null;
		
		// get the initial Space
		input = in.next();
		SpaceController init = getSpace(input, board);
		if(init == null){
			if(!exitCondition(input)){
				message.setInvalidInput();
			}
			return null;
		}
		
		// get the destination Space
		input = in.next();
		SpaceController dest = getSpace(input, board);
		if(dest == null){
			message.setInvalidInput();
			return null;
		}
		
		return new MoveInput(init, dest);
	}
	
	/*
	 * Translates a single-character code into the PieceType
	 * for the pawn to turn into. Returns null if an invalid 
	 * code is entered.
	 */
	public static PieceType getPawnPromotionType(Scanner in){
		String input = in.next();
		char selection = input.charAt(0);
		PieceType replacementType;
		switch(selection){
			case 'Q':
			case 'q':
				replacementType = PieceType.Queen;
				break;
			case 'R':
			case 'r':
				replacementType = PieceType.Rook;
				break;
			case 'K' :
			case 'k' :
				replacementType = PieceType.Knight;
				break;
			case 'B' :
			case 'b' :
				replacementType = PieceType.Bishop;
				break;
			default :
				return null;
		}
		return replacementType;
	}


	/*
	 * A helper method to translate Space input into a Space object.
	 * Returns null if the input is invalid.
	 */
	private static SpaceController getSpace(String input, BoardController board) {
		if(input == null || input.length() != 2)
			return null;
		char fileInput = input.toLowerCase().charAt(0);
		char rankInput = input.charAt(1);
		if(fileInput >= 'a' && fileInput <= 'h' &&
		   rankInput >= '1' && rankInput <= '8')
			return board.getSpace(Rank.values()[rankInput - '1'], 
					              File.values()[fileInput - 'a']);
		else
			return null;
	}

	/*
	 * Checks the input for the code to quit. Returns true if either 
	 * 'q' or 'Q' is detected as the first char in the input string.
	 */
	private static boolean exitCondition(String input) {
		 return input.charAt(0) == 'q' || input.charAt(0) == 'Q';
	}
}
