package factory;
import components.*;
import constraints.*;
import definitions.*;
import game.GameState;

/*
 * Used to create a piece of a given PieceType. Unlike the MoveFactory, 
 * there must be an instance of the class to use this class. This
 * is because of the need of a copy of the Board and Operations objects 
 * for this game for some of the constraints.
 */
public class PieceFactory {
	
	// Constraints for this PieceFactory
	public static SingleMove singleMove;
	public static MustCapture mustCapture;
	public static MustMoveAlike mustMoveAlike;
	//private MustBeenMoved mustBeenMoved;
	public static NotBeenMoved notBeenMoved;
	//private DoubleMove doubleMove;
	public static CannotCapture cannotCapture;
	public static CanKingCastle canKingCastle;
	public static CanQueenCastle canQueenCasltle;
	public static CannotPassPiece cannotPassPiece;
	public static NoPieceBehind noPieceBehind;
	public static CanCaptureEnPassantLeft canCaptureEnPassantLeft;
	public static CanCaptureEnPassantRight canCaptureEnPassantRight;
		
	/*
	 * Takes a copy of the Board state the GameState object and uses them to 
	 * initialize the various constraints. 
	 */
	public PieceFactory(Board board, GameState gs){
		if(singleMove == null){
			singleMove = new SingleMove();
			mustCapture = new MustCapture();
			mustMoveAlike = new MustMoveAlike();
			//mustBeenMoved = new MustBeenMoved();
			notBeenMoved = new NotBeenMoved();
			//doubleMove = new DoubleMove();
			cannotCapture = new CannotCapture(board);
			canKingCastle = new CanKingCastle();
			canQueenCasltle = new CanQueenCastle();
			cannotPassPiece = new CannotPassPiece();
			noPieceBehind = new NoPieceBehind(board);
			canCaptureEnPassantLeft = new CanCaptureEnPassantLeft(gs);
			canCaptureEnPassantRight = new CanCaptureEnPassantRight(gs);
		}
	}
	
