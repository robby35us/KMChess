package game;

import java.io.IOException;

import cardEffects.ContEffect;
import components.Board;
import moves.ActualMove;
import utility.ErrorMessage;
import utility.MoveInput;
import definitions.PieceColor;
import definitions.IOFramework;
import definitions.PieceType;
import definitions.Timing;
import definitions.Turn;
import factory.CardEffectFactory;
import io.ConsoleIO;
import knightmare.KMCard;


/*
 * Holds the core logic for playing chess between two players, as well as chess game setup
 */
public class Start {
	
	/*
	 * Run the main chess game using the console. Will change to GUI at a 
	 * later date.
	 */
	public static void main(String[] args) throws IOException{
		GameState gs = new GameState();
		
		ErrorMessage message = Start.playGame(new ConsoleIO(gs), gs);
		System.out.println(message);
	}
	
	
	/*
	 * The primary logic for running a chess game. Requires an Input/Output Framework and
	 * a GameState object to track the board and player statuses throughout the game. Can 
	 * through uncaught IOException.
	 */
	public static ErrorMessage playGame(IOFramework fw, GameState gs) throws IOException{
		Board board = gs.getBoard(); 
		ActualMove move = null;
		MoveInput moveInput;
		board.setTurn(Turn.Player1);
		ErrorMessage message = new ErrorMessage();
		do{ // until the game ends

			fw.displayBoard();
			//fw.refreshHand();
			// record and reset the message object
			if(message.hasError())
				gs.getMessages().add(message);
			message = new ErrorMessage();
			//System.out.println("Displaying get move input text");
			
			KMCard.CurrentTiming = Timing.Before;
			// get the input for the next move
			fw.displayGetMoveInputText(board.getTurn());
			//System.out.println("Get move input text displayed");
			moveInput = fw.getMoveInput(PieceColor.values()[board.getTurn().ordinal()], message);
			KMCard execCard = fw.getExecutingCard();
			if(execCard != null && (execCard.getCInfo().getTiming() == Timing.Before ||
			   execCard.getCInfo().getTiming() == Timing.BeforeOrAfter)){
				gs.getCardArea().completeCardExecution(gs);
				gs.updateContEffects();
				moveInput = fw.getMoveInput(PieceColor.values()[board.getTurn().ordinal()], message);
			}
			// build the given move, if possible 
			if(execCard == null && moveInput != null)
				move = MoveBuilder.buildMoveObject(moveInput.getInit(), moveInput.getDest(), gs, message);
			else {
				if(message.hasError()){ // signals for new input
					move = null;
				}
				else if (execCard != null && execCard.getCInfo().getTiming() == Timing.Instead){
					gs.getCardArea().completeCardExecution(gs);
					gs.updateContEffects();
				}
			}
			
			// checks the universal constraints
			if(move != null && gs.meetsUniversalConstraints(move, board.getTurn(), message)){
				
				// actually move the piece
				gs.makeMove(move, board.getTurn(), message);
				
				// check for pawnPromotion
				if(message.getPromotePawn()){
					PieceType pawnPromotionType = fw.promotePawnTo();
					if(gs.promotePawn(move.getDestinationSpace().getPiece(), pawnPromotionType)){
						message = new ErrorMessage(); // clear error message
					}
					else
						message.setUnableToPromotePawn();
				}
				
				// if move was made successfully
				if(!message.hasError()){
					
					move.getDestinationSpace().getPiece().setBeenMoved(true);
					KMCard.CurrentTiming = Timing.After;
					if(execCard == null){
						gs.getCardArea().activateSkipButton();
						fw.getAfterExecutingCard();
						execCard = fw.getExecutingCard();
						gs.getCardArea().resetNoCardButton();
					}
					// set to next player
					if(execCard != null && execCard.getCInfo().getTiming() == Timing.After){
						gs.getCardArea().completeCardExecution(gs);
					}
					gs.updateContEffects();
					if(board.getTurn() == Turn.Player1)
						board.setTurn(Turn.Player2);
					else
						board.setTurn(Turn.Player1);
				
					
					if(gs.checkForMate(board.getTurn(), message).hasError()){
						// if checkmate, exits the program, with current player in check
						break;
					}
				}
			}
			
			// display message, if there is an error reported
			if(message.hasError())
				fw.displayMessage(message);
		}while(true); // cycles until the game ends or is ended
		
		
		return message;
	}

}
