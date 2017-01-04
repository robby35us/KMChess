package consoleIO;

import control.BoardControl;
import control.SpaceController;
import enums.*;
import pieces.Piece;
import utilityContainers.ErrorMessage;

/*
 * This class holds helper functions for output to the console.
 */
public class ConsoleDisplay{
	
	/*
	 * Displays a pretty print version of a chess board.
	 */
	public static void displayBoard(BoardControl board){
		SpaceController head = board.getSpace(Rank.Eight, File.A);
		SpaceController current = head;
		System.out.println(" Rank    ----   ----   ----   ----   ----   ----   ----   ----");
		for(int i = 8; i >= 1; i--){
			if(current.getColor() == SpaceColor.White)
				System.out.printf("  %d    |  ", i);
			else {
				System.out.printf("  %d    | |", i);
			}
			for(int j = 1; j <= 8; j++){
				printPieceCode(current);
				if(current.getColor() == SpaceColor.White)
					if(current.getFile() == File.H)
						System.out.print("  |  ");
					else
						System.out.print("  | |");
				else
						System.out.print("| |  ");
				current = current.getSpaceRight();
			}
			System.out.println();
			System.out.println("         ----   ----   ----   ----   ----   ----   ----   ----");
			
			head = head.getSpaceBackward();
			current = head;
		}
		System.out.println("  File   a      b      c      d      e      f      g      h");
	}
	
	/*
	 * Displays the 2-character code that identifies the color and piece type in the display.
	 */
	private static void printPieceCode(SpaceController current) {
		Piece piece = current.getPiece();
		if(piece == null)
			System.out.print("  ");
		else{
			String result = "";
			result += piece.getColor() == PieceColor.White ? "W" : "B";
			switch(piece.getType()){
				case Pawn : result += "P"; break;
				case Knight : result += "N"; break;
				case Bishop : result += "B"; break;
				case Rook : result += "R"; break;
				case Queen : result += "Q"; break;
				case King : result += "K"; break;
			}
			System.out.print(result);
		}
	}

	/*
	 * The text to display when move input is required.
	 */
	public static void displayGetMoveInputText(Turn turn) {
		System.out.println("Player " + (turn.ordinal() + 1) + ", enter next move (ex. e2 e4):");
		
	}	
	
	/*
	 * The text to display when an invalid movement text is entered. 
	 */
	public static void invalidMoveText(){
		System.out.println("The entered move is not valid.");
		System.out.println("Please try again.");
		
	}

	/*
	 * Translates the ErrorMessage flags to console output text. 
	 */
	public static void displayMessage(ErrorMessage message) {
		if(message.getConstraintNotMet()){
			System.out.println("This piece cannot move there at this time.");
		}
		else if(message.getIllegalPattern()){
			System.out.println("This piece cannot move in that pattern.");
		}
		else if(message.getWrongColorMoving()){
			System.out.println("This piece is the wrong color.");
		}
		else if(message.getWrongColorCaptured()){
			System.out.println("This piece cannot capture a piece of the same color.");
		}
		if(message.hasError())
			invalidMoveText();
	}
}