	/*
	 * The workhorse of the PieceFactoy, this method takes a PieceType
	 * and a Color and produces the appropriate piece with all of the
	 * appropriate MoveTypes and Constraints.
	 */
	public Piece makePiece(PieceType type, PieceType display, PieceColor color){
		Piece newPiece;
		if(type == PieceType.King) // NOTE: there could possibly be multiple types that count as kings
			newPiece = new King(type, display, color);
		else
			newPiece = new Piece(type, display, color);
		switch(type){
			case Pawn : newPiece.addMove(MoveType.Forward, new MoveConstraint[]{singleMove, cannotCapture}); 
						newPiece.addMove(MoveType.ForwardLeft, new MoveConstraint[]{singleMove, mustCapture});
						newPiece.addMove(MoveType.ForwardRight, new MoveConstraint[]{singleMove, mustCapture});
						newPiece.addMove(MoveType.ForwardTwo, new MoveConstraint[]{singleMove, notBeenMoved, cannotCapture, noPieceBehind});
						newPiece.addMove(MoveType.EnPassantRight, new MoveConstraint[]{singleMove, canCaptureEnPassantRight});
						newPiece.addMove(MoveType.EnPassantLeft, new MoveConstraint[]{singleMove, canCaptureEnPassantLeft});
						newPiece.addMove(MoveType.NonStandard, new MoveConstraint[]{});
				break;
			case Knight : newPiece.addMove(MoveType.LForwardLeft, new MoveConstraint[]{singleMove});
						  newPiece.addMove(MoveType.LForwardRight, new MoveConstraint[]{singleMove});
						  newPiece.addMove(MoveType.LRightForward, new MoveConstraint[]{singleMove});
						  newPiece.addMove(MoveType.LRightBackward, new MoveConstraint[]{singleMove});
						  newPiece.addMove(MoveType.LBackwardRight, new MoveConstraint[]{singleMove});
						  newPiece.addMove(MoveType.LBackwardLeft, new MoveConstraint[]{singleMove});
						  newPiece.addMove(MoveType.LLeftBackward, new MoveConstraint[]{singleMove});
						  newPiece.addMove(MoveType.LLeftForward, new MoveConstraint[]{singleMove});
						  newPiece.addMove(MoveType.NonStandard, new MoveConstraint[]{});
				break;
			case Bishop : newPiece.addMove(MoveType.ForwardLeft, new MoveConstraint[]{mustMoveAlike, cannotPassPiece});
						  newPiece.addMove(MoveType.ForwardRight, new MoveConstraint[]{mustMoveAlike, cannotPassPiece});
						  newPiece.addMove(MoveType.BackwardRight, new MoveConstraint[]{mustMoveAlike, cannotPassPiece});
						  newPiece.addMove(MoveType.BackwardLeft, new MoveConstraint[]{mustMoveAlike, cannotPassPiece});
						  newPiece.addMove(MoveType.NonStandard, new MoveConstraint[]{});
				break;
			case Rook : newPiece.addMove(MoveType.Forward, new MoveConstraint[]{mustMoveAlike, cannotPassPiece});
			  			newPiece.addMove(MoveType.Right, new MoveConstraint[]{mustMoveAlike, cannotPassPiece});
			  			newPiece.addMove(MoveType.Backward, new MoveConstraint[]{mustMoveAlike, cannotPassPiece});
			  			newPiece.addMove(MoveType.Left, new MoveConstraint[]{mustMoveAlike, cannotPassPiece});
			  			newPiece.addMove(MoveType.ReverseKingSideCastle, new MoveConstraint[]{singleMove, notBeenMoved});
			  			newPiece.addMove(MoveType.ReverseQueenSideCastle, new MoveConstraint[]{singleMove, notBeenMoved});
			  			newPiece.addMove(MoveType.NonStandard, new MoveConstraint[]{});
				break;
			case Queen : newPiece.addMove(MoveType.ForwardLeft, new MoveConstraint[]{mustMoveAlike, cannotPassPiece});
			  			 newPiece.addMove(MoveType.ForwardRight, new MoveConstraint[]{mustMoveAlike, cannotPassPiece});
			  			 newPiece.addMove(MoveType.BackwardRight, new MoveConstraint[]{mustMoveAlike, cannotPassPiece});
			  			 newPiece.addMove(MoveType.BackwardLeft, new MoveConstraint[]{mustMoveAlike, cannotPassPiece});
			  			 newPiece.addMove(MoveType.Forward, new MoveConstraint[]{mustMoveAlike, cannotPassPiece});
			  			 newPiece.addMove(MoveType.Right, new MoveConstraint[]{mustMoveAlike, cannotPassPiece});
			  			 newPiece.addMove(MoveType.Backward, new MoveConstraint[]{mustMoveAlike, cannotPassPiece});
			  			 newPiece.addMove(MoveType.Left, new MoveConstraint[]{mustMoveAlike, cannotPassPiece});
			  			 newPiece.addMove(MoveType.NonStandard, new MoveConstraint[]{});
				break;
			case King : newPiece.addMove(MoveType.ForwardLeft, new MoveConstraint[]{singleMove});
 			 			newPiece.addMove(MoveType.ForwardRight, new MoveConstraint[]{singleMove});
 			 			newPiece.addMove(MoveType.BackwardRight, new MoveConstraint[]{singleMove});
 			 			newPiece.addMove(MoveType.BackwardLeft, new MoveConstraint[]{singleMove});
 			 			newPiece.addMove(MoveType.Forward, new MoveConstraint[]{singleMove});
 			 			newPiece.addMove(MoveType.Right, new MoveConstraint[]{singleMove});
 			 			newPiece.addMove(MoveType.Backward, new MoveConstraint[]{singleMove});
 			 			newPiece.addMove(MoveType.Left, new MoveConstraint[]{singleMove});
 			 			newPiece.addMove(MoveType.KingSideCastle, new MoveConstraint[]{notBeenMoved, singleMove, canKingCastle});
 			 			newPiece.addMove(MoveType.QueenSideCastle, new MoveConstraint[]{notBeenMoved, singleMove, canQueenCasltle});
 			 			newPiece.addMove(MoveType.NonStandard, new MoveConstraint[]{});
				break;
		}
		return newPiece;
	}
}
