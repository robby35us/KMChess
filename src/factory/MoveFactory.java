package factory;
import definitions.*;
import moves.*;

/*
 * The MoveFactory class calls the appropriate ActualMove constructor based
 * on the given MoveType. 
 */
public class MoveFactory {

	/*
	 * Returns a concrete ActualMove object using the constructor of the 
	 * class that corresponds with the given move type. Also takes the 
	 * color of the piece to be moved.
	 */
	public static ActualMove makeMoveObject(MoveType type, PieceColor pieceColor){
		ActualMove move = null;
		switch(type){
			case EnPassantLeft : move = new MoveEnPassantLeft(pieceColor); break;
			case EnPassantRight : move = new MoveEnPassantRight(pieceColor); break;
			case Forward : move = new MoveForward(pieceColor); break;
			case ForwardLeft : move = new MoveForwardLeft(pieceColor); break;
			case ForwardRight : move = new MoveForwardRight(pieceColor); break;
			case ForwardTwo : move = new MoveForwardTwo(pieceColor); break;
			case LForwardLeft : move = new MoveLForwardLeft(pieceColor); break;
			case LForwardRight : move = new MoveLForwardRight(pieceColor); break;
			case LRightForward : move = new MoveLRightForward(pieceColor); break;
			case LRightBackward : move = new MoveLRightBackward(pieceColor); break; 
			case LBackwardRight : move = new MoveLBackwardRight(pieceColor); break;
			case LBackwardLeft : move = new MoveLBackwardLeft(pieceColor); break;
			case LLeftBackward : move = new MoveLLeftBackward(pieceColor); break;
			case LLeftForward : move = new MoveLLeftForward(pieceColor); break;
			case BackwardRight : move = new MoveBackwardRight(pieceColor); break;
			case BackwardLeft : move = new MoveBackwardLeft(pieceColor); break;
			case Right : move = new MoveRight(pieceColor); break;
			case Backward : move = new MoveBackward(pieceColor); break;
			case Left : move = new MoveLeft(pieceColor); break;
			case ReverseKingSideCastle : move = new MoveReverseKingSideCastle(pieceColor); break;
			case ReverseQueenSideCastle : move = new MoveReverseQueenSideCastle(pieceColor); break; 
			case KingSideCastle : move = new MoveKingSideCastle(pieceColor); break;
			case QueenSideCastle : move = new MoveQueenSideCastle(pieceColor); break;
			case NonStandard : move = new NonStandard(pieceColor); break;
		}
		return move;
	}
}
