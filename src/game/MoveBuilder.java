package game;
import moves.*;
import utility.ErrorMessage;
import components.*;
import definitions.*;
import factory.MoveFactory;

/*
 * This class is used to construct complete, fully wrapped, move objects
 * from just the location information. It can also be used to wrap a pre-
 * existing Move object with a ActualMove object of a given type.
 */
public class MoveBuilder{
	private MoveBuilder(){}
	
	/*
	 * Takes an Space object, the moveType of the move to create, the GameState object for this game,
	 * and an ErrorMessage for returning error results. Returns a new ActualMove of the given type 
	 * initiated from the given space. Returns null if there is no Piece to move or if no legal move
	 * can be created. NOTE: THERE MAY BE AWAY TO CALL MoveCompositor.compositeMoves() DIRECTLY FROM THIS 
	 * FUNCTION.
	 */
	public static ActualMove buildMoveObject(Space space, MoveType moveType, GameState gs, ErrorMessage message) {
		PieceColor color = space.getPiece().getColor();
		int rankOffset = moveType.getRankOffset();
		int fileOffset = moveType.getFileOffset();
		
		// flip offset if the piece is black, except in the case of certain MoveTypes
		if(moveType != MoveType.KingSideCastle && moveType != MoveType.ReverseKingSideCastle &&
		   moveType != MoveType.QueenSideCastle && moveType != MoveType.ReverseQueenSideCastle &&
		   color == PieceColor.Black){
			rankOffset = -rankOffset;
			fileOffset = -fileOffset;
		}
		
		/* should be able to run this code, but can't for some reason */
		//return MoveCompositor.compositeMoves(new Touch(space), MoveFactory.makeMoveObject(moveType, color), message);
		
		/* The less efficient version */
		Space destination = gs.getBoard().getNextSpace(rankOffset, fileOffset, space);
		if(destination != null)
			return buildMoveObject(space, destination, gs, message);
		else
			return null;
	}

	/*
	 * Returns a valid Move from the given initial Space to the destination Space, using the given 
	 * GameState object and sending any errors to the provided ErrorMessage. Returns null if there
	 * is no Piece to move or if no legal move can be created from initial to destination for the 
	 * Piece at initial.
	 */
	public static ActualMove buildMoveObject(Space initial, Space destination, GameState gs, ErrorMessage message){
		Move move = new Touch(initial);
		return (ActualMove) buildMoveObject(move,destination,gs,message);
	}
	
	/*
	 * Takes an ActualMoveObject, the moveType of the move to create, the GameState object for this game,
	 * and an ErrorMessage for returning error results. Returns a new ActualMove of the given type and wraps 
	 * it around the given ActualMove. Returns null if there is no Piece to move or if no legal move
	 * can be created. NOTE: THERE MAY BE AWAY TO CALL MoveCompositor.compositeMoves() DIRECTLY FROM THIS 
	 * FUNCTION, BUT WHEN I TRIED IT THE CODE BROKE. SO, I CHANGED IT BACK.
	 */
	public static ActualMove buildMoveObject(ActualMove move, MoveType moveType, GameState gs, ErrorMessage message) {
		Space dest = gs.getBoard().getNextSpace(moveType.getRankOffset(), moveType.getFileOffset(), move.getDestinationSpace());
		if(dest == null)
			return null;
		return buildMoveObject(move, dest, gs, message);
		
	}

	/*
	 * This is the primary buildMoveObject that all the other buildMoveObjects call, whether directly or
	 * indirectly. Takes a Move object, which could be an ActualMove, the destinationSpace, the GameState
	 * object being used for this game, and an ErrorMessage object for returning error results. Returns an
	 * ActualMove that would get "one step closer" to the destination Space and wraps it around the given
	 * Move. Return's null if there is no Piece to move, or if a valid move cannot be created.
	 */
	public static ActualMove buildMoveObject(Move move, Space destination, GameState gs, ErrorMessage message){
		Piece pieceToMove = move.getInitialSpace().getPiece();
		if(pieceToMove == null){
			message.setNoPieceToMove();
			return null;
		}
		
		Space current = move.getDestinationSpace();
		Rank rank = current.getRank();
		File file = current.getFile();
		
		//while the last move created doesn't reach it's destination space....
		while((rank != destination.getRank() || file != destination.getFile())){
			// created the next move Object
			move = selectMove(move, destination, gs, message);
			
			// the move given was invalid
			if(move == null)
				return null;
			
			// update the rank and file
			rank = move.getDestinationSpace().getRank();
			file = move.getDestinationSpace().getFile();
		}
		return (ActualMove) move;
	}

