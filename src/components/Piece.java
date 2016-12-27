package components;
import game.GameState;
import game.MoveBuilder;
import graphics.ChessImages;

import java.awt.Image;
import java.util.ArrayList;
import moves.ActualMove;
import utility.*;
import constraints.MoveConstraint;
import definitions.*;

/*
 * Piece.java
 * A Piece object represents a chess piece in the game. Pieces
 * have a PieceType, Color and a list of MoveTypeAndConstraints
 * objects (for each type of Move that a Piece can make, there is 
 * a list of Constraint objects used to prevent that piece from 
 * making and invalid move). A Piece also keeps track of whether 
 * or not it has been moved away from it's original position.
 * Piece implements the PieceSubject and PieceObserver interfaces
 * to assist in keeping a piece from moving into check. When ever 
 * a Piece object is "moved," it notifies the king(s)
 * (notifyKingObservers()) which returns if that king has been
 * placed in check by this move. Each king of this piece's own 
 * color should be registered/unregistered through the course of
 * the game to insure appropriate functionality.
 */
public class Piece implements PieceSubject, PieceObserver{
	protected PieceType type;
	protected PieceColor color;
	protected Space space;
	protected Image whiteImg;
	protected Image greyImg;
	
	// Represents whether a piece has been moved away from it's 
	// original position.
	boolean beenMoved = false;
	
	// A list of MoveTypeAndConstraints objects that hold what 
	// moves a piece can make and the constraints placed on those
	// movements.
	protected ArrayList<MoveTypeAndConstraints> moveTypesAndConstraints;
	private ArrayList<KingObserver> kings;
	private static GameState gs;
	
	/* A convenience method to prevent unnecessary passing. 
	 * Should be called before any of the following:
	 * updateOpposingPiece() and tryEveryValieMove().
	 */
	public static void setGameState(GameState gs){
		Piece.gs = gs;
	}
	
	public Piece(PieceType type, PieceColor color){
		this.type = type;
		this.color = color;
		this.whiteImg = ChessImages.getImage(this.type, this.color, SpaceColor.White);
		this.greyImg = ChessImages.getImage(this.type, this.color, SpaceColor.Gray);
		moveTypesAndConstraints = new ArrayList<MoveTypeAndConstraints>();
		kings = new ArrayList<KingObserver>();
	}
	
	// Public getters 
	public PieceType getType() {
		return type;
	}
	
	public PieceColor getColor(){
		return color;
	}
	
	public java.awt.Color getAWTColor(){
		if(color == PieceColor.White)
			return java.awt.Color.WHITE;
		else
			return java.awt.Color.BLACK;
	}
	public Space getSpace(){
		return space;
	}
	
	// public setters
	public void setSpace(Space space){
		this.space = space;
	}
	
	/*
	 * Used to add a new MoveType with corresponding Constraint objects
	 * to the Piece's moveTypesAndConstraints. WARNING: BEHAVIOR IS NOT
	 * GUARANTEED FOR DUPLICATE MOVE TYPES!!
	 */
	public void addMove(MoveType moveType, MoveConstraint[] constraints){
		moveTypesAndConstraints.add(new MoveTypeAndConstraints(moveType,constraints));
	}
	
	/*
	 * Sets a piece as having moved away from its original
	 * location. Before it is called, beenMoved will return
	 * false. Once called, beenMoved() will always return
	 * true. There is no way to set it back.
	 */
	public void setBeenMoved(boolean value){
		beenMoved = value;
	}
	
	/*
	 * Returns whether markAsMoved() has been called.
	 */
	public boolean beenMoved(){
		return beenMoved;
	}

	/*
	 * Returns a list of Constraint objects associated with
	 * the given MoveType, if that MoveType has been added
	 * to the Piece using the addMove() method. Otherwise,
	 * returns null;
	 */
	public MoveConstraint[] getConstraints(MoveType moveType) {
		for(int i = 0; i < moveTypesAndConstraints.size(); i++)
			if(moveTypesAndConstraints.get(i).getMoveType() == moveType)
				return moveTypesAndConstraints.get(i).getConstraints();
		return null;
	}

	@Override
	/*
	 * The only method in the PieceObserver interface,
	 * this method is called by the opposing King
	 * after that side makes a move. destination is the
	 * location of the opposing King. Returns true if it
	 * cannot capture the king, false otherwise.
	 */
	public boolean updateOpposingPiece(Space destination) {
		ErrorMessage message = new ErrorMessage();
		Turn turn = color == PieceColor.White ? Turn.Player1 : Turn.Player2;
		ActualMove move = MoveBuilder.buildMoveObject(space, destination, gs, message);
		if(move == null || !gs.meetsUniversalConstraints(move, turn, message))
			return true;
		return false;
	}

	@Override
	/*
	 * This method should be called for each king of 
	 * this piece's own color. Otherwise, the mechanism
	 * to disallow self-check will not work. 
	 */
	public void registerKingObserver(KingObserver o) {
		kings.add(o);
	}

	@Override
	/*
	 * This method should be called after a duplicate
	 * king leaves play. Otherwise, the mechanism to
	 * disallow self-check will not work.
	 */
	public void removeKingObserver(KingObserver o) {
		int index = kings.indexOf(o);
		if(index >= 0)
			kings.remove(index);
	}

	@Override
	/*
	 * Notifies each kings of the same color as this 
	 * piece that this piece has moved. This is the 
	 * beginning of the mechanism to prevent a player 
	 * from making a move that would place one of it's
	 * own kings in check. Returns true if the king
	 * has not been placed in check.
	 */
	public boolean notifyKingObservers() {
		for(KingObserver k : kings){
			if(!k.updateKing())
				return false;
		}
		return true;
	}

	/*
	 *  This method returns true if this piece can make
	 *  a valid move. False otherwise.
	 */
	public boolean checkForValidMove() {
		// for each MoveType that this piece can make
		for(MoveTypeAndConstraints mAndC : moveTypesAndConstraints){
			
			// reset message
			ErrorMessage message = new ErrorMessage();
			
			// get the Move object
			ActualMove move = MoveBuilder.buildMoveObject(space, mAndC.getMoveType(), gs, message);
			
			while(move != null && gs.meetsUniversalConstraints(move, 
					(this.getColor() == PieceColor.White) ? Turn.Player1 : Turn.Player2, 
							message)){
				
				Piece captured = GameState.movePiece(move, false);

				// check for self-check
				for(KingObserver k : kings){	
					// if not self-check, this move is valid
					if(k.updateKing()){
					GameState.undoMove(move, captured, false);
						
						// we have found a valid move
						return true;
					}
				}
				GameState.undoMove(move, captured, false);
				
				// repeat the move on top of the last move
				// NOTE: this code assume only same move combinations!!
				// also, this code seems to work by accident, it should be changed
				move = MoveBuilder.buildMoveObject(move, mAndC.getMoveType(), gs, message);
			}	
		}
		// there are no valid moves for this piece.
		return false;
	}
}