	/*
	 * Given a Move object and a destination Space object, identifies the next ActualMove object
	 * to wrap around the given Move object and returns the created Move, if possible. Also 
	 * takes an GameState object which holds the current game state, along with an ErrorMessage
	 * used to record information about any errors that occur. Returns null if an ActualMove
	 * cannot be created.
	 */
	private static ActualMove selectMove(Move move, Space destination, GameState gs, ErrorMessage message){
		Rank rank = move.getDestinationSpace().getRank();
		File file = move.getDestinationSpace().getFile();
		Rank destinationRank = destination.getRank();
		File destinationFile = destination.getFile();
		
		//Select move group
		if(rank == destinationRank)
			move = moveAlongRank(move, destination, gs, message);
		else if(file == destinationFile)
			move = moveAlongFile(move, destination, message);
		else if(Math.abs(rank.ordinal() - destinationRank.ordinal()) == 
			    Math.abs(file.ordinal() - destinationFile.ordinal()))
			move = moveAlongDiagonal(move, destination, message);
		else
			move = moveAlongLShape(move, destination, message);
		
		return (ActualMove) move;
	}
		
	/*
	 * Called as a catch all for irregular moves that aren't along the Rank, File, or diagonal.
	 * Takes a Move object, the destination, the ErrorMessage to record errors with, and returns
	 * an ActualMove that is one of the LShape moves, if possible.
	 */
	private static ActualMove moveAlongLShape(Move move, Space destination, ErrorMessage message) {
		int rankOffset = destination.getRank().ordinal() - move.getDestinationSpace().getRank().ordinal();
		int fileOffset = destination.getFile().ordinal() - move.getDestinationSpace().getFile().ordinal();
		PieceColor pieceColor = move.getInitialSpace().getPiece().getColor();
		
		// flip coordinates if the piece is black
		if(pieceColor == PieceColor.Black){
			rankOffset = -rankOffset;
			fileOffset = -fileOffset;
		}
		if(rankOffset == 2) // 2 spaces forward
			if(fileOffset == 1) // 1 space right
				return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.LForwardRight, pieceColor), message);
			else if(fileOffset == -1) // 1 space left
				return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.LForwardLeft, pieceColor), message);
		
		if(rankOffset == -2) // 2 spaces back
			if(fileOffset == 1) // 1 space right
				return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.LBackwardRight, pieceColor), message);
			else if(fileOffset == -1) // 1 space left
				return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.LBackwardLeft, pieceColor), message);
		
		if(fileOffset == 2) // 2 spaces right
			if(rankOffset == 1) // 1 space forward
				return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.LRightForward, pieceColor), message);
			else if(rankOffset == -1) // 1 space back
				return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.LRightBackward, pieceColor), message);
			
		if(fileOffset == -2) // 2 spaces left
			if(rankOffset == 1) // 1 space forward
				return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.LLeftForward, pieceColor), message);
			else if(rankOffset == -1) // 1 space back
				return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.LLeftBackward, pieceColor), message);
		
		// not an L-shaped move
		return null;
	}

	/*
	 * Called for diagonal moves. Takes a Move object, the destination, the 
	 * ErrorMessage to record errors with, and returns an ActualMove that is one of the LShape moves, if possible.
	 */
	private static ActualMove moveAlongDiagonal(Move move, Space destination, ErrorMessage message) {
		int rankOffset = destination.getRank().ordinal() - move.getDestinationSpace().getRank().ordinal();
		int fileOffset = destination.getFile().ordinal() - move.getDestinationSpace().getFile().ordinal();
		PieceColor pieceColor = move.getInitialSpace().getPiece().getColor();
		if(pieceColor == PieceColor.Black){
			rankOffset = -rankOffset;
			fileOffset = -fileOffset;
		}
		if(rankOffset > 0) // forward
			if(fileOffset > 0) {// right
				Piece right;
				PieceColor color = move.getInitialSpace().getPiece().getColor();
				if(color == PieceColor.White)
					right = move.getInitialSpace().getSpaceRight().getPiece();
				else
					right = move.getInitialSpace().getSpaceLeft().getPiece();
				if(move.getInitialSpace().getPiece().getType() == PieceType.Pawn && destination.getPiece() == null
						&& right != null && right.getType() == PieceType.Pawn
						&& right.getColor() != move.getInitialSpace().getPiece().getColor()
						&& (color == PieceColor.White && move.getInitialSpace().getRank() == Rank.Five ||
							color == PieceColor.Black && move.getInitialSpace().getRank() == Rank.Four))
					return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.EnPassantRight, pieceColor), message);
				else
					return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.ForwardRight, pieceColor), message);
			}else {// fileOffset < 0, left
				Piece left;
				PieceColor color = move.getInitialSpace().getPiece().getColor();
				if(color == PieceColor.White)
					left = move.getInitialSpace().getSpaceLeft().getPiece();
				else
					left= move.getInitialSpace().getSpaceRight().getPiece();
				if(move.getInitialSpace().getPiece().getType() == PieceType.Pawn && destination.getPiece() == null
						&& left != null && left.getType() == PieceType.Pawn
						&& left.getColor() != move.getInitialSpace().getPiece().getColor()
						&& (color == PieceColor.White && move.getInitialSpace().getRank() == Rank.Five ||
							color == PieceColor.Black && move.getInitialSpace().getRank() == Rank.Four)
						)
					return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.EnPassantLeft, pieceColor), message);
				else
					return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.ForwardLeft, pieceColor), message);
		}else // rankOffset < 0, back
			if(fileOffset > 0) // right
				return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.BackwardRight, pieceColor), message);
			else // fileOffset < 0, left 
				return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.BackwardLeft, pieceColor), message);
	}

	/*
	 * Called for moves that only change the Rank, or row, that a piece will be on, and not
	 * the file it is own. Takes a Move object, the destination to move toward, and the
	 * ErrorMessage object to report errors to. Returns a new ActualMove wrapped around the
	 * given move that is "one step closer" along the file toward being at the given destination.
	 * Returns null if such a move cannot be legally created.
	 */
	private static ActualMove moveAlongFile(Move move, Space destination, ErrorMessage message) {
		int rankOffset = destination.getRank().ordinal() - move.getDestinationSpace().getRank().ordinal();
		PieceColor pieceColor = move.getInitialSpace().getPiece().getColor();
		
		// flip coordinates if piece color is black
		if(pieceColor == PieceColor.Black)
			rankOffset = -rankOffset;
		
		if(//!move.getInitialSpace().getPiece().beenMoved() &&
			move.getInitialSpace().getPiece().getType() == PieceType.Pawn && 
			rankOffset == 2) // a pawn's first move
				return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.ForwardTwo, pieceColor), message);
		if(rankOffset > 0) // forward
			return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.Forward, pieceColor), message);
		else // rankOffset < 0, back
			return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.Backward, pieceColor), message);
	}

	/*
	 * Called for moves that only change the File, or column, that a piece will be on, and not
	 * the file it is own. Takes a Move object, the destination to move toward, and the
	 * ErrorMessage object to report errors to. Returns a new ActualMove wrapped around the
	 * given move that is "one step closer" along the Rank toward being at the given destination.
	 * Returns null if such a move cannot be legally created.
	 */
	private static ActualMove moveAlongRank(Move move, Space destination, GameState gs, ErrorMessage message) {
		int fileOffset = destination.getFile().ordinal() - move.getDestinationSpace().getFile().ordinal();
		Space initial = move.getInitialSpace();
		PieceColor pieceColor = initial.getPiece().getColor();
		
		// check for castling
		if(initial.getPiece().getType() == PieceType.King && Math.abs(fileOffset) == 2){
			if(fileOffset == 2){ //king side castle
				ActualMove result = MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.KingSideCastle, pieceColor), message);
				if(result != null){
					// move the rook
					Space rookDest = initial.getSpaceRight();
					Space rookInit = destination.getSpaceRight();
					ActualMove rookMove = buildMoveObject(rookInit, rookDest, gs, new ErrorMessage());
					gs.makeMove(rookMove, pieceColor == PieceColor.White ? Turn.Player1 : Turn.Player2, message);
				}
				return result; 
			}
			else if(fileOffset == -2){ // queen side castle
				ActualMove result =MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.QueenSideCastle, pieceColor), message);
				if(result != null){
					// move  the rook
					Space rookDest = initial.getSpaceLeft();
					Space rookInit = rookDest.getSpaceLeft().getSpaceLeft().getSpaceLeft();
					ActualMove rookMove = buildMoveObject(rookInit, rookDest, gs, new ErrorMessage());
					gs.makeMove(rookMove, pieceColor == PieceColor.White ? Turn.Player1 : Turn.Player2, message);
				}
				return result; 
			}
			else
				return null;
		}
		
		// check for the rook counterpart of castle
		else if(initial.getPiece().getType() == PieceType.Rook && 
			    gs.getPreviousMove().getClass() == MoveKingSideCastle.class){
			if(fileOffset == -2)
				return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.ReverseKingSideCastle, pieceColor), message);
			else{
				return null;
			}
		}
		else if(initial.getPiece().getType() == PieceType.Rook &&
				gs.getPreviousMove().getClass() == MoveQueenSideCastle.class){
			if(fileOffset == 3)
				return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.ReverseQueenSideCastle, pieceColor), message);
			else
				return null;
		}
		
		// for other moves, flip coordinates if the moving piece is black
		if(pieceColor == PieceColor.Black)
			fileOffset = -fileOffset;
		if(fileOffset > 0) // right
			return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.Right, pieceColor), message);
		else // fileOffset < 0, left
			return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.Left, pieceColor), message);
	}
}
